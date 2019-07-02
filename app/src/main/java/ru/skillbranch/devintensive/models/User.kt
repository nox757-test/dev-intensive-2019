package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println(
            "It's Alive!!!\n" +
                    "${if (lastName === "Doe")
                        "His name is $firstName $lastName"
                    else
                        "And his name is $firstName $lastName!!!"}\n"
        )
    }

    companion object Factory {

        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User("$lastId", firstName, lastName)
        }
    }

    data class Builder(
        var id: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = null,
        var isOnline: Boolean = false
    ) {
        fun id(value: String) = apply { this.id = value }
        fun firstName(value: String) = apply { this.firstName = value }
        fun lastName(value: String) = apply { this.lastName = value }
        fun avatar(value: String) = apply { avatar = value }
        fun rating(value: Int) = apply { rating = value }
        fun respect(value: Int) = apply { respect = value }
        fun lastVisit(value: Date) = apply { lastVisit = value }
        fun isOnline(value: Boolean) = apply { isOnline = value }

        fun build() = User(
            id ?: (++lastId).toString(),
            firstName,
            lastName,
            avatar,
            rating,
            respect,
            lastVisit,
            isOnline
        )
    }
}
