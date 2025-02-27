package com.nano.data

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val id: Int,
    val title: String,
    val subtitle: String,
    val content: String,
    val created: LocalDateTime,
    val additionalLinks: Link
) {
    val createdString = created.toJavaLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
}

class Articles(articles: List<Article>) {
    val all = articles.sortedBy { it.created }
    val last = articles.take(10)
    fun getArticleById(id: Int) = all.find { it.id == id }
}

val articles = Articles(
    listOf(
        Article(
            1,
            "Как создать свой первый проект на Kotlin",
            "Подробное руководство для начинающих",
            "В этой статье мы расскажем, как создать свой первый проект на Kotlin. Мы покажем, как установить Kotlin и настроить среду разработки. Также мы расскажем, как создать простое приложение на Kotlin и запустить его.",
            LocalDateTime(2021, 10, 1, 10, 0),
            Link("Читать далее", "https://example.com/article1", "https://example.com/read-more-icon.png")
        ),
        Article(
            2,
            "Как создать REST API на Spring Boot",
            "Подробное руководство для начинающих",
            "В этой статье мы расскажем, как создать REST API на Spring Boot. Мы покажем, как создать контроллеры, сервисы и репозитории. Также мы расскажем, как настроить базу данных и запустить приложение.",
            LocalDateTime(2021, 10, 5, 10, 0),
            Link("Читать далее", "https://example.com/article2", "https://example.com/read-more-icon.png")
        ),
        Article(
            3,
            "Как создать SPA на React",
            "Подробное руководство для начинающих",
            "В этой статье мы расскажем, как создать SPA на React. Мы покажем, как создать компоненты, роуты и хуки. Также мы расскажем, как настроить стили и запустить приложение.",
            LocalDateTime(2021, 10, 10, 10, 0),
            Link("Читать далее", "https://example.com/article3", "https://example.com/read-more-icon.png")
        )
    )
)