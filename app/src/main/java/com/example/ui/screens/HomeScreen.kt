package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.ExamSubject
import com.example.data.models.ExamTrack
import com.example.data.models.QuestionEntity
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedTrack by viewModel.selectedTrack.collectAsState()
    val isBengali by viewModel.isBengaliLanguage.collectAsState()
    val dailyQuestion by viewModel.dailyQuestion.collectAsState()
    val dailySelected by viewModel.dailyQuestionSelected.collectAsState()
    val gapAnalysis by viewModel.gapAnalysis.collectAsState()
    val mistakeList by viewModel.mistakeQuestionList.collectAsState()
    val bookmarkList by viewModel.bookmarkedQuestionList.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Mock Test & Monthly Review Banners
        item {
            HeroExamBanners(
                isBengali = isBengali,
                selectedTrack = selectedTrack,
                onStartWeeklyMock = { viewModel.startWeeklyMockTest() },
                onStartMonthlyReview = { viewModel.startMonthlyComprehensiveReview() }
            )
        }

        // Daily Question of the Day
        item {
            DailyQuestionCard(
                question = dailyQuestion,
                selectedOption = dailySelected,
                isBengali = isBengali,
                onSelectOption = { viewModel.answerDailyQuestion(it) },
                onAskAi = { q -> viewModel.askAiAboutQuestion(q) }
            )
        }

        // Quick Stats & Gap Summary
        item {
            StatsAndGapsRow(
                overallAccuracy = gapAnalysis?.overallAccuracy ?: 0.0,
                totalTests = gapAnalysis?.totalAttemptedTests ?: 0,
                mistakeCount = mistakeList.size,
                bookmarkCount = bookmarkList.size,
                weakTopicsCount = gapAnalysis?.weakTopics?.size ?: 0,
                onOpenGapAnalysis = { viewModel.navigateTo(AppScreen.GAP_ANALYSIS) },
                onOpenMistakes = { viewModel.navigateTo(AppScreen.MISTAKE_BOOK) },
                onOpenBookmarks = { viewModel.navigateTo(AppScreen.BOOKMARKS) }
            )
        }

        // Syllabus Subjects Section Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (isBengali) "পাঠ্যক্রম ও অধ্যায়ভিত্তিক অনুশীলন" else "Syllabus & Subject Practice",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = if (selectedTrack == ExamTrack.GENERAL) "Bengali (25) + English (25) + Math (25) + GK (15)" else "Civil Engg (65) + English (13) + GK (7)",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }
        }

        // Subject Cards
        item {
            val subjectsToDisplay = if (selectedTrack == ExamTrack.GENERAL) {
                listOf(
                    ExamSubject.BENGALI,
                    ExamSubject.ENGLISH,
                    ExamSubject.ARITHMETIC,
                    ExamSubject.GK_RURAL_DEV
                )
            } else {
                listOf(
                    ExamSubject.CIVIL_ENGINEERING,
                    ExamSubject.ENGLISH,
                    ExamSubject.GK_RURAL_DEV
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                subjectsToDisplay.forEach { subject ->
                    val marksText = when (subject) {
                        ExamSubject.BENGALI -> if (selectedTrack == ExamTrack.GENERAL) "25 Marks / Madhyamik" else "Optional"
                        ExamSubject.ENGLISH -> if (selectedTrack == ExamTrack.GENERAL) "25 Marks / Madhyamik" else "13 Marks"
                        ExamSubject.ARITHMETIC -> "20–25 Marks / Class 8–10"
                        ExamSubject.GK_RURAL_DEV -> if (selectedTrack == ExamTrack.GENERAL) "15 Marks (PRI & Schemes)" else "7 Marks (PRI & WB GK)"
                        ExamSubject.CIVIL_ENGINEERING -> "65 Marks / Diploma Standard"
                    }

                    val subtopicsText = when (subject) {
                        ExamSubject.BENGALI -> "Sandhi, Samas, Karak, Kriya, Idioms, Spelling, Literature"
                        ExamSubject.ENGLISH -> "Tenses, Prepositions, Voice, Narration, Idioms, Errors"
                        ExamSubject.ARITHMETIC -> "Percentage, Profit/Loss, SI/CI, Ratio, Time & Work, Speed"
                        ExamSubject.GK_RURAL_DEV -> "73rd Amendment, Lakshmir Bhandar, Krishak Bandhu, WB GK"
                        ExamSubject.CIVIL_ENGINEERING -> "Building Materials, Surveying, RCC (IS 456), Estimating, Roads"
                    }

                    val icon = when (subject) {
                        ExamSubject.BENGALI -> Icons.Default.Translate
                        ExamSubject.ENGLISH -> Icons.Default.MenuBook
                        ExamSubject.ARITHMETIC -> Icons.Default.Calculate
                        ExamSubject.GK_RURAL_DEV -> Icons.Default.AccountBalance
                        ExamSubject.CIVIL_ENGINEERING -> Icons.Default.Engineering
                    }

                    SubjectItemCard(
                        subject = subject,
                        marksText = marksText,
                        subtopicsText = subtopicsText,
                        icon = icon,
                        isBengali = isBengali,
                        onPracticeClick = { viewModel.openPracticeForSubject(subject) },
                        onDrillClick = { viewModel.startSubjectDrill(subject) }
                    )
                }
            }
        }
    }
}

