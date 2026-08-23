package com.example.data.local

import com.example.data.models.ExamSubject
import com.example.data.models.QuestionEntity

object InitialQuestionBank {
    fun getCuratedQuestions(): List<QuestionEntity> {
        val list = mutableListOf<QuestionEntity>()

        // ==========================================
        // 1. BENGALI LANGUAGE (বাংলা ভাষা ও ব্যাকরণ)
        // ==========================================

        // Sandhi
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Sandhi (সন্ধি)",
                topicBangla = "সন্ধি বিচ্ছেদ ও গঠন",
                questionText = "'বিদ্যালয়' শব্দটির সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                questionTextBangla = "'বিদ্যালয়' শব্দটির সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                optionA = "বিদ্যা + আলয়",
                optionB = "বিদ্য + আলয়",
                optionC = "বিদ্যা + লয়",
                optionD = "বিদ + আলয়",
                correctAnswerIndex = 0,
                explanation = "বিদ্যালয় = বিদ্যা + আলয় (স্বরসন্ধির নিয়ম: আ + আ = আ)।",
                explanationBangla = "স্বরসন্ধির সূত্রানুসারে 'আ' কারের পর 'আ' কার থাকলে উভয় মিলে দীর্ঘ 'আ' কার হয়।",
                isDailyQuestion = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Sandhi (সন্ধি)",
                topicBangla = "সন্ধি বিচ্ছেদ ও গঠন",
                questionText = "'গায়ক' শব্দের সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                questionTextBangla = "'গায়ক' শব্দের সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                optionA = "গা + অক",
                optionB = "গৈ + অক",
                optionC = "গে + অক",
                optionD = "গো + অক",
                correctAnswerIndex = 1,
                explanation = "গায়ক = গৈ + অক (ঐ + অ = আয়্ + অ)।",
                explanationBangla = "ঐ-কারের পর স্বরবর্ণ থাকলে ঐ-স্থানে 'আয়্' হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Sandhi (সন্ধি)",
                topicBangla = "সন্ধি বিচ্ছেদ ও গঠন",
                questionText = "'পদ্ধতি' শব্দটির সন্ধি বিচ্ছেদ কোনটি?",
                questionTextBangla = "'পদ্ধতি' শব্দটির সন্ধি বিচ্ছেদ কোনটি?",
                optionA = "পদ + ধতি",
                optionB = "পদ্ + হতি",
                optionC = "পৎ + হতি",
                optionD = "পদ + হতি",
                correctAnswerIndex = 2,
                explanation = "ব্যঞ্জনসন্ধির নিয়ম অনুযায়ী: পদ্/পৎ + হতি = পদ্ধতি (ত্/দ্ + হ = দ্ধ)।",
                explanationBangla = "ত্ বা দ্-এর পর হ থাকলে ত্/দ্ স্থানে দ্ এবং হ স্থানে ধ্ হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Sandhi (সন্ধি)",
                topicBangla = "সন্ধি বিচ্ছেদ ও গঠন",
                questionText = "'ষড়ানন' শব্দের সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                questionTextBangla = "'ষড়ানন' শব্দের সঠিক সন্ধি বিচ্ছেদ কোনটি?",
                optionA = "ষড় + আনন",
                optionB = "ষট্ + আনন",
                optionC = "ষড + আনন",
                optionD = "ষষ্ + আনন",
                correctAnswerIndex = 1,
                explanation = "ষট্ + আনন = ষড়ানন (ট্-এর পর স্বরবর্ণ থাকলে ট্ স্থানে ড্/ড়্ হয়)।",
                explanationBangla = "ব্যঞ্জনসন্ধির বর্গের প্রথম বর্ণ বর্গের তৃতীয় বর্ণে রূপান্তরিত হয়।"
            )
        )

        // Samas
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Samas (সমাস)",
                topicBangla = "সমাস নির্ণয় ও ব্যাসবাক্য",
                questionText = "'পীতাম্বর' কোন সমাসের উদাহরণ?",
                questionTextBangla = "'পীতাম্বর' কোন সমাসের উদাহরণ?",
                optionA = "কর্মধারয় সমাস",
                optionB = "বহুব্রীহি সমাস",
                optionC = "তৎপুরুষ সমাস",
                optionD = "দ্বিগু সমাস",
                correctAnswerIndex = 1,
                explanation = "পীতাম্বর = পীত অম্বর যার (শ্রীকৃষ্ণ/বিষ্ণু)। পূর্বপদ বা পরপদের অর্থ প্রধান না হয়ে অন্য কোনো তৃতীয় ব্যক্তিকে বোঝালে বহুব্রীহি সমাস হয়।",
                explanationBangla = "ব্যাসবাক্য: পীত (হলুদ) অম্বর (বস্ত্র) যার = পীতাম্বর (বহুব্রীহি সমাস)।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Samas (সমাস)",
                topicBangla = "সমাস নির্ণয় ও ব্যাসবাক্য",
                questionText = "'হাতাহাতি' কোন প্রকারের বহুব্রীহি সমাস?",
                questionTextBangla = "'হাতাহাতি' কোন প্রকারের বহুব্রীহি সমাস?",
                optionA = "সমানাধিকরণ বহুব্রীহি",
                optionB = "ব্যতিহার বহুব্রীহি",
                optionC = "মধ্যপদলোপী বহুব্রীহি",
                optionD = "নঞর্থক বহুব্রীহি",
                correctAnswerIndex = 1,
                explanation = "হাতে হাতে যে যুদ্ধ = হাতাহাতি। পরস্পর একই ক্রিয়ার পুনরাবৃত্তি বোঝালে তাকে ব্যতিহার বহুব্রীহি সমাস বলে।",
                explanationBangla = "ব্যতিহার বহুব্রীহি ক্রিয়ার পারস্পরিক প্রতিক্রিয়া নির্দেশ করে (যেমন: লাঠালাঠি, কোলাকুলি)।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Samas (সমাস)",
                topicBangla = "সমাস নির্ণয় ও ব্যাসবাক্য",
                questionText = "'গাছে-পাকা' কোন সমাসের উদাহরণ?",
                questionTextBangla = "'গাছে-পাকা' কোন সমাসের উদাহরণ?",
                optionA = "তৃতীয়া তৎপুরুষ",
                optionB = "সপ্তমী তৎপুরুষ",
                optionC = "উপপদ তৎপুরুষ",
                optionD = "অলুক তৎপুরুষ",
                correctAnswerIndex = 1,
                explanation = "গাছে পাকা = গাছে পাকা (পূর্বপদের সপ্তমী বিভক্তি 'এ' লোপ পেয়ে বা যুক্ত থেকে তৎপুরুষ সমাস হয়েছে)।",
                explanationBangla = "পূর্বপদে সপ্তমী বিভক্তি (এ, য়, তে) থাকলে সপ্তমী তৎপুরুষ হয়।"
            )
        )

        // Karak & Bibhakti
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Karak & Bibhakti (কারক ও বিভক্তি)",
                topicBangla = "কারক ও বিভক্তি নির্ণয়",
                questionText = "'<u>ঘোড়ায়</u> গাড়ি টানে' — রেখাঙ্কিত পদটি কোন কারকে কোন বিভক্তি?",
                questionTextBangla = "'<u>ঘোড়ায়</u> গাড়ি টানে' — রেখাঙ্কিত পদটি কোন কারকে কোন বিভক্তি?",
                optionA = "কর্তৃকারকে 'য়' (বা 'এ') বিভক্তি",
                optionB = "কর্মকারকে 'য়' বিভক্তি",
                optionC = "করণকারকে 'য়' বিভক্তি",
                optionD = "অপাদান কারকে 'য়' বিভক্তি",
                correctAnswerIndex = 0,
                explanation = "যে ক্রিয়া সম্পন্ন করে সে কর্তা। এখানে ঘোড়া নিজেই গাড়ি টানার কাজ করছে, তাই ঘোড়ায় = কর্তৃকারকে 'য়' বিভক্তি।",
                explanationBangla = "ক্রিয়াকে 'কে' দিয়ে প্রশ্ন করলে কর্তৃকারক পাওয়া যায়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Karak & Bibhakti (কারক ও বিভক্তি)",
                topicBangla = "কারক ও বিভক্তি নির্ণয়",
                questionText = "'<u>মেঘ থেকে</u> বৃষ্টি পড়ে' — এটি কোন কারক?",
                questionTextBangla = "'<u>মেঘ থেকে</u> বৃষ্টি পড়ে' — এটি কোন কারক?",
                optionA = "অধিকরণ কারক",
                optionB = "অপাদান কারক",
                optionC = "করণ কারক",
                optionD = "কর্ম কারক",
                correctAnswerIndex = 1,
                explanation = "যা থেকে কিছু বিচ্যুত, জাত বা পতিত হয় তাকে অপাদান কারক বলে।",
                explanationBangla = "হতে/থেকে অনুসর্গ অপাদান কারকে ব্যবহৃত হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Karak & Bibhakti (কারক ও বিভক্তি)",
                topicBangla = "কারক ও বিভক্তি নির্ণয়",
                questionText = "'তিনি <u>কলমে</u> লেখেন' — 'কলমে' কোন কারকে বিভক্তি?",
                questionTextBangla = "'তিনি <u>কলমে</u> লেখেন' — 'কলমে' কোন কারকে বিভক্তি?",
                optionA = "কর্তৃকারকে 'এ'",
                optionB = "করণকারকে 'এ' বিভক্তি",
                optionC = "অধিকরণ কারকে 'এ'",
                optionD = "কর্মকারকে 'এ'",
                correctAnswerIndex = 1,
                explanation = "যার দ্বারা ক্রিয়া নিষ্পন্ন হয় তাকে করণ কারক বলে (কলম লেখার সহায়ক উপাদান)।",
                explanationBangla = "করণ কারকে 'এ' বিভক্তি যুক্ত হয়েছে।"
            )
        )

        // Kriya, Upasarga & Pratyay
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Kriya (ক্রিয়াপদ)",
                topicBangla = "ক্রিয়াপদ ও ধাতু",
                questionText = "ক্রিয়াপদের মূল অবিভাজ্য অংশকে কী বলা হয়?",
                questionTextBangla = "ক্রিয়াপদের মূল অবিভাজ্য অংশকে কী বলা হয়?",
                optionA = "শব্দরূপ",
                optionB = "ধাতু বা ক্রিয়া-মূল",
                optionC = "অনুসর্গ",
                optionD = "উপসর্গ",
                correctAnswerIndex = 1,
                explanation = "ক্রিয়াপদকে বিশ্লেষণ করলে যে মূল মৌলিক অংশটি পাওয়া যায় তাকে ধাতু বলে।",
                explanationBangla = "ধাতুর সাথে বিভক্তি/প্রত্যয় যুক্ত হয়ে ক্রিয়াপদ গঠিত হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Upasarga & Pratyay (উপসর্গ ও প্রত্যয়)",
                topicBangla = "উপসর্গ ও প্রত্যয়",
                questionText = "'পঙ্কজ' শব্দের গঠন ও ব্যুৎপত্তি কোনটি?",
                questionTextBangla = "'পঙ্কজ' শব্দের গঠন ও ব্যুৎপত্তি কোনটি?",
                optionA = "পঙ্ক + অজ",
                optionB = "পঙ্কে জন্মে যা (পঙ্ক + জন্ + ড)",
                optionC = "পঙ্ক + জ্ + অল",
                optionD = "পঙ্ক + জ প্রত্যয়",
                correctAnswerIndex = 1,
                explanation = "পঙ্কজ = পঙ্কে জন্মে যা (পঙ্ক + জন্ ধাতু + ড প্রত্যয়)। এটি কৃদন্ত যোগরূঢ় শব্দ।",
                explanationBangla = "পঙ্কে যা জন্মায় তাই পঙ্কজ হলেও এর বিশেষ অর্থ পদ্মফুল।"
            )
        )

        // Vocabulary, Idioms, Spelling & Literature
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Idioms & Proverbs (বাগধারা)",
                topicBangla = "বাগধারা ও প্রবাদ-প্রবচন",
                questionText = "'ডুমুরের ফুল' বাগধারাটির সঠিক অর্থ কোনটি?",
                questionTextBangla = "'ডুমুরের ফুল' বাগধারাটির সঠিক অর্থ কোনটি?",
                optionA = "অতি মূল্যবান বস্তু",
                optionB = "অদৃশ্য বা দুর্লভ বস্তু",
                optionC = "সৌভাগ্যবান ব্যক্তি",
                optionD = "অসম্ভব পরিকল্পনা",
                correctAnswerIndex = 1,
                explanation = "'ডুমুরের ফুল' বাগধারার অর্থ দুর্লভ বা অপ্রাপ্য বস্তু যা সচরাচর চোখে পড়ে না।",
                explanationBangla = "বাক্য প্রয়োগ: চাকরির পর থেকে তার দেখা মেলাই ভার, যেন ডুমুরের ফুল হয়ে গেছে।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Synonyms & Antonyms (সমার্থক ও বিপরীত শব্দ)",
                topicBangla = "সমার্থক ও বিপরীতার্থক শব্দ",
                questionText = "'অর্বাচীন' শব্দটির সঠিক বিপরীতার্থক শব্দ কোনটি?",
                questionTextBangla = "'অর্বাচীন' শব্দটির সঠিক বিপরীতার্থক শব্দ কোনটি?",
                optionA = "নবীন",
                optionB = "প্রাচীন",
                optionC = "আধুনিক",
                optionD = "চিরন্তন",
                correctAnswerIndex = 1,
                explanation = "অর্বাচীন মানে আধুনিক বা স্বল্প বয়স্ক। এর বিপরীত শব্দ হলো 'প্রাচীন'।",
                explanationBangla = "অর্বাচীন (নবীন/আধুনিক) এর বিপরীত শব্দ প্রাচীন।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "One-Word Substitution (এক কথায় প্রকাশ)",
                topicBangla = "এক কথায় প্রকাশ",
                questionText = "'যা পূর্বে শোনা যায়নি' — এক কথায় প্রকাশ কী হবে?",
                questionTextBangla = "'যা পূর্বে শোনা যায়নি' — এক কথায় প্রকাশ কী হবে?",
                optionA = "অদৃষ্টপূর্ব",
                optionB = "অশ্রুতপূর্ব",
                optionC = "অনাস্বাদিতপূর্ব",
                optionD = "অভূতপূর্ব",
                correctAnswerIndex = 1,
                explanation = "যা পূর্বে শোনা যায়নি = অশ্রুতপূর্ব। (যা পূর্বে দেখা যায়নি = অদৃষ্টপূর্ব; যা পূর্বে ঘটেনি = অভূতপূর্ব)।",
                explanationBangla = "অশ্রুত (না শোনা) + পূর্ব = অশ্রুতপূর্ব।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Spelling Rectification (বানান শুদ্ধি)",
                topicBangla = "বানান শুদ্ধি ও বাক্য সংশোধন",
                questionText = "নিচের কোন বানানটি সম্পূর্ণ শুদ্ধ?",
                questionTextBangla = "নিচের কোন বানানটি সম্পূর্ণ শুদ্ধ?",
                optionA = "মরিচিকা",
                optionB = "মরুচিকা",
                optionC = "মরীচিকা",
                optionD = "মরীচীকা",
                correctAnswerIndex = 2,
                explanation = "শুদ্ধ বানান হলো 'মরীচিকা' (ম-র-দীর্ঘ ঈ-চ-হ্রস্ব ই-ক-আ কার)।",
                explanationBangla = "মরীচিকা (Mirage) বানানে প্রথমটিতে দীর্ঘ ঈ এবং দ্বিতীয়টিতে হ্রস্ব ই কার বসে।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Literature & Comprehension",
                topicBangla = "বাংলা সাহিত্য ও লেখক",
                questionText = "'পথের পাঁচালী' উপন্যাসের রচয়িতা কে?",
                questionTextBangla = "'পথের পাঁচালী' উপন্যাসের রচয়িতা কে?",
                optionA = "তারাশঙ্কর বন্দ্যোপাধ্যায়",
                optionB = "বিভূতিভূষণ বন্দ্যোপাধ্যায়",
                optionC = "মানিক বন্দ্যোপাধ্যায়",
                optionD = "শরৎচন্দ্র চট্টোপাধ্যায়",
                correctAnswerIndex = 1,
                explanation = "'পথের পাঁচালী' কালজয়ী উপন্যাসটি বিভূতিভূষণ বন্দ্যোপাধ্যায় ১৯২৯ সালে রচনা করেন।",
                explanationBangla = "প্রধান চরিত্র অপু ও দুর্গা।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.BENGALI.name,
                topic = "Literature & Comprehension",
                topicBangla = "বাংলা সাহিত্য ও লেখক",
                questionText = "'গীতাঞ্জলি' কাব্যের জন্য রবীন্দ্রনাথ ঠাকুর কোন সালে নোবেল পুরস্কার লাভ করেন?",
                questionTextBangla = "'গীতাঞ্জলি' কাব্যের জন্য রবীন্দ্রনাথ ঠাকুর কোন সালে নোবেল পুরস্কার লাভ করেন?",
                optionA = "১৯১১ সালে",
                optionB = "১৯১২ সালে",
                optionC = "১৯১৩ সালে",
                optionD = "১৯১৫ সালে",
                correctAnswerIndex = 2,
                explanation = "১৯১৩ সালে রবীন্দ্রনাথ ঠাকুর 'গীতাঞ্জলি' (Song Offerings) কাব্যগ্রন্থের জন্য সাহিত্যে এশিয়ার প্রথম নোবেল পুরস্কার অর্জন করেন।",
                explanationBangla = "১৯১৩ সালের নভেম্বর মাসে এই ঐতিহাসিক নোবেল পুরস্কার ঘোষিত হয়।"
            )
        )

        // ==========================================
        // 2. ENGLISH LANGUAGE (ইংরেজি ভাষা)
        // ==========================================

        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Articles & Prepositions",
                topicBangla = "Articles ও Prepositions",
                questionText = "The officer is very senior _______ me in the department.",
                questionTextBangla = "The officer is very senior _______ me in the department.",
                optionA = "than",
                optionB = "from",
                optionC = "to",
                optionD = "with",
                correctAnswerIndex = 2,
                explanation = "Latin comparative adjectives like senior, junior, superior, inferior, prior always take the preposition 'to', never 'than'.",
                explanationBangla = "Senior, junior, superior ইত্যাদির পর সর্বদাই Preposition 'to' বসে।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Articles & Prepositions",
                topicBangla = "Articles ও Prepositions",
                questionText = "He has been suffering from fever _______ last Monday.",
                questionTextBangla = "He has been suffering from fever _______ last Monday.",
                optionA = "for",
                optionB = "since",
                optionC = "from",
                optionD = "by",
                correctAnswerIndex = 1,
                explanation = "In Present Perfect Continuous Tense, 'since' is used for a specific Point of Time (last Monday), whereas 'for' is used for Period of Time.",
                explanationBangla = "নির্দিষ্ট সময় বা Point of Time নির্দেশ করতে 'since' ব্যবহৃত হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Subject-Verb Agreement",
                topicBangla = "Subject-Verb Agreement",
                questionText = "Neither the manager nor the employees _______ present at the meeting yesterday.",
                questionTextBangla = "Neither the manager nor the employees _______ present at the meeting yesterday.",
                optionA = "was",
                optionB = "were",
                optionC = "is",
                optionD = "are",
                correctAnswerIndex = 1,
                explanation = "When two subjects are joined by 'neither...nor', the verb agrees in number and person with the closest subject ('the employees' is plural in past tense -> 'were').",
                explanationBangla = "Neither...nor দ্বারা যুক্ত বাক্যে verb সর্বদাই নিকটবর্তী subject অনুযায়ী নির্ধারিত হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Voice Change",
                topicBangla = "Voice Change (বাচ্য পরিবর্তন)",
                questionText = "Change into passive voice: 'They will construct a new bridge over the river.'",
                questionTextBangla = "Change into passive voice: 'They will construct a new bridge over the river.'",
                optionA = "A new bridge is constructed over the river by them.",
                optionB = "A new bridge will be constructed over the river by them.",
                optionC = "A new bridge would be constructed over the river.",
                optionD = "A new bridge was constructed over the river.",
                correctAnswerIndex = 1,
                explanation = "Simple Future Passive rule: Object + will be + Past Participle (V3) + by + Subject. -> 'A new bridge will be constructed over the river by them.'",
                explanationBangla = "Future indefinite tense-এর passive voice গঠনের নিয়ম: will be + V3."
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Narration Change",
                topicBangla = "Narration Change (উক্তি পরিবর্তন)",
                questionText = "He said to me, 'Where do you live?' — Indirect speech:",
                questionTextBangla = "He said to me, 'Where do you live?' — Indirect speech:",
                optionA = "He asked me where did I live.",
                optionB = "He asked me where I lived.",
                optionC = "He told me where I am living.",
                optionD = "He enquired where do I lived.",
                correctAnswerIndex = 1,
                explanation = "In indirect interrogative sentences with Wh-words, the question structure changes to assertive statement order (Subject + Verb) in past tense: 'He asked me where I lived.'",
                explanationBangla = "Interrogative sentence-এ indirect করার সময় 'asked' বসে এবং বাক্য assertive রূপ নেয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Idioms & Phrases",
                topicBangla = "Idioms & Phrases (বাগধারা)",
                questionText = "What does the idiom 'To burn the candle at both ends' mean?",
                questionTextBangla = "What does the idiom 'To burn the candle at both ends' mean?",
                optionA = "To waste money foolishly",
                optionB = "To work extremely hard and exhaust oneself",
                optionC = "To create light in darkness",
                optionD = "To cheat someone cleverly",
                correctAnswerIndex = 1,
                explanation = "'To burn the candle at both ends' means to overwork oneself by getting up early and staying up late, exhausting energy.",
                explanationBangla = "অর্থ: অতিরিক্ত পরিশ্রম করা এবং বিশ্রামের সুযোগ না পেয়ে শক্তি ক্ষয় করা।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Phrasal Verbs",
                topicBangla = "Phrasal Verbs",
                questionText = "The firemen managed to _______ the raging fire within two hours.",
                questionTextBangla = "The firemen managed to _______ the raging fire within two hours.",
                optionA = "put out",
                optionB = "put off",
                optionC = "put up with",
                optionD = "put on",
                correctAnswerIndex = 0,
                explanation = "'Put out' means to extinguish a fire or flame. ('Put off' = postpone; 'Put up with' = tolerate).",
                explanationBangla = "'Put out' phrasal verb-এর অর্থ আগুন নেভানো (extinguish)।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "One-Word Substitution",
                topicBangla = "One-Word Substitution (এক কথায় প্রকাশ)",
                questionText = "A person who knows and speaks many languages is called a:",
                questionTextBangla = "A person who knows and speaks many languages is called a:",
                optionA = "Philanthropist",
                optionB = "Polyglot",
                optionC = "Monoglot",
                optionD = "Somnambulist",
                correctAnswerIndex = 1,
                explanation = "A 'Polyglot' is a person capable of speaking or writing several languages.",
                explanationBangla = "বহুভাষাবিদ ব্যক্তিকে ইংরেজিতে 'Polyglot' বলা হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Spotting Errors",
                topicBangla = "Spotting Errors (ভুল সংশোধন)",
                questionText = "Find the error: 'Each of the boys (A) / have completed (B) / their homework (C) / on time (D).'",
                questionTextBangla = "Find the error: 'Each of the boys (A) / have completed (B) / their homework (C) / on time (D).'",
                optionA = "Part A",
                optionB = "Part B ('have completed' should be 'has completed')",
                optionC = "Part C",
                optionD = "No error",
                correctAnswerIndex = 1,
                explanation = "'Each of' is grammatically singular and must take a singular verb ('has completed') rather than plural 'have'.",
                explanationBangla = "'Each of'-এর পর noun প্লুরাল হলেও verb সর্বদা সিঙ্গুলার (has) হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ENGLISH.name,
                topic = "Synonyms & Antonyms",
                topicBangla = "Synonyms & Antonyms",
                questionText = "Select the antonym of the word 'OBSTINATE':",
                questionTextBangla = "Select the antonym of the word 'OBSTINATE':",
                optionA = "Stubborn",
                optionB = "Flexible",
                optionC = "Rigid",
                optionD = "Adamant",
                correctAnswerIndex = 1,
                explanation = "'Obstinate' means stubborn or unyielding. Its antonym is 'Flexible' or 'Compliant'.",
                explanationBangla = "Obstinate (একগুঁয়ে/অনমনীয়) শব্দের বিপরীত হলো Flexible (নমনীয়)।"
            )
        )

        // ==========================================
        // 3. ARITHMETIC / MATHEMATICS (পাটিগণিত ও গণিত)
        // ==========================================

        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Percentage",
                topicBangla = "শতকরা (Percentage)",
                questionText = "If the price of sugar increases by 25%, by what percentage must a household reduce consumption so that total expenditure remains unchanged?",
                questionTextBangla = "চিনির মূল্য ২৫% বৃদ্ধি পেলে, খরচের পরিমাণ অপরিবর্তিত রাখতে চিনির ব্যবহার শতকরা কত কমাতে হবে?",
                optionA = "20%",
                optionB = "25%",
                optionC = "16.67%",
                optionD = "15%",
                correctAnswerIndex = 0,
                explanation = "Formula: Reduction % = [r / (100 + r)] * 100 = [25 / 125] * 100 = (1/5) * 100 = 20%.",
                explanationBangla = "ব্যবহার হ্রাসের শতকরা হার = (r / (100 + r)) × ১০০% = (২৫ / ১২৫) × ১০০% = ২০%।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Profit & Loss",
                topicBangla = "লাভ ও ক্ষতি (Profit & Loss)",
                questionText = "A shopkeeper sells an article for ₹840 at a profit of 20%. What was the cost price (CP) of the article?",
                questionTextBangla = "একজন দোকানদার একটি জিনিস ২০% লাভে ৮৪০ টাকায় বিক্রি করেন। জিনিসটির ক্রয়মূল্য কত ছিল?",
                optionA = "₹680",
                optionB = "₹700",
                optionC = "₹720",
                optionD = "₹750",
                correctAnswerIndex = 1,
                explanation = "CP = SP / (1 + Profit%) = 840 / 1.20 = ₹700.",
                explanationBangla = "ক্রয়মূল্য = (বিক্রয়মূল্য × ১০০) / (১০০ + লাভের হার) = (৮৪০ × ১০০) / ১২০ = ৭০০ টাকা।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Simple & Compound Interest",
                topicBangla = "সরল ও চক্রবৃদ্ধি সুদ",
                questionText = "What will be the simple interest on ₹5,000 for 3 years at an annual interest rate of 6%?",
                questionTextBangla = "বার্ষিক ৬% সরল সুদে ৫,০০০ টাকার ৩ বছরের মোট সুদ কত হবে?",
                optionA = "₹800",
                optionB = "₹900",
                optionC = "₹950",
                optionD = "₹1,000",
                correctAnswerIndex = 1,
                explanation = "SI = (P * R * T) / 100 = (5000 * 6 * 3) / 100 = 50 * 18 = ₹900.",
                explanationBangla = "সরল সুদ I = (P × R × T) / ১০০ = (৫০০০ × ৬ × ৩) / ১০০ = ৯০০ টাকা।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Ratio & Proportion",
                topicBangla = "অনুপাত ও সমানুপাত",
                questionText = "If A : B = 2 : 3 and B : C = 4 : 5, then what is the combined ratio A : B : C?",
                questionTextBangla = "যদি A : B = ২ : ৩ এবং B : C = ৪ : ৫ হয়, তবে A : B : C অনুপাতটি কত?",
                optionA = "8 : 12 : 15",
                optionB = "6 : 8 : 10",
                optionC = "8 : 10 : 15",
                optionD = "2 : 4 : 5",
                correctAnswerIndex = 0,
                explanation = "Make B equal: A:B = 8:12, B:C = 12:15. Combined A : B : C = 8 : 12 : 15.",
                explanationBangla = "B পদ সমান করে: A : B : C = ৮ : ১২ : ১৫।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Time & Work",
                topicBangla = "সময় ও কার্য (Time & Work)",
                questionText = "A can complete a piece of work in 12 days, and B can complete the same work in 24 days. Working together, in how many days will they finish the work?",
                questionTextBangla = "A একটি কাজ ১২ দিনে এবং B সেই কাজটি ২৪ দিনে করতে পারে। তারা একসাথে কাজ করলে কাজটি কত দিনে সম্পন্ন হবে?",
                optionA = "6 days",
                optionB = "8 days",
                optionC = "9 days",
                optionD = "10 days",
                correctAnswerIndex = 1,
                explanation = "Total work LCM(12, 24) = 24 units. A rate = 2, B rate = 1. Combined rate = 3 units/day. Time = 24 / 3 = 8 days.",
                explanationBangla = "মোট সময় = (১২ × ২৪) / (১২ + ২৪) = ২৮৮ / ৩৬ = ৮ দিন।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Speed, Distance & Time",
                topicBangla = "গতিবেগ ও দূরত্ব",
                questionText = "A 180-meter long train is traveling at a speed of 54 km/h. How many seconds will it take to cross an electric post?",
                questionTextBangla = "১৮০ মিটার দীর্ঘ একটি ট্রেন ৫৪ কিমি/ঘণ্টা গতিবেগে চললে, একটি বৈদ্যুতিক খুঁটি অতিক্রম করতে ট্রেনটির কত সেকেন্ড সময় লাগবে?",
                optionA = "10 seconds",
                optionB = "12 seconds",
                optionC = "14 seconds",
                optionD = "15 seconds",
                correctAnswerIndex = 1,
                explanation = "Speed in m/s = 54 * (5/18) = 15 m/s. Time = Distance / Speed = 180 / 15 = 12 seconds.",
                explanationBangla = "গতিবেগ = ৫৪ × (৫/১৮) = ১৫ মিটার/সেকেন্ড। সময় = ১৮০ / ১৫ = ১২ সেকেন্ড।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Problems on Ages",
                topicBangla = "বয়স সংক্রান্ত সমস্যা",
                questionText = "The ratio of present ages of Father and Son is 5 : 2. After 4 years, sum of their ages will be 50. What is the present age of the son?",
                questionTextBangla = "পিতা ও পুত্রের বর্তমান বয়সের অনুপাত ৫ : ২। ৪ বছর পর তাদের বয়সের সমষ্টি হবে ৫০ বছর। পুত্রের বর্তমান বয়স কত?",
                optionA = "10 years",
                optionB = "12 years",
                optionC = "14 years",
                optionD = "16 years",
                correctAnswerIndex = 1,
                explanation = "Present sum = 50 - 8 = 42. Son's age = 42 * (2/7) = 12 years.",
                explanationBangla = "বর্তমান বয়সের সমষ্টি = ৫০ - ৮ = ৪২ বছর। পুত্রের বয়স = ৪২ × (২/৭) = ১২ বছর।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Simplification & LCM/HCF",
                topicBangla = "সরলীকরণ ও ল.সা.গু/গ.সা.গু",
                questionText = "The HCF of two numbers is 12 and their LCM is 72. If one of the numbers is 24, what is the other number?",
                questionTextBangla = "দুটি সংখ্যার গ.সা.গু ১২ এবং ল.সা.গু ৭২। একটি সংখ্যা ২৪ হলে অপর সংখ্যাটি কত?",
                optionA = "32",
                optionB = "36",
                optionC = "40",
                optionD = "48",
                correctAnswerIndex = 1,
                explanation = "Product of numbers = HCF * LCM. Other number = (12 * 72) / 24 = 36.",
                explanationBangla = "অপর সংখ্যা = (১২ × ৭২) / ২৪ = ৩৬।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Pipes & Cisterns",
                topicBangla = "নল ও চৌবাচ্চা",
                questionText = "Pipe A can fill a tank in 6 hours and Pipe B can empty it in 8 hours. If both pipes are opened together in an empty tank, in how many hours will the tank be filled?",
                questionTextBangla = "A নল দিয়ে একটি চৌবাচ্চা ৬ ঘণ্টায় পূর্ণ হয় এবং B নল দিয়ে ৮ ঘণ্টায় খালি হয়। দুটি নল একসাথে খুলে দিলে খালি চৌবাচ্চাটি কত ঘণ্টায় পূর্ণ হবে?",
                optionA = "14 hours",
                optionB = "20 hours",
                optionC = "24 hours",
                optionD = "28 hours",
                correctAnswerIndex = 2,
                explanation = "Net rate = (1/6) - (1/8) = (4 - 3)/24 = 1/24 per hour. Time taken = 24 hours.",
                explanationBangla = "১ ঘণ্টায় পূর্ণ হয় = (১/৬ - ১/৮) = ১/২৪ অংশ। সম্পূর্ণ চৌবাচ্চা পূর্ণ হতে সময় লাগবে ২৪ ঘণ্টা।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.ARITHMETIC.name,
                topic = "Partnership & Average",
                topicBangla = "অংশীদারি কারবার ও গড়",
                questionText = "The average weight of 5 students is 40 kg. If a new student of weight 52 kg joins the group, what is the new average weight?",
                questionTextBangla = "৫ জন ছাত্রের গড় ওজন ৪০ কেজি। ৫২ কেজি ওজনের এক নতুন ছাত্র দলে যোগ দিলে দলের নতুন গড় ওজন কত হবে?",
                optionA = "41 kg",
                optionB = "42 kg",
                optionC = "43 kg",
                optionD = "44 kg",
                correctAnswerIndex = 1,
                explanation = "Total initial weight = 5 * 40 = 200 kg. New total = 200 + 52 = 252 kg. New average = 252 / 6 = 42 kg.",
                explanationBangla = "নতুন মোট ওজন = ২০০ + ৫২ = ২৫২ কেজি। নতুন গড় = ২৫২ / ৬ = ৪২ কেজি।"
            )
        )

        // ==========================================
        // 4. GENERAL KNOWLEDGE & RURAL DEVELOPMENT
        // ==========================================

        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Panchayati Raj 73rd Amendment",
                topicBangla = "পঞ্চায়েতি রাজ ব্যবস্থা ও ৭৩তম সংবিধান সংশোধন",
                questionText = "Which Constitutional Amendment Act accorded constitutional status to the Panchayati Raj Institutions (PRI) in India?",
                questionTextBangla = "কোন সংবিধান সংশোধন আইনের মাধ্যমে ভারতে পঞ্চায়েতি রাজ ব্যবস্থাকে সাংবিধানিক স্বীকৃতি দেওয়া হয়?",
                optionA = "42nd Amendment Act (1976)",
                optionB = "44th Amendment Act (1978)",
                optionC = "73rd Amendment Act (1992)",
                optionD = "74th Amendment Act (1992)",
                correctAnswerIndex = 2,
                explanation = "The 73rd Constitutional Amendment Act, 1992 (came into effect on 24th April 1993, celebrated as National Panchayati Raj Day) inserted Part IX and the 11th Schedule containing 29 subjects.",
                explanationBangla = "৭৩তম সংবিধান সংশোধন আইন, ১৯৯২ দ্বারা সংবিধানে ৯ম ভাগ এবং একাদশ তফসিল (২৯টি বিষয়) যুক্ত হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Panchayati Raj Structure",
                topicBangla = "ত্রিতরীয় পঞ্চায়েত কাঠামো ও গ্রাম সভা",
                questionText = "What is the intermediate tier in the three-tier Panchayati Raj system of West Bengal?",
                questionTextBangla = "পশ্চিমবঙ্গের ত্রিতরীয় পঞ্চায়েত ব্যবস্থার মধ্যবর্তী স্তরটি কোনটি?",
                optionA = "Gram Panchayat",
                optionB = "Panchayat Samiti",
                optionC = "Zilla Parishad",
                optionD = "Gram Sansad",
                correctAnswerIndex = 1,
                explanation = "The three tiers in West Bengal are: 1. Gram Panchayat (village level), 2. Panchayat Samiti (block level), and 3. Zilla Parishad (district level). The intermediate tier is Panchayat Samiti.",
                explanationBangla = "ব্লক স্তরে পঞ্চায়েত সমিতি হলো পঞ্চায়েতি রাজ ব্যবস্থার মধ্যবর্তী স্তর।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Panchayati Raj Structure",
                topicBangla = "গ্রাম সভা ও গ্রাম সংসদ",
                questionText = "Who are the members of a Gram Sabha in a Gram Panchayat area?",
                questionTextBangla = "একটি গ্রাম পঞ্চায়েত এলাকার গ্রাম সভার সদস্য কারা?",
                optionA = "Only elected ward members",
                optionB = "All adults registered as voters in the electoral roll of that Panchayat area",
                optionC = "Landowners and government employees",
                optionD = "Heads of households only",
                correctAnswerIndex = 1,
                explanation = "Under Article 243A, a Gram Sabha comprises all persons registered in the electoral rolls relating to a village comprised within the area of Panchayat.",
                explanationBangla = "গ্রাম পঞ্চায়েত এলাকার ভোটার তালিকায় অন্তর্ভুক্ত সকল প্রাপ্তবয়স্ক ব্যক্তিই গ্রাম সভার সদস্য।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Government Welfare Schemes",
                topicBangla = "পশ্চিমবঙ্গ সরকারি জনকল্যাণমূলক প্রকল্প",
                questionText = "Under the 'Lakshmir Bhandar' scheme in West Bengal, what is the monthly financial assistance provided to general category women heads of family?",
                questionTextBangla = "পশ্চিমবঙ্গের 'লক্ষ্মীর ভাণ্ডার' প্রকল্পে সাধারণ শ্রেণির পরিবারের মহিলা প্রধানদের মাসিক কত টাকা আর্থিক অনুদান প্রদান করা হয়?",
                optionA = "₹500 per month",
                optionB = "₹1,000 per month",
                optionC = "₹1,200 per month",
                optionD = "₹1,500 per month",
                correctAnswerIndex = 1,
                explanation = "As per state revisions, General category female beneficiaries receive ₹1,000/month and SC/ST women receive ₹1,200/month under Lakshmir Bhandar.",
                explanationBangla = "রাজ্য সরকারের বাজেট অনুযায়ী সাধারণ শ্রেণির মহিলারা মাসে ১,০০০ টাকা এবং SC/ST মহিলারা মাসে ১,২০০ টাকা পান।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Government Welfare Schemes",
                topicBangla = "কৃষক বন্ধু ও স্বাস্থ্য সাথী প্রকল্প",
                questionText = "Under the 'Krishak Bandhu (Natun)' scheme of West Bengal, what is the maximum annual financial aid given to a farmer having 1 acre or more cultivable land?",
                questionTextBangla = "পশ্চিমবঙ্গের 'কৃষক বন্ধু (নতুন)' প্রকল্পে ১ একর বা ততোধিক চাষযোগ্য জমির মালিক কৃষককে বার্ষিক সর্বোচ্চ কত টাকা আর্থিক সহায়তা দেওয়া হয়?",
                optionA = "₹6,000 per year",
                optionB = "₹8,000 per year",
                optionC = "₹10,000 per year (in 2 installments)",
                optionD = "₹12,000 per year",
                correctAnswerIndex = 2,
                explanation = "Under Krishak Bandhu (Natun), farmers with 1 acre or more get ₹10,000 per year (₹5,000 in Kharif + ₹5,000 in Rabi), plus ₹2 lakh death benefit for age 18-60.",
                explanationBangla = "কৃষক বন্ধু প্রকল্পে সর্বোচ্চ বার্ষিক ১০,০০০ টাকা এবং ২ লক্ষ টাকা জীবন বীমা সহায়তা দেওয়া হয়।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "Government Welfare Schemes",
                topicBangla = "MGNREGA ও গ্রামীণ আবাসন",
                questionText = "How many days of guaranteed wage employment per financial year is legally provided under MGNREGA to every rural household?",
                questionTextBangla = "MGNREGA আইনে প্রতিটি গ্রামীণ পরিবারকে আর্থিক বছরে ন্যূনতম কত দিনের কাজের আইনি নিশ্চয়তা দেওয়া হয়?",
                optionA = "75 days",
                optionB = "100 days",
                optionC = "120 days",
                optionD = "150 days",
                correctAnswerIndex = 1,
                explanation = "The Mahatma Gandhi National Rural Employment Guarantee Act (2005) guarantees at least 100 days of wage employment in a financial year to every rural household whose adult members volunteer for unskilled manual work.",
                explanationBangla = "মহাত্মা গান্ধী জাতীয় গ্রামীণ কর্মসংস্থান নিশ্চয়তা আইনে বছরে ন্যূনতম ১০০ দিনের কাজের অধিকার নিশ্চিত করা হয়েছে।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "West Bengal Static GK",
                topicBangla = "পশ্চিমবঙ্গের ভূগোল ও সাধারণ জ্ঞান",
                questionText = "Which is the highest mountain peak in West Bengal?",
                questionTextBangla = "পশ্চিমবঙ্গের সর্বোচ্চ পর্বতশৃঙ্গ কোনটি?",
                optionA = "Tonglu (টংলু)",
                optionB = "Sandakphu (সান্দাকফু)",
                optionC = "Phalut (ফালুট)",
                optionD = "Sabargram (সবরগ্রাম)",
                correctAnswerIndex = 1,
                explanation = "Sandakphu (3,636 meters / 11,930 ft) situated on the Singalila ridge in Darjeeling district is the highest point of West Bengal.",
                explanationBangla = "সান্দাকফু (উচ্চতা ৩,৬৩৬ মিটার) দার্জিলিং জেলায় সিঙ্গালীলা শৈলশিরায় অবস্থিত এবং এটি পশ্চিমবঙ্গের সর্বোচ্চ শৃঙ্গ।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "West Bengal Static GK",
                topicBangla = "পশ্চিমবঙ্গের নদী ও জেলাসমূহ",
                questionText = "Which river is famously known as the 'Sorrow of Bengal' (বাংলার দুঃখ)?",
                questionTextBangla = "কোন নদীকে 'বাংলার দুঃখ' বলা হতো?",
                optionA = "Teesta River",
                optionB = "Damodar River",
                optionC = "Rupnarayan River",
                optionD = "Subarnarekha River",
                correctAnswerIndex = 1,
                explanation = "The Damodar River was historically known as the 'Sorrow of Bengal' due to devastating recurrent monsoon floods before DVC multipurpose dams were built.",
                explanationBangla = "পূর্বে বিধ্বংসী বন্যার কারণে দামোদর নদকে বাংলার দুঃখ বলা হতো।"
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.GK_RURAL_DEV.name,
                topic = "West Bengal Static GK",
                topicBangla = "পশ্চিমবঙ্গের সীমানা ও জেলাসমূহ",
                questionText = "How many Indian states share their geographical borders with West Bengal?",
                questionTextBangla = "পশ্চিমবঙ্গের সীমানা ভারতের মোট কতগুলি রাজ্যের সাথে যুক্ত?",
                optionA = "3 states",
                optionB = "4 states",
                optionC = "5 states",
                optionD = "6 states",
                correctAnswerIndex = 2,
                explanation = "West Bengal borders 5 Indian states: Assam, Sikkim, Bihar, Jharkhand, and Odisha, and 3 international countries: Bangladesh, Nepal, and Bhutan.",
                explanationBangla = "পশ্চিমবঙ্গের সাথে ভারতের ৫টি রাজ্যের (আসাম, সিকিম, বিহার, ঝাড়খণ্ড, ওড়িশা) এবং ৩টি প্রতিবেশী দেশের সীমানা রয়েছে।"
            )
        )

        // ==========================================
        // 5. TECHNICAL SYLLABUS (NIRMAN SAHAYAK - CIVIL)
        // ==========================================

        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Building Materials",
                topicBangla = "বিল্ডিং মেটেরিয়ালস (সিমেন্ট, কংক্রিট, ইট)",
                questionText = "What is the initial setting time of Ordinary Portland Cement (OPC) as per Indian Standards (IS 269/IS 8112)?",
                questionTextBangla = "IS কোড অনুসারে সাধারণ পোর্টল্যান্ড সিমেন্টের (OPC) প্রাথমিক জমাট বাঁধার সময় (Initial Setting Time) কত?",
                optionA = "Not less than 15 minutes",
                optionB = "Not less than 30 minutes",
                optionC = "Not more than 60 minutes",
                optionD = "Not less than 10 hours",
                correctAnswerIndex = 1,
                explanation = "As per IS specifications, initial setting time of OPC should not be less than 30 minutes, and final setting time should not exceed 600 minutes (10 hours).",
                explanationBangla = "OPC সিমেন্টের Initial Setting Time ন্যূনতম ৩০ মিনিট এবং Final Setting Time অনধিক ১০ ঘণ্টা।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Surveying",
                topicBangla = "সার্ভেয়ারিং (Levelling & Contouring)",
                questionText = "In differential levelling, the first reading taken on a benchmark of known elevation after setting up the instrument is called:",
                questionTextBangla = "লেভেলিংয়ে ইন্সট্রুমেন্ট বসানোর পর জ্ঞাত উচ্চতার বেঞ্চমার্কে নেওয়া প্রথম পাঠকে (reading) কী বলে?",
                optionA = "Fore Sight (FS)",
                optionB = "Intermediate Sight (IS)",
                optionC = "Back Sight (BS)",
                optionD = "Change Point (CP)",
                correctAnswerIndex = 2,
                explanation = "Back Sight (BS) is always the first staff reading taken on a point of known Reduced Level (RL) after setting up and leveling the instrument.",
                explanationBangla = "যন্ত্র বসিয়ে প্রথম যে স্টাফ রিডিং নেওয়া হয় তাকে ব্যাক সাইট (BS) বলা হয় এবং Height of Instrument (HI) = RL + BS হয়।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "RCC & PCC Design",
                topicBangla = "RCC ও PCC ডিজাইন (IS 456)",
                questionText = "What is the characteristic 28-day compressive cube strength of M20 grade concrete in N/mm²?",
                questionTextBangla = "M20 গ্রেড কংক্রিটের ২৮ দিনের চরিত্রগত সংকোচন শক্তি (Compressive Strength) কত?",
                optionA = "15 N/mm²",
                optionB = "20 N/mm²",
                optionC = "25 N/mm²",
                optionD = "30 N/mm²",
                correctAnswerIndex = 1,
                explanation = "In M20, 'M' stands for Mix and '20' represents characteristic compressive strength of a 150mm cube at 28 days of curing in N/mm² (MPa). Nominal mix is 1 : 1.5 : 3.",
                explanationBangla = "M20 গ্রেডে ২০ সংখ্যাটি ২৮ দিনের ১৫ সেমি কিউবের কমপ্রেসিভ শক্তি ২০ N/mm² নির্দেশ করে। এর প্রচলিত অনুপাত ১:১.৫:৩।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "RCC & PCC Design",
                topicBangla = "RCC ও PCC ডিজাইন (IS 456)",
                questionText = "As per IS 456:2000, what is the minimum nominal clear cover provided to main reinforcement in RCC slabs?",
                questionTextBangla = "IS 456 অনুসারে RCC স্ল্যাবের মূল রডের ক্ষেত্রে ন্যূনতম ক্লিয়ার কভার (Clear Cover) কত রাখা হয়?",
                optionA = "15 mm",
                optionB = "20 mm",
                optionC = "25 mm",
                optionD = "40 mm",
                correctAnswerIndex = 1,
                explanation = "As per IS 456 (Table 16 / Clause 26.4), minimum nominal cover is: Slab = 20 mm, Beam = 25 mm, Column = 40 mm, Footing = 50 mm.",
                explanationBangla = "স্ল্যাবে ২০ মিমি, বীমে ২৫ মিমি, কলামে ৪০ মিমি এবং ফুটিংয়ে ৫০ মিমি ক্লিয়ার কভার প্রয়োজন।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Estimating & Costing",
                topicBangla = "এস্টিমেটিং, কস্টিং ও রেট অ্যানালিসিস",
                questionText = "In estimation of building earthwork, the 'Center Line Method' is most suitable and quick for buildings having:",
                questionTextBangla = "বিল্ডিং এস্টিমেশনে 'সেন্টার লাইন মেথড' (Center Line Method) কোন ধরনের ভবনের জন্য সবচেয়ে উপযুক্ত?",
                optionA = "Irregular walls with varying cross-sections",
                optionB = "Symmetrical building walls with uniform cross-section throughout",
                optionC = "Hexagonal shape only",
                optionD = "L-shaped single rooms only",
                correctAnswerIndex = 1,
                explanation = "The center line method is quickest and most accurate for symmetrical structures with uniform wall widths and cross-sections throughout.",
                explanationBangla = "অভিন্ন ক্রস-সেকশন বিশিষ্ট প্রতিসম ভবনের পরিমাপ ও এস্টিমেশনের জন্য সেন্টার লাইন পদ্ধতি দ্রুততম।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Hydraulics & Drainage",
                topicBangla = "হাইড্রলিক্স ও নিষ্কাশন ব্যবস্থা",
                questionText = "In fluid mechanics, the hydraulic mean depth (or hydraulic radius 'R') for an open channel is defined as:",
                questionTextBangla = "ওপেন চ্যানেলে হাইড্রলিক মিন ডেপথ (বা হাইড্রলিক রেডিয়াস R) কাকে বলে?",
                optionA = "Wetted Perimeter / Cross-sectional Area",
                optionB = "Cross-sectional Area (A) / Wetted Perimeter (P)",
                optionC = "Total Depth / 2",
                optionD = "Top Width / Hydraulic Depth",
                correctAnswerIndex = 1,
                explanation = "Hydraulic Radius R = A / P, where A is cross-sectional area of flow and P is wetted perimeter in contact with solid channel boundary.",
                explanationBangla = "হাইড্রলিক রেডিয়াস R = ক্ষেত্রফল (A) / ওয়েটেড পেরিমিটার বা আর্দ্র পরিসীমা (P)।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Road Construction",
                topicBangla = "রোড কনস্ট্রাকশন ও পেভমেন্ট ডিজাইন",
                questionText = "What is the cross slope (camber) recommended by IRC for bituminous roads in heavy rainfall areas?",
                questionTextBangla = "ভারী বৃষ্টিপাতপ্রবণ এলাকায় পিচ ঢালাই রাস্তার (Bituminous Road) জন্য IRC নির্ধারিত ক্যাম্বার (Camber) কত?",
                optionA = "1 in 33 (3.0%)",
                optionB = "1 in 40 (2.5%)",
                optionC = "1 in 50 (2.0%)",
                optionD = "1 in 60 (1.7%)",
                correctAnswerIndex = 1,
                explanation = "As per IRC standards, camber for thin bituminous surfacing in heavy rainfall areas is 1 in 40 (2.5%), and in light rainfall areas it is 1 in 50 (2.0%).",
                explanationBangla = "IRC নির্দেশিকা অনুযায়ী ভারী বৃষ্টিপাতের অঞ্চলে বিটুমিনাস সারফেসিংয়ে ক্যাম্বার ১ ইন ৪০ (২.৫%) প্রযোজ্য।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Building Materials",
                topicBangla = "ইটের গুণাগুণ ও মান পরীক্ষা",
                questionText = "A good first-class building brick should not absorb water more than what percentage of its dry weight after 24 hours immersion?",
                questionTextBangla = "২৪ ঘণ্টা জলে ভিজিয়ে রাখলে একটি প্রথম শ্রেণির উৎকৃষ্ট ইট তার শুষ্ক ওজনের শতকরা কত ভাগের বেশি জল শোষণ করবে না?",
                optionA = "10%",
                optionB = "15%",
                optionC = "20%",
                optionD = "25%",
                correctAnswerIndex = 2,
                explanation = "As per IS 1077, water absorption of First Class bricks after 24-hour water immersion should not exceed 20% of dry weight.",
                explanationBangla = "প্রথম শ্রেণির ইটের জল শোষণ ক্ষমতা ২০%-এর বেশি হওয়া উচিত নয় (দ্বিতীয় শ্রেণি ২২%, তৃতীয় শ্রেণি ২৫%)।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Surveying",
                topicBangla = "সার্ভেয়ারিং (থিয়োডোলাইট ও ট্রাভার্স)",
                questionText = "The size of a theodolite is designated by the:",
                questionTextBangla = "একটি থিয়োডোলাইটের সাইজ (আকার) কীসের মাধ্যমে নির্দিষ্ট করা হয়?",
                optionA = "Length of the telescope",
                optionB = "Diameter of the lower graduated horizontal circle",
                optionC = "Diameter of the vertical circle",
                optionD = "Height of the total instrument",
                correctAnswerIndex = 1,
                explanation = "The size of a theodolite is specified by the diameter of the graduated circle of the lower horizontal plate (e.g. 10 cm to 25 cm).",
                explanationBangla = "থিয়োডোলাইটের পরিমাপ নিচের অনুভূমিক গ্র্যাজুয়েটেড স্কেল প্লেটের ব্যাস দ্বারা নির্ধারিত হয়।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )
        list.add(
            QuestionEntity(
                subject = ExamSubject.CIVIL_ENGINEERING.name,
                topic = "Estimating & Costing",
                topicBangla = "এস্টিমেশন ও কাজের পরিমাপ",
                questionText = "In building measurement (IS 1200), what is the standard unit of measurement for RCC column/beam work?",
                questionTextBangla = "IS 1200 অনুসারে RCC কলাম বা বীমের কাজের পরিমাপের প্রমিত একক কোনটি?",
                optionA = "Square meter (sq.m / m²)",
                optionB = "Cubic meter (cu.m / m³)",
                optionC = "Running meter (r.m / m)",
                optionD = "Quintal",
                correctAnswerIndex = 1,
                explanation = "As per IS 1200, RCC structural work (foundations, columns, beams, slabs) is measured in Cubic Meters (cu.m / m³). Steel reinforcement is measured in Quintal / Tonnes.",
                explanationBangla = "RCC কাঠামোগত কাজের পরিমাপ ঘনমিটার (m³) এককে এবং রড পরিমাপ কুইন্টাল/টন এককে করা হয়।",
                isForGeneralTrack = false,
                isForNirmanSahayak = true
            )
        )

        return list
    }
}
