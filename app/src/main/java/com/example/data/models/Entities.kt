package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val subject: String, // ExamSubject name
    val topic: String, // e.g. "Sandhi (সন্ধি)", "Panchayati Raj 73rd Amendment", etc.
    val topicBangla: String,
    val questionText: String,
    val questionTextBangla: String? = null,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswerIndex: Int, // 0 = A, 1 = B, 2 = C, 3 = D
    val explanation: String,
    val explanationBangla: String? = null,
    val isForGeneralTrack: Boolean = true,
    val isForNirmanSahayak: Boolean = false,
    val difficulty: String = "MEDIUM", // EASY, MEDIUM, HARD
    val yearTag: String = "WB Panchayat Expected",
    val isDailyQuestion: Boolean = false
) {
    fun getDisplayText(isBengali: Boolean): String {
        return if (isBengali && !questionTextBangla.isNullOrBlank()) questionTextBangla else questionText
    }

    fun getDisplayExplanation(isBengali: Boolean): String {
        return if (isBengali && !explanationBangla.isNullOrBlank()) explanationBangla else explanation
    }
}

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val track: String, // GENERAL or NIRMAN_SAHAYAK
    val testType: String, // TestType name
    val testTitle: String,
    val score: Double,
    val totalMarks: Double,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val unattemptedCount: Int,
    val timeTakenSeconds: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "answer_records")
data class AnswerRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val attemptId: Long,
    val questionId: Long,
    val subject: String,
    val topic: String,
    val selectedOptionIndex: Int, // -1 if skipped
    val isCorrect: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val questionId: Long,
    val note: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
