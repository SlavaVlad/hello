package com.nano.data

import korlibs.time.months
import korlibs.time.years
import kotlinx.datetime.Clock
import kotlinx.datetime.Clock.System
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.awt.Color
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

data class Technology(
    val name: String,
    val icon: String? = "",
    val _color: Color = Color.DARK_GRAY,
    val description: String? = "А тут у нас будут разные букавы",
    val libs: List<String> = emptyList(),
) {
    val color = "${_color.red},${_color.green},${_color.blue}"

    companion object {
        val kotlin = Technology(
            name = "Kotlin",
            icon = "static/svg/kotlin.svg",
            description = "Мой основной язык, использую для разработки везде где применимо использовать JVM и нет специфичных требований к ЯП",
            libs = listOf(
                "Ktor",
                "Exposed",
                "Coroutines",
                "Serialization",
                "KSP",
                "Thymeleaf",
                "Playwright"
            )
        )
        val typescript = Technology(
            name = "TypeScript",
            icon = "static/svg/typescript.svg",
            description = "Для небольших специфичных скриптов таких как тесты смарт-контрактов, а также для фронтенда иногда",
        )
        val java = Technology(
            name = "Java",
            description = "Только для легаси или если нужно прочитать уже существующий код библиотеки, а также при реверс-инжиниринге",
            icon = "static/png/java.png"
        )
        val tact = Technology(
            name = "Tact",
            icon = "https://avatars.githubusercontent.com/u/104382459?s=200&v=4",
            description = "Лучший язык для написания смартов, понятных для понимания. Очень удобный в плане синтаксиса и самое главное - читаемости и предскахуемости поведения",
            libs = listOf(
                "sandbox",
                "myLocalTon"
            )
        )
        val ton = Technology(
            name = "TON",
            icon = "static/svg/ton.svg",
            description = "Самый передовой L1 блокчейн. Я хорошо знаю его внутреннее устройство, работаю с ним с 2022 года, NFT, Jetton (TEP-74), DeFi. Делал смарты на Tact, немного на FunC, даже свой софт-форк делал",
        )
        val github = Technology(
            name = "GitHub",
            icon = "static/svg/github.svg",
            description = "Использую везде, понятно почему, и просто git локально",
        )
        val docker = Technology(
            name = "Docker",
            description = "Для быстрого развёртывания всякого и для тестироания",
            icon = "static/svg/docker.svg"
        )
        val postgres = Technology(
            name = "PostgreSQL",
            description = "Быстрая база для большого количества данных, больших запросов и кучи записей одновременно без доп слоя кеширования (он уже встроен)",
            icon = "static/png/postgresql.png"
        )
        val keycloak = Technology(
            name = "Keycloak",
            description = "Мощное решение для авторизации и аутентификации. Поддерживает большинство последних стандартов криптографии и ИБ, спасёт от тупых косяков и уязвимостей, а также головной боли в реализации ролей юзеров",
            icon = "static/png/keycloak.png"
        )
        val android = Technology(
            name = "Android",
            description = "Работал с ним много, большинство проектов до увлечения Web3 были под Android. От простого Java+Fragments дошёл до многомодульных проектов на KMP с кучей функций и экранов",
            icon = "static/png/android.png",
            libs = listOf(
                "KMP",
                "Jetpack",
                "Compose",
                "Koin",
                "Retrofit",
                "Room (small exp.)",
                "Kotlin Coroutines",
                "Flow",
                "DataStore",
                "Compose Destinations",
                "ViewModel",
                "LiveData",
                "Compose Navigation",
                "ViewBinding",
                "WorkManager",
                "Paging3",
                "Glide",
                "Firebase"
            )
        )
        val crypto = Technology(
            name = "Криптография",
            description = "После начала увлечения Web3 & TON начал изучать различные алгоритмы, знаю что такое хеширование, симметричное и ассиметричное шифрование (в особенности ED25519), подписи, алгоритмы хранения ключей и т.д.",
            icon = "static/png/cryptography.png"
        )
        val unrealEngine = Technology(
            name = "Unreal Engine",
            description = "Делаю разные игры на Blueprints, изучаю графические технологии, шейдинг и AI (не люблю его в этом контексте). Сейчас есть большой проект под названием \"Enceladus\" по мотивам Barotrauma",
            icon = "https://cdn2.unrealengine.com/ue-logotype-2023-vertical-white-1686x2048-bbfded26daa7.png"
        )
    }
}

enum class ProjectStatus(val literal: String) {
    IN_PROGRESS("В процессе"),
    DONE("Завершён"),
    PLANNED("Запланирован"),
    CANCELED("Отменён"),
    PAUSED("На паузе")
}

data class Project(
    val name: String,
    val description: String,
    val status: ProjectStatus,
    val technologies: List<Technology>
)

data class Link(val name: String, val url: String, val icon: String = "")

data class Developer(
    val name: String,
    val bio: String,
    val projects: List<Project>,
    val journey: List<String>,
    val links: List<Link>,
) {
    val techStack: List<Technology> = projects.flatMap { it.technologies }.distinct()
}

