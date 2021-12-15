package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.databinding.ActivityMenuBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.UserViewModel

@AndroidEntryPoint
class QuoteMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var btnAdd1 = findViewById<Button>(R.id.btnAgregar1)
        btnAdd1.setOnClickListener {
            var intent = Intent(this, AddQuoteActivity::class.java)
            startActivity(intent)
        }

        var btnUpdate = findViewById<Button>(R.id.btnUpdate1)
        btnUpdate.setOnClickListener {
            var intent = Intent(this, EditQuoteActivity::class.java)
            startActivity(intent)
        }

        var btnList = findViewById<Button>(R.id.btnListar1)
        btnList.setOnClickListener {
            var intent = Intent(this, ListQuoteActivity::class.java)
            startActivity(intent)
        }

        var btnDelete = findViewById<Button>(R.id.idDeletee)
        btnDelete.setOnClickListener {
            var intent = Intent(this, DeleteQuoteActivity::class.java)
            startActivity(intent)
        }

    }
}