package com.example.data.repository

import android.graphics.Bitmap
import com.example.data.local.BookmarkDao
import com.example.data.local.QuestionDao
import com.example.data.local.QuizAttemptDao
import com.example.data.models.*
import com.example.data.remote.GeminiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

data class TopicPerformance(
    val subject: String,
    val topic: String,
    val totalAnswered: Int,
    val correctCount: Int,
    val accuracyPercentage: Double
)

data class SubjectPerformance(
    val subject: ExamSubject,
    val totalAnswered: Int,
    val correctCount: Int,
    val accuracyPercentage: Double
)

data class GapAnalysisResult(
    val overallAccuracy: Double,
    val totalAttemptedTests: Int,
    val subjectBreakdown: List<SubjectPerformance>,
    val weakTopics: List<TopicPerformance>,
    val strongTopics: List<TopicPerformance>,
    val recommendations: List<String>
)

class ExamRepository(
    private val questionDao: QuestionDao,
    private val quizAttemptDao: QuizAttemptDao,
    private val bookmarkDao: BookmarkDao
) {
    val allQuestions: Flow<List<QuestionEntity>> = questionDao.getAllQuestions()
    val allAttempts: Flow<List<QuizAttemptEntity>> = quizAttemptDao.getAllAttempts()
    val allBookmarks: Flow<List<BookmarkEntity>> = bookmarkDao.getAllBookmarks()

    suspend fun getQuestionCount(): Int = questionDao.getCount()

    suspend fun getQuestionsForTrack(track: ExamTrack, limit: Int = 20): List<QuestionEntity> = withContext(Dispatchers.IO) {
        if (track == ExamTrack.GENERAL) {
            questionDao.getRandomQuestionsForGeneral(limit)
        } else {
            questionDao.getRandomQuestionsForNirmanSahayak(limit)
        }
    }

    suspend fun getQuestionsForSubject(subjectName: String, limit: Int = 15): List<QuestionEntity> = withContext(Dispatchers.IO) {
        questionDao.getRandomQuestionsForSubject(subjectName, limit)
    }

    suspend fun getQuestionsForTopics(topics: List<String>, limit: Int = 15): List<QuestionEntity> = withContext(Dispatchers.IO) {
        questionDao.getRandomQuestionsForTopics(topics, limit)
    }

    suspend fun getQuestionsByIds(ids: List<Long>): List<QuestionEntity> = withContext(Dispatchers.IO) {
        questionDao.getQuestionsByIds(ids)
    }

    suspend fun getDailyQuestion(): QuestionEntity? = withContext(Dispatchers.IO) {
        val dailyList = questionDao.getDailyQuestions().first()
        if (dailyList.isNotEmpty()) {
            dailyList.first()
        } else {
            questionDao.getAllQuestions().first().firstOrNull()
        }
    }

    suspend fun saveQuizAttempt(
        track: ExamTrack,
        testType: TestType,
        testTitle: String,
        score: Double,
        totalMarks: Double,
        totalQuestions: Int,
        correctCount: Int,
        wrongCount: Int,
        unattemptedCount: Int,
        timeTakenSeconds: Int,
        answers: List<Pair<QuestionEntity, Int>> // (Question, selectedOptionIndex)
    ): Long = withContext(Dispatchers.IO) {
        val attempt = QuizAttemptEntity(
            track = track.name,
            testType = testType.name,
            testTitle = testTitle,
            score = score,
            totalMarks = totalMarks,
            totalQuestions = totalQuestions,
            correctCount = correctCount,
            wrongCount = wrongCount,
            unattemptedCount = unattemptedCount,
            timeTakenSeconds = timeTakenSeconds
        )
        val attemptId = quizAttemptDao.insertAttempt(attempt)

        val answerRecords = answers.map { (q, selectedIdx) ->
            AnswerRecordEntity(
                attemptId = attemptId,
                questionId = q.id,
                subject = q.subject,
                topic = q.topic,
                selectedOptionIndex = selectedIdx,
                isCorrect = selectedIdx == q.correctAnswerIndex
            )
        }
        quizAttemptDao.insertAnswerRecords(answerRecords)
        attemptId
    }

    suspend fun toggleBookmark(questionId: Long, note: String = ""): Boolean = withContext(Dispatchers.IO) {
        val isBookmarked = bookmarkDao.isBookmarked(questionId).first()
        if (isBookmarked) {
            bookmarkDao.deleteBookmark(questionId)
            false
        } else {
            bookmarkDao.insertBookmark(BookmarkEntity(questionId = questionId, note = note))
            true
        }
    }

    fun isBookmarked(questionId: Long): Flow<Boolean> = bookmarkDao.isBookmarked(questionId)

    suspend fun calculateGapAnalysis(): GapAnalysisResult = withContext(Dispatchers.IO) {
        val answers = quizAttemptDao.getAllAnswerRecords().first()
        val attempts = quizAttemptDao.getAllAttempts().first()

        if (answers.isEmpty()) {
            return@withContext GapAnalysisResult(
                overallAccuracy = 0.0,
                totalAttemptedTests = attempts.size,
                subjectBreakdown = ExamSubject.values().map {
                    SubjectPerformance(it, 0, 0, 0.0)
                },
                weakTopics = emptyList(),
                strongTopics = emptyList(),
                recommendations = listOf(
                    "Take your first Weekly Mock Test or Topic Drill to evaluate baseline performance across all subjects.",
                    "Review Bengali grammar (Sandhi & Samas) and Panchayati Raj 73rd Amendment."
                )
            )
        }

        val totalAnswered = answers.size
        val totalCorrect = answers.count { it.isCorrect }
        val overallAccuracy = if (totalAnswered > 0) (totalCorrect.toDouble() / totalAnswered) * 100.0 else 0.0

        // Subject breakdown
        val subjectGroups = answers.groupBy { it.subject }
        val subjectBreakdown = ExamSubject.values().map { subj ->
            val list = subjectGroups[subj.name] ?: emptyList()
            val correct = list.count { it.isCorrect }
            val count = list.size
            val acc = if (count > 0) (correct.toDouble() / count) * 100.0 else 0.0
            SubjectPerformance(subj, count, correct, acc)
        }

        // Topic breakdown
        val topicGroups = answers.groupBy { "${it.subject}:::${it.topic}" }
        val topicPerformances = topicGroups.map { (key, list) ->
            val parts = key.split(":::")
            val subj = parts.getOrNull(0) ?: ""
            val topic = parts.getOrNull(1) ?: ""
            val correct = list.count { it.isCorrect }
            val count = list.size
            val acc = if (count > 0) (correct.toDouble() / count) * 100.0 else 0.0
            TopicPerformance(subj, topic, count, correct, acc)
        }

        val sortedByAccuracy = topicPerformances.sortedBy { it.accuracyPercentage }
        val weakTopics = sortedByAccuracy.filter { it.totalAnswered >= 1 && it.accuracyPercentage < 65.0 }.take(5)
        val strongTopics = topicPerformances.filter { it.totalAnswered >= 1 && it.accuracyPercentage >= 75.0 }.sortedByDescending { it.accuracyPercentage }.take(5)

        val recommendations = mutableListOf<String>()
        if (weakTopics.isNotEmpty()) {
            val topWeak = weakTopics.take(3).joinToString(", ") { it.topic }
            recommendations.add("Priority Focus: High mistake frequency in [$topWeak]. Run a targeted recovery practice session.")
        }
        val bengaliAcc = subjectBreakdown.find { it.subject == ExamSubject.BENGALI }?.accuracyPercentage ?: 0.0
        if (bengaliAcc < 60 && bengaliAcc > 0) {
            recommendations.add("Bengali Language (25M): Strengthen Sandhi, Samas, and Spelling rules using the AI Tutor explanations.")
        }
        val mathAcc = subjectBreakdown.find { it.subject == ExamSubject.ARITHMETIC }?.accuracyPercentage ?: 0.0
        if (mathAcc < 60 && mathAcc > 0) {
            recommendations.add("Arithmetic (25M): Focus on speed techniques for Profit & Loss, Percentage, and Time & Work.")
        }
        val gkAcc = subjectBreakdown.find { it.subject == ExamSubject.GK_RURAL_DEV }?.accuracyPercentage ?: 0.0
        if (gkAcc < 60 && gkAcc > 0) {
            recommendations.add("Rural Development: Revise key features of Lakshmir Bhandar, Krishak Bandhu, and 73rd Amendment Articles.")
        }
        if (recommendations.isEmpty()) {
            recommendations.add("Excellent overall mastery! Maintain consistency with weekly full-length timed mock tests.")
        }

        GapAnalysisResult(
            overallAccuracy = overallAccuracy,
            totalAttemptedTests = attempts.size,
            subjectBreakdown = subjectBreakdown,
            weakTopics = weakTopics,
            strongTopics = strongTopics,
            recommendations = recommendations
        )
    }

    suspend fun askAiTutor(
        prompt: String,
        useSearchGrounding: Boolean = false,
        useHighThinking: Boolean = false,
        imageBitmap: Bitmap? = null
    ): Result<String> {
        return GeminiClient.askTutor(
            prompt = prompt,
            useSearchGrounding = useSearchGrounding,
            useHighThinking = useHighThinking,
            imageBitmap = imageBitmap
        )
    }
}
