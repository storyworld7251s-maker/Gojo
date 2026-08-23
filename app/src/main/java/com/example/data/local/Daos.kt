package com.example.data.local

import androidx.room.*
import com.example.data.models.AnswerRecordEntity
import com.example.data.models.BookmarkEntity
import com.example.data.models.QuestionEntity
import com.example.data.models.QuizAttemptEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE subject = :subjectName")
    fun getQuestionsBySubject(subjectName: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE subject = :subjectName AND topic = :topic")
    suspend fun getQuestionsByTopic(subjectName: String, topic: String): List<QuestionEntity>

    @Query("SELECT DISTINCT topic FROM questions WHERE subject = :subjectName")
    fun getTopicsForSubject(subjectName: String): Flow<List<String>>

    @Query("SELECT * FROM questions WHERE isDailyQuestion = 1")
    fun getDailyQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Long): QuestionEntity?

    @Query("SELECT * FROM questions WHERE id IN (:ids)")
    suspend fun getQuestionsByIds(ids: List<Long>): List<QuestionEntity>

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: QuestionEntity): Long

    @Query("SELECT * FROM questions WHERE isForGeneralTrack = 1 ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsForGeneral(limit: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE isForNirmanSahayak = 1 ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsForNirmanSahayak(limit: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE subject = :subjectName ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsForSubject(subjectName: String, limit: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE topic IN (:topics) ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsForTopics(topics: List<String>, limit: Int): List<QuestionEntity>
}

@Dao
interface QuizAttemptDao {
    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttemptEntity>>

    @Query("SELECT * FROM quiz_attempts WHERE track = :track ORDER BY timestamp DESC")
    fun getAttemptsByTrack(track: String): Flow<List<QuizAttemptEntity>>

    @Query("SELECT * FROM quiz_attempts WHERE testType = :testType ORDER BY timestamp DESC")
    fun getAttemptsByType(testType: String): Flow<List<QuizAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttemptEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswerRecords(records: List<AnswerRecordEntity>)

    @Query("SELECT * FROM answer_records ORDER BY timestamp DESC")
    fun getAllAnswerRecords(): Flow<List<AnswerRecordEntity>>

    @Query("SELECT * FROM answer_records WHERE isCorrect = 0 ORDER BY timestamp DESC")
    fun getWrongAnswerRecords(): Flow<List<AnswerRecordEntity>>

    @Query("DELETE FROM quiz_attempts")
    suspend fun clearHistory()
}

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE questionId = :questionId)")
    fun isBookmarked(questionId: Long): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE questionId = :questionId")
    suspend fun deleteBookmark(questionId: Long)
}
