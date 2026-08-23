package com.example.data.models

enum class ExamSubject(val displayName: String, val displayNameBangla: String, val defaultMarks: Int) {
    BENGALI("Bengali Language", "বাংলা ভাষা ও ব্যাকরণ", 25),
    ENGLISH("English Language", "ইংরেজি ভাষা", 25),
    ARITHMETIC("Arithmetic & Math", "পাটিগণিত ও গণিত", 25),
    GK_RURAL_DEV("GK & Rural Development", "সাধারণ জ্ঞান ও গ্রামীণ উন্নয়ন", 15),
    CIVIL_ENGINEERING("Civil Engineering (Tech)", "সিভিল ইঞ্জিনিয়ারিং (ডিপ্লোমা)", 65)
}

enum class ExamTrack(val title: String, val titleBangla: String, val subtitle: String, val totalMarks: Int) {
    GENERAL(
        "General Panchayat Cadre",
        "সাধারণ পঞ্চায়েত ক্যাডার",
        "Executive Assistant, GP Karmee, Sahayak & Secretary",
        90
    ),
    NIRMAN_SAHAYAK(
        "Nirman Sahayak Track",
        "নির্মাণ সহায়ক (টেকনিক্যাল)",
        "Civil Engg (65M) + English (13M) + GK (7M)",
        85
    )
}

enum class TestType(val label: String, val labelBangla: String) {
    DAILY_PRACTICE("Daily Practice", "দৈনিক অনুশীলন"),
    TOPIC_DRILL("Topic Drill", "অধ্যায়ভিত্তিক ড্রিল"),
    WEEKLY_MOCK("Weekly Mock Test", "সাপ্তাহিক মক টেস্ট"),
    MONTHLY_REVIEW("Monthly Comprehensive Review", "মাসিক সামগ্রিক পর্যালোচনা"),
    GAP_RECOVERY("Targeted Gap Recovery", "দুর্বলতা নিরাময় পরীক্ষা"),
    AI_GENERATED("AI Smart Test", "এআই স্মার্ট টেস্ট")
}
