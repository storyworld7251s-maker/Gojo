package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.ExamSubject
import com.example.data.models.ExamTrack
import com.example.ui.AppScreen
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopHeader(
    currentScreen: AppScreen,
    selectedTrack: ExamTrack,
    isBengali: Boolean,
    onTrackToggle: () -> Unit,
    onLanguageToggle: () -> Unit,
    onBackClick: () -> Unit,
    onOpenMistakes: () -> Unit,
    onOpenBookmarks: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (currentScreen != AppScreen.HOME) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.testTag("back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = "Panchayat Prep Logo",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Column {
                        Text(
                            text = if (isBengali) "পশ্চিমবঙ্গ পঞ্চায়েত প্রস্তুতি" else "WB Panchayat Prep",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (selectedTrack == ExamTrack.GENERAL) "Executive Asst / Sahayak / Karmee" else "Nirman Sahayak (Civil Engg)",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Language Switcher Button
                    FilledTonalButton(
                        onClick = onLanguageToggle,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("language_toggle")
                    ) {
                        Text(
                            text = if (isBengali) "বাংলা" else "ENG",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Bookmark icon
                    IconButton(
                        onClick = onOpenBookmarks,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("bookmarks_nav_button")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = "Bookmarks",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            if (currentScreen == AppScreen.HOME) {
                Spacer(modifier = Modifier.height(8.dp))
                // Track Selector Segmented Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TrackTabItem(
                        title = if (isBengali) "সাধারণ পঞ্চায়েত (৯০ নম্বর)" else "General Cadre (90M)",
                        subtitle = "EA / Sahayak / Karmee",
                        isSelected = selectedTrack == ExamTrack.GENERAL,
                        modifier = Modifier.weight(1f),
                        onClick = { if (selectedTrack != ExamTrack.GENERAL) onTrackToggle() }
                    )
                    TrackTabItem(
                        title = if (isBengali) "নির্মাণ সহায়ক (৮৫ নম্বর)" else "Nirman Sahayak (85M)",
                        subtitle = "Civil Engineering",
                        isSelected = selectedTrack == ExamTrack.NIRMAN_SAHAYAK,
                        modifier = Modifier.weight(1f),
                        onClick = { if (selectedTrack != ExamTrack.NIRMAN_SAHAYAK) onTrackToggle() }
                    )
                }
            }
        }
    }
}

@Composable
fun TrackTabItem(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clickable(onClick = onClick)
            .testTag("track_tab_${if (isSelected) "active" else "inactive"}")
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    color = if (isSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun AppBottomNavigation(
    currentScreen: AppScreen,
    onNavigate: (AppScreen) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        NavigationBarItem(
            selected = currentScreen == AppScreen.HOME,
            onClick = { onNavigate(AppScreen.HOME) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.HOME) Icons.Filled.Dashboard else Icons.Outlined.Dashboard,
                    contentDescription = "Dashboard"
                )
            },
            label = { Text("Dashboard", style = MaterialTheme.typography.labelSmall) },
            modifier = Modifier.testTag("nav_dashboard")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.PRACTICE_HUB,
            onClick = { onNavigate(AppScreen.PRACTICE_HUB) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.PRACTICE_HUB) Icons.AutoMirrored.Filled.MenuBook else Icons.Outlined.MenuBook,
                    contentDescription = "Practice"
                )
            },
            label = { Text("Practice", style = MaterialTheme.typography.labelSmall) },
            modifier = Modifier.testTag("nav_practice")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.GAP_ANALYSIS,
            onClick = { onNavigate(AppScreen.GAP_ANALYSIS) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.GAP_ANALYSIS) Icons.Filled.Analytics else Icons.Outlined.Analytics,
                    contentDescription = "Gap Review"
                )
            },
            label = { Text("Gap Review", style = MaterialTheme.typography.labelSmall) },
            modifier = Modifier.testTag("nav_gap_analysis")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.AI_TUTOR,
            onClick = { onNavigate(AppScreen.AI_TUTOR) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.AI_TUTOR) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome,
                    contentDescription = "AI Tutor"
                )
            },
            label = { Text("AI Tutor", style = MaterialTheme.typography.labelSmall) },
            modifier = Modifier.testTag("nav_ai_tutor")
        )
    }
}
