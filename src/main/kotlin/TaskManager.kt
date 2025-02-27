package com.nano

import io.github.flaxoos.ktor.server.plugins.taskscheduling.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.redis.*
import io.ktor.server.application.*

fun Application.configureTasks() {
    install(TaskScheduling){
        // Choose task manager config based on your chosen task manager dependencies
        redis { // <-- given no name, this will be the default manager
            connectionPoolInitialSize = 1
            host = "0.0.0.0"
            port = 6379
            username = ""
            password = ""
            connectionAcquisitionTimeoutMs = 1_000
            lockExpirationMs = 60_000
        }
    
        task { // if no taskManagerName is provided, the task would be assigned to the default manager
            name = "My task"
            task = { taskExecutionTime ->
                log.info("My task is running: $taskExecutionTime")
            }
            kronSchedule = {
                seconds {
                    from(0).every(5)
                }
            }
            concurrency = 1
        }
    }
}