val fullDeveloper = Developer(
    name = "Владислав Владимиров",
    bio = "Привет! Я разработчик, студент 2-го курса Программной Инженерии и нейротехнологий университета ИТМО, ${age()} лет. \n\n\n<br>Программированием увлекался с 5 класса, начинал с робототехники и IoT. <br><br>Сейчас в основном занимаюсь разработкой смарт-контрактов для блокчейна TON, увлекаюсь технологиями децентрализации, также большими языковыми моделями и их принципами работы. <br><br>В свободное время читаю статьи на Хабре, развиваю с десяток пет-проектов разной степени готовности.",
    projects = listOf(
        Project(
            name = "RedChain",
            description = "Софт форк TON, созданный для быстрой обработки коротких сообщений, валидаторы по принципу BSC. Всё задумывается ради очень специфичного и большого DeFi/GameFi протокола DGB",
            status = ProjectStatus.IN_PROGRESS,
            technologies = listOf(Technology.ton, Technology.tact, Technology.kotlin, Technology.typescript)
        ),
        Project(
            name = "Unsession",
            description = "Тоже один из больших проектов. Создан для сбора обратной связи от студентов о преподавателях, упражняясь в обработке статистики, анализе текста с помощью LLM. Здесь я применяю все свои накопленные знания, например оплата за услуги принимается в криптовалюте TON",
            status = ProjectStatus.IN_PROGRESS,
            technologies = listOf(
                Technology.kotlin,
                Technology.keycloak,
                Technology.crypto,
                Technology.java,
                Technology.github,
                Technology.ton,
                Technology.docker,
                Technology.postgres
            )
        ),
        Project(
            name = "Enceladus",
            description = "С технической точки зрения - мультиплеерная игра, используем процедурную генерацию для мира, решили проблему \"движущейся платформы\", разделил подлодку на 2 объекта, которые объединены через одну абстракцию. Таким образом добился ускорения работы на порядки. Для общения с ИИ используется LLM и распознавание голоса, всё при условии p2p-соединения",
            status = ProjectStatus.PAUSED,
            technologies = listOf(Technology.unrealEngine)
        ),
        Project(
            name = "Твой Дневник",
            description = "Мобильное приложение для учеников и родителей, позволяющее вести учёт успеваемости, домашних заданий и общаться с учителями. Защищён как проект за 11 класс. Первое использование Firebase и Kotlin как основы мобильного приложения",
            status = ProjectStatus.DONE,
            technologies = listOf(Technology.android)
        ),
        Project(
            name = "Таинственный GameFi проект",
            description = "Завершён в 2024. Создавался командой, я разрабатывал Web3 часть и консультировал остальную команду по экономическим вопросам и интеграции с блокчейном. Проект был относительно успешен и принёс мне много нового опыта",
            status = ProjectStatus.DONE,
            technologies = listOf(Technology.ton, Technology.tact, Technology.typescript)
        ),
        Project(
            name = "Telegram бот таинственного GameFi проекта",
            description = "Выдерживал пиковые нагрузки до 1000 tps, был многоязычным. По сути было это небольшое руководство по проекту и путеводитель по сообществам на разных языках",
            status = ProjectStatus.DONE,
            technologies = listOf(Technology.kotlin, Technology.github)
        ),
        Project(
            name = "Project 47",
            description = "Был начат в 2023 году как тренировка в мультиплеерных технологиях UE 5.1.1. Это командный Sci-Fi шутер от первого лица с разрушаемым окружением. Отменён ибо исчерпал свой обучающий потенциал для меня",
            status = ProjectStatus.CANCELED,
            technologies = listOf(Technology.unrealEngine)
        ),
        Project(
            name = "Прочие проекты",
            description = "Небольшие проекты упомянуты не были такие как маленькие игры на Unreal Engine, смарт-контракты, которые не пошли в продакшн, различные боты и скрипты для автоматизации",
            status = ProjectStatus.DONE,
            technologies = listOf()
        )
    ),
    journey = listOf(
        "2015 - Робототехника в школе, Robolab и Lego Technics",
        "2017 - Arduino, инжинерное 3D в Компас v14",
        "2018 - Первый макет на 3D принтере с роботизацией Arduino nano",
        "2019 - Первый сайт на HTML/CSS + JS, курсы Fructcode",
        "2020 - Проект \"Твой Дневник\" - мобильный аналог системы Элетронный дневник с упором на персонализацию",
        "2021 - Первые проекты на фрилансе, переход с Java на Koltin раз и навсегда",
        "2022 - Первые шаги в блокчейн-технологиях, поступление в ИТМО на ПИиКТ",
        "2023 - Первые учебные смарт-контракты, разработка мобильных приложений под заказ, старт в геймдеве на UE",
        "2024 - Первый успешный криптопроект в GameFi, старт в коммерческой блокчейн-разработке",
        "2025 - Крупный собственный блокчейн-проект"
    ),
    links = listOf(
        Link("GitHub", "https://github.com/SlavaVlad", "static/svg/github.svg"),
        Link("Telegram", "https://t.me/smartcontractman", "static/svg/telegram.svg"),
    )
)

