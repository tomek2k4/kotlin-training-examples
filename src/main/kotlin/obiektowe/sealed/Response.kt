package org.example.obiektowe.sealed

sealed class Response(val status: Status) {
    class Progress(status: Status) : Response(status)
    class Success(status: Status, val data: String) : Response(status)
    class Failure(status: Status, val errorMessage: String) : Response(status)
}

enum class Status(val code: Int) {
    Success(200), Accepted(202), Unauthorized(401), NotFound(404), ServerError(500);

    companion object {
        fun fromCode(code: Int): Status = entries.first { it.code == code }

    }
}

class ResponseHandler {
    fun handle(code: Int, data: String?): Response {
        val status = Status.fromCode(code)
        return when (status) {
            Status.Success, Status.Accepted -> Response.Success(data = data ?: "", status = status)
            Status.Unauthorized, Status.NotFound, Status.ServerError -> Response.Failure(status, status.name)
        }
    }

    companion object {
        fun main(responseHandler: ResponseHandler, code: Int = 200, data: String? = "OK") {
            responseHandler.handle(code, data).print()
        }
    }
}

fun Response.print() {
    println(
        when (this) {
            is Response.Progress -> "Response in progress"
            is Response.Success -> data
            is Response.Failure -> errorMessage
        }
    )
}

