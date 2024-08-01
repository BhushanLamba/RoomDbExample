package it.services.roomdbexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contacts: Contacts)

    @Update
    suspend fun updateContact(contacts: Contacts)

    @Delete
    suspend fun deleteContact(contacts: Contacts)

    @Query("Delete from contact")
    suspend fun deleteAllContacts()

    @Query("Select * from contact")
    fun getContacts():LiveData<List<Contacts>>
}