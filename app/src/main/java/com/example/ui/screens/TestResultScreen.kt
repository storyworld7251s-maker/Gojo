package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.SuccessGreen

@Composable
fun TestResultScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val resultData by viewModel.latestTestResult.collectAsState()
    val isBengali by viewModel.isBengaliLanguage.collectAsState()

    if (resultData == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No test result available")
        }
        return
    }

    val res = resultData!!
    val accuracy = if (res.totalQuestions > 0) (res.correctCount.toDouble() / res.totalQuestions) * 100.0 else 0.0

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Scorecard Header Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("result_scorecard")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = res.testTitle,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White.copy(alpha = 0.9f),
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = String.format("%.2f", res.score),
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "out of ${String.format("%.0f", res.totalMarks)} Marks",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(alpha = 0.8f))
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = when {
                                accuracy >= 80 -> "🏆 Outstanding Preparation! High Chance of Selection"
                                accuracy >= 60 -> "🎯 Good Score! Review weak areas to ensure top rank"
                                else -> "⚠️ Needs Practice: Identify gaps and use AI Tutor"
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Metrics Grid (Correct, Wrong, Unattempted, Accuracy)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ResultMetricCard(
                    title = "Correct",
                    value = "${res.correctCount}",
                    color = SuccessGreen,
                    modifier = Modifier.weight(1f)
                )
                ResultMetricCard(
                    title = "Wrong (-0.25)",
                    value = "${res.wrongCount}",
                    color = ErrorRed,
                    modifier = Modifier.weight(1f)
                )
                ResultMetricCard(
                    title = "Skipped",
                    value = "${res.unattemptedCount}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                ResultMetricCard(
                    title = "Accuracy",
                    value = "${String.format("%.0f", accuracy)}%",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Navigation Action Buttons
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { viewModel.navigateTo(AppScreen.GAP_ANALYSIS) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("view_gap_analysis_from_result_btn")
                ) {
                    Icon(Icons.Default.Analytics, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("View Gap Analysis")
                }

                OutlinedButton(
                    onClick = { viewModel.navigateTo(AppScreen.HOME) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Dashboard")
                }
            }
        }

        // Detailed Question Review Header
        item {
            Text(
                text = "Detailed Solutions & AI Review",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

        // Review items
        itemsIndexed(res.questions) { index, question ->
            val userSelected = res.userAnswers[index] ?: -1
            val isCorrect = userSelected == question.correctAnswerIndex
            val isUnattempted = userSelected == -1

            val options = listOf(question.optionA, question.optionB, question.optionC, question.optionD)

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = when {
                                isCorrect -> SuccessGreen.copy(alpha = 0.15f)
                                isUnattempted -> MaterialTheme.colorScheme.surfaceVariant
                                else -> ErrorRed.copy(alpha = 0.15f)
                            },
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = when {
                                    isCorrect -> "✓ Correct (+1.0)"
                                    isUnattempted -> "— Skipped (0.0)"
                                    else -> "✗ Wrong (-0.25)"
                                },
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = when {
                                        isCorrect -> SuccessGreen
                                        isUnattempted -> MaterialTheme.colorScheme.onSurfaceVariant
                                        else -> ErrorRed
                                    }
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = question.topic,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 10.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Q${index + 1}. ${question.getDisplayText(isBengali)}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    options.forEachIndexed { optIdx, optText ->
                        val isThisCorrect = optIdx == question.correctAnswerIndex
                        val isThisUserChoice = optIdx == userSelected

                        val bgColor = when {
                            isThisCorrect -> SuccessGreen.copy(alpha = 0.12f)
                            isThisUserChoice -> ErrorRed.copy(alpha = 0.12f)
                            else -> Color.Transparent
                        }

                        Surface(
                            color = bgColor,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + optIdx)}. $optText",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = if (isThisCorrect || isThisUserChoice) FontWeight.Bold else FontWeight.Normal,
                                        color = when {
                                            isThisCorrect -> SuccessGreen
                                            isThisUserChoice -> ErrorRed
                                            else -> MaterialTheme.colorScheme.onSurface
                                        }
                                    ),
                                    modifier = Modifier.weight(1f)
                                )

                                if (isThisCorrect) {
                                    Text("(Correct)", style = MaterialTheme.typography.labelSmall.copy(color = SuccessGreen, fontWeight = FontWeight.Bold))
                                } else if (isThisUserChoice) {
                                    Text("(Your Choice)", style = MaterialTheme.typography.labelSmall.copy(color = ErrorRed, fontWeight = FontWeight.Bold))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "💡 Explanation:",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                TextButton(
                                    onClick = { viewModel.askAiAboutQuestion(question) },
                                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                                    modifier = Modifier.height(24.dp)
                                ) {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(12.dp), tint = MaterialTheme.colorScheme.secondary)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Ask AI Tutor", style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold))
                                }
                            }
                            Text(
                                text = question.getDisplayExplanation(isBengali),
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultMetricCard(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(10.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 1
            )
        }
    }
}
