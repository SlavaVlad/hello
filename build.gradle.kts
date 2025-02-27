
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.nano"
version = "0.0.1"

application {
    mainClass = "com.nano.ApplicationKt"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.task.scheduling.core)
    implementation(libs.ktor.server.task.scheduling.redis)
    implementation(libs.ktor.server.task.scheduling.mongodb)
    implementation(libs.ktor.server.task.scheduling.jdbc)
    implementation(libs.ktor.server.thymeleaf)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
//    implementation(libs.exposed.core)
//    implementation(libs.exposed.dao)
//    implementation(libs.exposed.jodatime)
//    implementation(libs.exposed.jdbc)
//    implementation(libs.postgresql.jdbc)
//    implementation("org.jetbrains:markdown:0.7.3")

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