@Composable
fun HeroExamBanners(
    isBengali: Boolean,
    selectedTrack: ExamTrack,
    onStartWeeklyMock: () -> Unit,
    onStartMonthlyReview: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Weekly Mock Banner
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("weekly_mock_card")
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                Color(0xFF1E3E62),
                                Color(0xFF0F3460)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "WEEKLY SERIES",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "⏱️ -0.25 Negative Marking",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White.copy(alpha = 0.85f),
                                    fontSize = 10.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (isBengali) "সাপ্তাহিক পূর্ণাঙ্গ মক টেস্ট" else "Weekly Full-Length Mock Test",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )

                        Text(
                            text = if (selectedTrack == ExamTrack.GENERAL) "20 MCQs • Bengali, English, Math & Rural GK" else "20 MCQs • Civil Engineering Diploma + Eng/GK",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = onStartWeeklyMock,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("start_weekly_mock_btn")
                    ) {
                        Text("Start Test", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Monthly Review Banner
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("monthly_review_card")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Insights,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (isBengali) "মাসিক সামগ্রিক পর্যালোচনা" else "Monthly Comprehensive Review",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Text(
                            text = "Tracks multi-topic learning gaps & progress",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                fontSize = 11.sp
                            )
                        )
                    }
                }

                OutlinedButton(
                    onClick = onStartMonthlyReview,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("start_monthly_review_btn")
                ) {
                    Text("Take Review", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
fun DailyQuestionCard(
    question: QuestionEntity?,
    selectedOption: Int?,
    isBengali: Boolean,
    onSelectOption: (Int) -> Unit,
    onAskAi: (QuestionEntity) -> Unit
) {
    if (question == null) return

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("daily_question_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isBengali) "আজকের প্রশ্ন (Question of the Day)" else "Daily Practice Challenge",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = question.topic,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = question.questionText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            val options = listOf(question.optionA, question.optionB, question.optionC, question.optionD)

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEachIndexed { index, optionText ->
                    val isSelected = selectedOption == index
                    val isRevealed = selectedOption != null
                    val isCorrect = index == question.correctAnswerIndex

                    val borderColor = when {
                        !isRevealed -> if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                        isCorrect -> SuccessGreen
                        isSelected -> ErrorRed
                        else -> MaterialTheme.colorScheme.outlineVariant
                    }

                    val bgColor = when {
                        !isRevealed -> if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f) else Color.Transparent
                        isCorrect -> SuccessGreen.copy(alpha = 0.12f)
                        isSelected -> ErrorRed.copy(alpha = 0.12f)
                        else -> Color.Transparent
                    }

                    Surface(
                        color = bgColor,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                            .clickable(enabled = selectedOption == null) {
                                onSelectOption(index)
                            }
                            .testTag("daily_option_$index")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            isRevealed && isCorrect -> SuccessGreen
                                            isRevealed && isSelected -> ErrorRed
                                            isSelected -> MaterialTheme.colorScheme.primary
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A' + index).toString(),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected || (isRevealed && isCorrect)) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected || (isRevealed && isCorrect)) FontWeight.SemiBold else FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            if (isRevealed && isCorrect) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                            } else if (isRevealed && isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Cancel,
                                    contentDescription = "Wrong",
                                    tint = ErrorRed,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (selectedOption != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "💡 Explanation & Tips:",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            TextButton(
                                onClick = { onAskAi(question) },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                modifier = Modifier.testTag("ask_ai_daily_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "Ask AI Tutor",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                        Text(
                            text = question.explanation,
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatsAndGapsRow(
    overallAccuracy: Double,
    totalTests: Int,
    mistakeCount: Int,
    bookmarkCount: Int,
    weakTopicsCount: Int,
    onOpenGapAnalysis: () -> Unit,
    onOpenMistakes: () -> Unit,
    onOpenBookmarks: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Gap Analysis Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onOpenGapAnalysis)
                .testTag("gap_stat_card")
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.TrackChanges,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "${String.format("%.0f", overallAccuracy)}%",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (overallAccuracy >= 70) SuccessGreen else MaterialTheme.colorScheme.primary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Accuracy & Gaps",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = if (weakTopicsCount > 0) "$weakTopicsCount weak topics" else "$totalTests tests taken",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = if (weakTopicsCount > 0) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        // Mistakes Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onOpenMistakes)
                .testTag("mistakes_stat_card")
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.HistoryEdu,
                        contentDescription = null,
                        tint = ErrorRed,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "$mistakeCount",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = ErrorRed
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Mistakes Book",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Revision drills",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        // Bookmarks Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onOpenBookmarks)
                .testTag("bookmarks_stat_card")
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "$bookmarkCount",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Saved Notes",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Bookmarks",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@Composable
fun SubjectItemCard(
    subject: ExamSubject,
    marksText: String,
    subtopicsText: String,
    icon: ImageVector,
    isBengali: Boolean,
    onPracticeClick: () -> Unit,
    onDrillClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onPracticeClick)
            .testTag("subject_card_${subject.name}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isBengali) subject.displayNameBangla else subject.displayName,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Surface(
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = marksText,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            ),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtopicsText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.75f),
                        fontSize = 11.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledTonalButton(
                        onClick = onPracticeClick,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(28.dp)
                    ) {
                        Text("Practice Topics", style = MaterialTheme.typography.labelSmall)
                    }

                    OutlinedButton(
                        onClick = onDrillClick,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(28.dp)
                    ) {
                        Text("15Q Drill", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}
