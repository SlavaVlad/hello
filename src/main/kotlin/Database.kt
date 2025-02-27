package com.nano

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.exposedLogger

val db = com.nano.Database()

class Database {
    init {
        connect()
    }

    private fun connect() {
        val connection =
            Database.connect(
                url = "jdbc:postgresql://localhost:5432/hello",
                driver = "org.postgresql.Driver",
                user = "vlad",
                password = System.getenv("db-password"),
            )
        exposedLogger.info("Connected to database")

    }
}