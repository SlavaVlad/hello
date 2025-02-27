package com.nano

import com.nano.data.nav.NavigationDialect
import com.nano.data.developer
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.Thymeleaf
import io.ktor.server.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun Application.configureTemplating() {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
        addDialect(NavigationDialect())
    }
    routing {
        staticResources("/static", "static")
        get("/") {
            call.respond(ThymeleafContent("index", mapOf("developer" to developer)))
        }
//        get("/articles") {
//            call.respond(ThymeleafContent("articles", mapOf("articles" to articles)))
//        }
//        get("/articles/{articleId}") {
//            val articleId = call.parameters["articleId"]?.toIntOrNull()
//            if (articleId == null) {
//                call.respondText("articleId param not specified", status = HttpStatusCode.BadRequest)
//                return@get
//            }
//            val article = articles.getArticleById(articleId)
//            if (article == null) {
//                call.respondText("Article $articleId not found", status = HttpStatusCode.NotFound)
//                return@get
//            }
//            call.respond(ThymeleafContent("article", mapOf("article" to article)))
//        }
    }
}
