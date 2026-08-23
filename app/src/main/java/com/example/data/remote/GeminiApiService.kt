package com.example.data.remote

import android.graphics.Bitmap
import android.util.Base64
import com.example.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

// Moshi data models for Gemini REST API

@JsonClass(generateAdapter = true)
data class GeminiRequest(
    val contents: List<GeminiContent>,
    val generationConfig: GeminiGenerationConfig? = null,
    val tools: List<GeminiTool>? = null,
    val systemInstruction: GeminiContent? = null
)

@JsonClass(generateAdapter = true)
data class GeminiContent(
    val role: String? = "user",
    val parts: List<GeminiPart>
)

@JsonClass(generateAdapter = true)
data class GeminiPart(
    val text: String? = null,
    val inlineData: GeminiInlineData? = null
)

@JsonClass(generateAdapter = true)
data class GeminiInlineData(
    val mimeType: String,
    val data: String
)

@JsonClass(generateAdapter = true)
data class GeminiGenerationConfig(
    val temperature: Float? = null,
    val topP: Float? = null,
    val thinkingConfig: GeminiThinkingConfig? = null
)

@JsonClass(generateAdapter = true)
data class GeminiThinkingConfig(
    val thinkingLevel: String? = "HIGH"
)

@JsonClass(generateAdapter = true)
data class GeminiTool(
    val googleSearch: Map<String, String>? = null
)

@JsonClass(generateAdapter = true)
data class GeminiResponse(
    val candidates: List<GeminiCandidate>? = null
)

@JsonClass(generateAdapter = true)
data class GeminiCandidate(
    val content: GeminiResponseContent? = null,
    val groundingMetadata: GeminiGroundingMetadata? = null
)

@JsonClass(generateAdapter = true)
data class GeminiResponseContent(
    val parts: List<GeminiResponsePart>? = null
)

@JsonClass(generateAdapter = true)
data class GeminiResponsePart(
    val text: String? = null
)

@JsonClass(generateAdapter = true)
data class GeminiGroundingMetadata(
    val webSearchQueries: List<String>? = null,
    val searchChunks: List<Map<String, Any>>? = null
)

interface GeminiApi {
    @POST("v1beta/models/{model}:generateContent")
    suspend fun generateContent(
        @Path("model") model: String,
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object GeminiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: GeminiApi = retrofit.create(GeminiApi::class.java)

    /**
     * Ask Gemini AI Tutor
     * @param prompt Question or doubt from user
     * @param useSearchGrounding Whether to enable Google Search grounding with gemini-3.5-flash
     * @param useHighThinking Whether to enable High Thinking mode with gemini-3.1-pro-preview
     * @param imageBitmap Optional attached diagram / question screenshot for multimodal reasoning
     */
    suspend fun askTutor(
        prompt: String,
        useSearchGrounding: Boolean = false,
        useHighThinking: Boolean = false,
        imageBitmap: Bitmap? = null
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            val apiKey = try {
                BuildConfig.GEMINI_API_KEY
            } catch (e: Exception) {
                ""
            }

            if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
                return@withContext Result.failure(
                    Exception("Gemini API key is not configured yet. Please configure GEMINI_API_KEY in the Secrets panel in AI Studio.")
                )
            }

            val parts = mutableListOf<GeminiPart>()
            if (imageBitmap != null) {
                val outputStream = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
                val base64 = Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
                parts.add(GeminiPart(inlineData = GeminiInlineData(mimeType = "image/jpeg", data = base64)))
            }
            parts.add(GeminiPart(text = prompt))

            val systemPrompt = """
                You are 'Panchayat AI Master', an elite examination tutor specializing in West Bengal Panchayat recruitment exams (Executive Assistant, Nirman Sahayak, Gram Panchayat Karmee, Sahayak, Secretary).
                Syllabus covered:
                1. Bengali Language (Madhyamik Standard: Sandhi, Samas, Karak, Kriya, Upasarga, Vocabulary, Idioms, Spelling, Literature).
                2. English Language (Tenses, Prepositions, Subject-Verb Agreement, Voice/Narration, Idioms, Errors).
                3. Arithmetic & Math (BODMAS, Percentage, Profit & Loss, SI/CI, Ratio, Time & Work, Speed, Ages).
                4. General Knowledge & Rural Development (73rd Amendment, PRI 3-tier, Gram Sabha, Lakshmir Bhandar, Kanyashree, Krishak Bandhu, Swasthya Sathi, MGNREGA, PMAY-G, PMGSY, WB Static GK & Current Affairs).
                5. Nirman Sahayak Civil Engineering (Building Materials, Surveying, RCC/PCC IS 456, Estimating, Hydraulics, Roads).

                Provide crystal-clear, structured, bilingual (Bengali + English) explanations with step-by-step shortcuts, rules, and exam tips.
            """.trimIndent()

            val tools = if (useSearchGrounding) {
                listOf(GeminiTool(googleSearch = emptyMap()))
            } else null

            val thinkingConfig = if (useHighThinking) {
                GeminiThinkingConfig(thinkingLevel = "HIGH")
            } else null

            val generationConfig = if (thinkingConfig != null) {
                GeminiGenerationConfig(thinkingConfig = thinkingConfig)
            } else {
                GeminiGenerationConfig(temperature = 0.4f)
            }

            // Model Selection as per instructions:
            // - If high thinking or image analysis -> gemini-3.1-pro-preview
            // - If search grounding or general -> gemini-3.5-flash
            val model = when {
                useHighThinking || imageBitmap != null -> "gemini-3.1-pro-preview"
                else -> "gemini-3.5-flash"
            }

            val request = GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        role = "user",
                        parts = parts
                    )
                ),
                systemInstruction = GeminiContent(
                    role = "user",
                    parts = listOf(GeminiPart(text = systemPrompt))
                ),
                tools = tools,
                generationConfig = generationConfig
            )

            val response = api.generateContent(model = model, apiKey = apiKey, request = request)
            val text = response.candidates?.firstOrNull()?.content?.parts?.mapNotNull { it.text }?.joinToString("\n")

            if (text.isNullOrBlank()) {
                Result.failure(Exception("No explanation received from Gemini."))
            } else {
                Result.success(text)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
