package it.services.roomdbexample

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun longToDate(value: Long): Date {
        return Date(value)
    }
}