package it.services.roomdbexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import it.services.roomdbexample.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var dataBase: ContactDataBase
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        dataBase = ContactDataBase.getDataBase(this)

        binding.apply {
            btnInsert.setOnClickListener {
                GlobalScope.launch {
                    dataBase.contactDao()
                        .insertContact(Contacts(0, "Bhushan Lamba", "9212394390", Date(),"Father","Lamba","Kumar"))

                }
            }

            btnDelete.setOnClickListener {
                GlobalScope.launch {
                    val id = etId.text.toString().toLong()
                    dataBase.contactDao()
                        .deleteContact(Contacts(id, "Bhushan Lamba", "9212394390", Date(),"Father","Lamba","Kumar"))

                }
            }

            btnDeleteAll.setOnClickListener {
                GlobalScope.launch {
                    dataBase.contactDao().deleteAllContacts()
                }
            }

            btnGetData.setOnClickListener {
                dataBase.contactDao().getContacts().observe(this@MainActivity, {
                    Log.d("contacts", "onCreate: $it")
                })

            }

        }
    }
}