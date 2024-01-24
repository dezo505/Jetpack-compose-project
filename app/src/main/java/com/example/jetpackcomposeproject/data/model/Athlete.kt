package com.example.jetpackcomposeproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "athletes")
class Athlete() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "firstName")
    var firstName: String? = null
    @ColumnInfo(name = "lastName")
    var lastName: String? = null
    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String? = null
    @ColumnInfo(name = "email")
    var email: String? = null
    @ColumnInfo(name = "salary")
    var salary: Double = 0.0
    @ColumnInfo(name = "sport")
    var sport: Sport? = null

    constructor(
        firstName: String?,
        lastName: String?,
        phoneNumber: String?,
        email: String?,
        salary: Double,
        sport: Sport?
    ) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.email = email
        this.salary = salary
        this.sport = sport
    }

    constructor(num: Int) : this() {
        firstName = "Person"
        lastName = num.toString()
        phoneNumber = generateRandomPhoneNumber()
        email = generateRandomEmail()
        salary = Random.nextDouble() * 10000
        sport = Sport.values().random()
    }

    private fun generateRandomPhoneNumber(): String {
        val sb = StringBuilder()
        repeat(3) {
            sb.append((0..9).random())
        }
        sb.append("-")
        repeat(3) {
            sb.append((0..9).random())
        }
        sb.append("-")
        repeat(4) {
            sb.append((0..9).random())
        }
        return sb.toString()
    }

    private fun generateRandomEmail(): String {
        val domains = arrayOf("gmail.com", "yahoo.com", "hotmail.com", "example.com", "domain.com")
        val localPart = (1..10).map { ('a'..'z').random() }.joinToString("")
        val domain = domains.random()
        return "$localPart@$domain"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Athlete

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (email != other.email) return false
        if (salary != other.salary) return false
        if (sport != other.sport) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + salary.hashCode()
        result = 31 * result + (sport?.hashCode() ?: 0)
        return result
    }
}