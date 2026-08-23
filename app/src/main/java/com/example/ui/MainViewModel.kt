package com.example.ui

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.models.*
import com.example.data.repository.ExamRepository
import com.example.data.repository.GapAnalysisResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    PRACTICE_HUB,
    MOCK_TEST,
    TEST_RESULT,
    GAP_ANALYSIS,
    AI_TUTOR,
    MISTAKE_BOOK,
    BOOKMARKS
}

data class ChatMessage(
    val id: Long = System.currentTimeMillis(),
    val sender: String, // "user" or "gemini"
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val hasImage: Boolean = false,
    val isSearchGrounded: Boolean = false,
    val isHighThinking: Boolean = false
)

data class TestResultData(
    val attemptId: Long,
    val track: ExamTrack,
    val testType: TestType,
    val testTitle: String,
    val score: Double,
    val totalMarks: Double,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val unattemptedCount: Int,
    val timeTakenSeconds: Int,
    val questions: List<QuestionEntity>,
    val userAnswers: Map<Int, Int> // questionIndex -> selectedOptionIndex
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application, viewModelScope)
    private val repository = ExamRepository(
        questionDao = database.questionDao(),
        quizAttemptDao = database.quizAttemptDao(),
        bookmarkDao = database.bookmarkDao()
    )

    // Navigation state
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    // Selected track: General vs Nirman Sahayak
    private val _selectedTrack = MutableStateFlow(ExamTrack.GENERAL)
    val selectedTrack: StateFlow<ExamTrack> = _selectedTrack.asStateFlow()

    // Language toggle
    private val _isBengaliLanguage = MutableStateFlow(true)
    val isBengaliLanguage: StateFlow<Boolean> = _isBengaliLanguage.asStateFlow()

    // Daily Question
    private val _dailyQuestion = MutableStateFlow<QuestionEntity?>(null)
    val dailyQuestion: StateFlow<QuestionEntity?> = _dailyQuestion.asStateFlow()

    private val _dailyQuestionSelected = MutableStateFlow<Int?>(null)
    val dailyQuestionSelected: StateFlow<Int?> = _dailyQuestionSelected.asStateFlow()

    // Active Mock Test / Quiz State
    private val _testQuestions = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val testQuestions: StateFlow<List<QuestionEntity>> = _testQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _userAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userAnswers: StateFlow<Map<Int, Int>> = _userAnswers.asStateFlow()

    private val _markedForReview = MutableStateFlow<Set<Int>>(emptySet())
    val markedForReview: StateFlow<Set<Int>> = _markedForReview.asStateFlow()

    private val _timeRemainingSeconds = MutableStateFlow(0)
    val timeRemainingSeconds: StateFlow<Int> = _timeRemainingSeconds.asStateFlow()

    private val _totalTestTimeSeconds = MutableStateFlow(0)
    val totalTestTimeSeconds: StateFlow<Int> = _totalTestTimeSeconds.asStateFlow()

    private val _isTestActive = MutableStateFlow(false)
    val isTestActive: StateFlow<Boolean> = _isTestActive.asStateFlow()

    private val _activeTestType = MutableStateFlow(TestType.WEEKLY_MOCK)
    val activeTestType: StateFlow<TestType> = _activeTestType.asStateFlow()

    private val _activeTestTitle = MutableStateFlow("Weekly Full Mock Test")
    val activeTestTitle: StateFlow<String> = _activeTestTitle.asStateFlow()

    // Test Results
    private val _latestTestResult = MutableStateFlow<TestResultData?>(null)
    val latestTestResult: StateFlow<TestResultData?> = _latestTestResult.asStateFlow()

    // Gap Analysis State
    private val _gapAnalysis = MutableStateFlow<GapAnalysisResult?>(null)
    val gapAnalysis: StateFlow<GapAnalysisResult?> = _gapAnalysis.asStateFlow()

    // Practice Hub State
    private val _selectedPracticeSubject = MutableStateFlow<ExamSubject?>(null)
    val selectedPracticeSubject: StateFlow<ExamSubject?> = _selectedPracticeSubject.asStateFlow()

    private val _practiceQuestions = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val practiceQuestions: StateFlow<List<QuestionEntity>> = _practiceQuestions.asStateFlow()

    private val _practiceRevealedAnswers = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val practiceRevealedAnswers: StateFlow<Map<Long, Int>> = _practiceRevealedAnswers.asStateFlow()

    // Bookmarks and Mistake Book
    val allBookmarks = repository.allBookmarks
    val allAttempts = repository.allAttempts

    private val _bookmarkedQuestionList = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val bookmarkedQuestionList: StateFlow<List<QuestionEntity>> = _bookmarkedQuestionList.asStateFlow()

    private val _mistakeQuestionList = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val mistakeQuestionList: StateFlow<List<QuestionEntity>> = _mistakeQuestionList.asStateFlow()

    // AI Tutor / Doubt Solver State
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                sender = "gemini",
                text = "নমস্কার! I am your WB Panchayat Exam AI Tutor. Ask me any question on Bengali grammar, English, Arithmetic formulas, Panchayati Raj rules, or Nirman Sahayak Civil Engineering. You can also turn on Search Grounding for current welfare schemes or High Thinking mode for complex math!"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading: StateFlow<Boolean> = _isAiLoading.asStateFlow()

    private val _useSearchGrounding = MutableStateFlow(false)
    val useSearchGrounding: StateFlow<Boolean> = _useSearchGrounding.asStateFlow()

    private val _useHighThinking = MutableStateFlow(false)
    val useHighThinking: StateFlow<Boolean> = _useHighThinking.asStateFlow()

    private val _attachedBitmap = MutableStateFlow<Bitmap?>(null)
    val attachedBitmap: StateFlow<Bitmap?> = _attachedBitmap.asStateFlow()

    private var timerJob: Job? = null

    init {
        loadDailyQuestion()
        refreshGapAnalysis()
        loadBookmarksAndMistakes()
    }

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
        if (screen == AppScreen.GAP_ANALYSIS) {
            refreshGapAnalysis()
        } else if (screen == AppScreen.BOOKMARKS || screen == AppScreen.MISTAKE_BOOK) {
            loadBookmarksAndMistakes()
        }
    }

    fun selectTrack(track: ExamTrack) {
        _selectedTrack.value = track
        loadDailyQuestion()
    }

    fun toggleLanguage() {
        _isBengaliLanguage.value = !_isBengaliLanguage.value
    }

    private fun loadDailyQuestion() {
        viewModelScope.launch {
            _dailyQuestion.value = repository.getDailyQuestion()
        }
    }

    fun answerDailyQuestion(optionIndex: Int) {
        _dailyQuestionSelected.value = optionIndex
    }

    fun refreshGapAnalysis() {
        viewModelScope.launch {
            _gapAnalysis.value = repository.calculateGapAnalysis()
        }
    }

    fun loadBookmarksAndMistakes() {
        viewModelScope.launch {
            val bookmarks = repository.allBookmarks.first()
            val bookmarkedIds = bookmarks.map { it.questionId }
            _bookmarkedQuestionList.value = repository.getQuestionsByIds(bookmarkedIds)

            val wrongRecords = database.quizAttemptDao().getWrongAnswerRecords().first()
            val wrongIds = wrongRecords.map { it.questionId }.distinct()
            _mistakeQuestionList.value = repository.getQuestionsByIds(wrongIds)
        }
    }

    fun toggleBookmark(questionId: Long) {
        viewModelScope.launch {
            repository.toggleBookmark(questionId)
            loadBookmarksAndMistakes()
        }
    }

    // ===================================
    // TEST LAUNCHERS & ENGINE
    // ===================================

    fun startWeeklyMockTest() {
        viewModelScope.launch {
            val track = _selectedTrack.value
            val questions = repository.getQuestionsForTrack(track, limit = 20)
            val totalTime = questions.size * 60 // 1 min per question
            setupTest(
                questions = questions,
                testType = TestType.WEEKLY_MOCK,
                title = if (track == ExamTrack.GENERAL) "Weekly Full Mock Test #1 (General Cadre)" else "Weekly Full Mock Test #1 (Nirman Sahayak)",
                totalSeconds = totalTime
            )
        }
    }

    fun startMonthlyComprehensiveReview() {
        viewModelScope.launch {
            val track = _selectedTrack.value
            val questions = repository.getQuestionsForTrack(track, limit = 25)
            val totalTime = questions.size * 75 // 75s per question
            setupTest(
                questions = questions,
                testType = TestType.MONTHLY_REVIEW,
                title = "Monthly Comprehensive Review Test",
                totalSeconds = totalTime
            )
        }
    }

    fun startSubjectDrill(subject: ExamSubject) {
        viewModelScope.launch {
            val questions = repository.getQuestionsForSubject(subject.name, limit = 15)
            val totalTime = questions.size * 60
            setupTest(
                questions = questions,
                testType = TestType.TOPIC_DRILL,
                title = "${subject.displayName} Mastery Drill",
                totalSeconds = totalTime
            )
        }
    }

    fun startTargetedGapRecovery(topics: List<String>) {
        viewModelScope.launch {
            val questions = if (topics.isNotEmpty()) {
                repository.getQuestionsForTopics(topics, limit = 12)
            } else {
                repository.getQuestionsForTrack(_selectedTrack.value, limit = 10)
            }
            setupTest(
                questions = questions,
                testType = TestType.GAP_RECOVERY,
                title = "Targeted Weak-Topic Recovery Drill",
                totalSeconds = questions.size * 60
            )
        }
    }

    fun startMistakesRecoveryTest() {
        val mistakes = _mistakeQuestionList.value
        if (mistakes.isNotEmpty()) {
            setupTest(
                questions = mistakes,
                testType = TestType.GAP_RECOVERY,
                title = "Mistakes Revision Test",
                totalSeconds = mistakes.size * 60
            )
        }
    }

    private fun setupTest(
        questions: List<QuestionEntity>,
        testType: TestType,
        title: String,
        totalSeconds: Int
    ) {
        _testQuestions.value = questions
        _currentQuestionIndex.value = 0
        _userAnswers.value = emptyMap()
        _markedForReview.value = emptySet()
        _activeTestType.value = testType
        _activeTestTitle.value = title
        _totalTestTimeSeconds.value = totalSeconds
        _timeRemainingSeconds.value = totalSeconds
        _isTestActive.value = true

        startTimer()
        _currentScreen.value = AppScreen.MOCK_TEST
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeRemainingSeconds.value > 0 && _isTestActive.value) {
                delay(1000)
                _timeRemainingSeconds.value -= 1
            }
            if (_timeRemainingSeconds.value <= 0 && _isTestActive.value) {
                submitTest()
            }
        }
    }

    fun selectAnswer(questionIndex: Int, optionIndex: Int) {
        val updated = _userAnswers.value.toMutableMap()
        updated[questionIndex] = optionIndex
        _userAnswers.value = updated
    }

    fun clearAnswer(questionIndex: Int) {
        val updated = _userAnswers.value.toMutableMap()
        updated.remove(questionIndex)
        _userAnswers.value = updated
    }

    fun toggleMarkForReview(questionIndex: Int) {
        val updated = _markedForReview.value.toMutableSet()
        if (updated.contains(questionIndex)) {
            updated.remove(questionIndex)
        } else {
            updated.add(questionIndex)
        }
        _markedForReview.value = updated
    }

    fun setCurrentQuestionIndex(index: Int) {
        if (index in 0 until _testQuestions.value.size) {
            _currentQuestionIndex.value = index
        }
    }

    fun submitTest() {
        timerJob?.cancel()
        _isTestActive.value = false

        val questions = _testQuestions.value
        val answers = _userAnswers.value
        val timeTaken = _totalTestTimeSeconds.value - _timeRemainingSeconds.value

        var correctCount = 0
        var wrongCount = 0
        val unattemptedCount = questions.size - answers.size

        val answersForDb = mutableListOf<Pair<QuestionEntity, Int>>()

        questions.forEachIndexed { index, question ->
            val userSelected = answers[index] ?: -1
            answersForDb.add(Pair(question, userSelected))
            if (userSelected != -1) {
                if (userSelected == question.correctAnswerIndex) {
                    correctCount++
                } else {
                    wrongCount++
                }
            }
        }

        // Standard Panchayat Marking: +1.0 for correct, -0.25 negative marking for wrong
        val totalMarks = questions.size.toDouble()
        val score = (correctCount * 1.0) - (wrongCount * 0.25)
        val finalScore = maxOf(0.0, score)

        viewModelScope.launch {
            val attemptId = repository.saveQuizAttempt(
                track = _selectedTrack.value,
                testType = _activeTestType.value,
                testTitle = _activeTestTitle.value,
                score = finalScore,
                totalMarks = totalMarks,
                totalQuestions = questions.size,
                correctCount = correctCount,
                wrongCount = wrongCount,
                unattemptedCount = unattemptedCount,
                timeTakenSeconds = timeTaken,
                answers = answersForDb
            )

            _latestTestResult.value = TestResultData(
                attemptId = attemptId,
                track = _selectedTrack.value,
                testType = _activeTestType.value,
                testTitle = _activeTestTitle.value,
                score = finalScore,
                totalMarks = totalMarks,
                totalQuestions = questions.size,
                correctCount = correctCount,
                wrongCount = wrongCount,
                unattemptedCount = unattemptedCount,
                timeTakenSeconds = timeTaken,
                questions = questions,
                userAnswers = answers
            )

            refreshGapAnalysis()
            loadBookmarksAndMistakes()
            _currentScreen.value = AppScreen.TEST_RESULT
        }
    }

    // ===================================
    // PRACTICE HUB
    // ===================================

    fun openPracticeForSubject(subject: ExamSubject) {
        _selectedPracticeSubject.value = subject
        viewModelScope.launch {
            _practiceQuestions.value = repository.getQuestionsForSubject(subject.name, limit = 20)
            _practiceRevealedAnswers.value = emptyMap()
            _currentScreen.value = AppScreen.PRACTICE_HUB
        }
    }

    fun revealPracticeAnswer(questionId: Long, selectedOption: Int) {
        val updated = _practiceRevealedAnswers.value.toMutableMap()
        updated[questionId] = selectedOption
        _practiceRevealedAnswers.value = updated
    }

    // ===================================
    // AI TUTOR / DOUBT SOLVER
    // ===================================

    fun setSearchGrounding(enabled: Boolean) {
        _useSearchGrounding.value = enabled
    }

    fun setHighThinking(enabled: Boolean) {
        _useHighThinking.value = enabled
    }

    fun setAttachedBitmap(bitmap: Bitmap?) {
        _attachedBitmap.value = bitmap
    }

    fun sendAiPrompt(prompt: String) {
        if (prompt.isBlank()) return

        val userMessage = ChatMessage(
            sender = "user",
            text = prompt,
            hasImage = _attachedBitmap.value != null,
            isSearchGrounded = _useSearchGrounding.value,
            isHighThinking = _useHighThinking.value
        )

        _chatMessages.value = _chatMessages.value + userMessage
        _isAiLoading.value = true

        val image = _attachedBitmap.value
        val search = _useSearchGrounding.value
        val highThinking = _useHighThinking.value

        // Clear attached image for next turn
        _attachedBitmap.value = null

        viewModelScope.launch {
            val result = repository.askAiTutor(
                prompt = prompt,
                useSearchGrounding = search,
                useHighThinking = highThinking,
                imageBitmap = image
            )

            _isAiLoading.value = false

            val responseText = result.getOrElse { e ->
                "⚠️ ${e.message ?: "Unable to fetch response from Gemini. Please verify your internet connection or API key."}"
            }

            val geminiMessage = ChatMessage(
                sender = "gemini",
                text = responseText,
                isSearchGrounded = search,
                isHighThinking = highThinking
            )

            _chatMessages.value = _chatMessages.value + geminiMessage
        }
    }

    fun askAiAboutQuestion(question: QuestionEntity) {
        val prompt = "Please explain the concept and solution behind this question step-by-step with exam tips in Bengali & English:\n\nQuestion: ${question.questionText}\nTopic: ${question.topic}\nCorrect Answer: ${when(question.correctAnswerIndex) { 0 -> question.optionA; 1 -> question.optionB; 2 -> question.optionC; else -> question.optionD }}\nExplanation: ${question.explanation}"
        _currentScreen.value = AppScreen.AI_TUTOR
        sendAiPrompt(prompt)
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