fun calculateAge(birthDate: LocalDate): Int {
    val currentDate = System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val yearsDifference = currentDate.year - birthDate.year
    val adjustedBirthDate = birthDate.plus(DatePeriod(years = yearsDifference))
    return if (currentDate < adjustedBirthDate) yearsDifference - 1 else yearsDifference
}

fun age(): Int {
    val birthDate = LocalDate(2004, 11, 3)
    val age = calculateAge(birthDate)
    return age
}

val developer = Developer(
    name = "Владислав Владимиров",
    bio = "Привет! Я разработчик, студент 2-го курса Программной Инженерии и нейротехнологий университета ИТМО, ${
        age()
    } лет. <br><br>Программированием увлекался с 5 класса, начинал с робототехники и IoT. <br><br>Сейчас в основном занимаюсь разработкой смарт-контрактов для блокчейна TON, увлекаюсь технологиями децентрализации, также большими языковыми моделями и их принципами работы. <br><br>В свободное время читаю статьи на Хабре, развиваю с десяток пет-проектов разной степени готовности.",
    projects = listOf(
        Project(
            name = "RedChain",
            description = "Софт форк TON, созданный для быстрой обработки коротких сообщений, валидаторы по принципу BSC. Всё задумывается ради очень специфичного и большого DeFi/GameFi протокола DGB",
            status = ProjectStatus.IN_PROGRESS,
            technologies = listOf(Technology.ton, Technology.tact, Technology.kotlin, Technology.typescript)
        ),
        Project(
            name = "Unsession",
            description = "Тоже один из больших проектов. Создан для сбора обратной связи от студентов о преподавателях, упражняясь в обработке статистики, анализе текста с помощью LLM. Здесь я применяю все свои накопленные знания, например оплата за услуги принимается в криптовалюте TON",
            status = ProjectStatus.IN_PROGRESS,
            technologies = listOf(
                Technology.kotlin,
                Technology.keycloak,
                Technology.crypto,
                Technology.java,
                Technology.github,
                Technology.ton,
                Technology.docker,
                Technology.postgres
            )
        ),
        Project(
            name = "Enceladus",
            description = "С технической точки зрения - мультиплеерная игра, используем процедурную генерацию для мира, решили проблему \"движущейся платформы\", разделил подлодку на 2 объекта, которые объединены через одну абстракцию. Таким образом добился ускорения работы на порядки. Для общения с ИИ используется LLM и распознавание голоса, всё при условии p2p-соединения",
            status = ProjectStatus.PAUSED,
            technologies = listOf(Technology.unrealEngine)
        ),
        Project(
            name = "Таинственный GameFi проект",
            description = "Завершён в 2024. Создавался командой, я разрабатывал Web3 часть и консультировал остальную команду по экономическим вопросам и интеграции с блокчейном. Проект был относительно успешен и принёс мне много нового опыта",
            status = ProjectStatus.DONE,
            technologies = listOf(Technology.ton, Technology.tact, Technology.typescript)
        ),
        Project(
            name = "Telegram бот таинственного GameFi проекта",
            description = "Выдерживал пиковые нагрузки до 1000 tps, был многоязычным. По сути было это небольшое руководство по проекту и путеводитель по сообществам на разных языках",
            status = ProjectStatus.DONE,
            technologies = listOf(Technology.kotlin, Technology.github)
        ),
        Project(
            name = "Project 47",
            description = "Был начат в 2023 году как тренировка в мультиплеерных технологиях UE 5.1.1. Это командный Sci-Fi шутер от первого лица с разрушаемым окружением. Отменён ибо исчерпал свой обучающий потенциал для меня",
            status = ProjectStatus.CANCELED,
            technologies = listOf(Technology.unrealEngine)
        ),
        Project(
            name = "Прочие проекты",
            description = "Небольшие проекты упомянуты не были такие как маленькие игры на Unreal Engine, смарт-контракты, которые не пошли в продакшн, различные боты и скрипты для автоматизации",
            status = ProjectStatus.DONE,
            technologies = listOf()
        )
    ),
    journey = listOf(
        "2015-2021 - Первые шаги, от робототехники до фриланса",
        "2021 - Первые проекты на фрилансе, переход с Java на Koltin раз и навсегда",
        "2022 - Первые шаги в блокчейн-технологиях, поступление в ИТМО на ПИиКТ",
        "2023 - Первые учебные смарт-контракты, разработка мобильных приложений под заказ, старт в геймдеве на UE",
        "2024 - Первый успешный криптопроект в GameFi, старт в коммерческой блокчейн-разработке",
        "2025 - Крупный собственный блокчейн-проект"
    ),
    links = listOf(
        Link("GitHub", "https://github.com/SlavaVlad", "static/svg/github.svg"),
        Link("Telegram", "https://t.me/smartcontractman", "static/svg/telegram.svg"),
    )
)