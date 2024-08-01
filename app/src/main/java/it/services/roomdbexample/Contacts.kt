package it.services.roomdbexample

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: String,
    val date :Date,
    val relation:String,
    val lastName:String,
    val middleName:String
)