package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityAgregarQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.AddQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint///
class AddQuoteActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAgregarQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private val addQuoteViewModel: AddQuoteViewModel by viewModels()///
    private var token=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@AddQuoteActivity)
        setContentView(binding.root)
        getToken()
        binding.btnagregarquote.setOnClickListener {
            val id= binding.idAgregar.text.toString().toInt()
            val quote = binding.quoteAgregar.text.toString()
            val author = binding.autorAgregar.text.toString()
            val quoteModel = QuoteModel(id = id, quote = quote, author = author)

            lifecycleScope.launch(Dispatchers.IO) {
                addQuoteViewModel.addQuote(quoteModel, "Bearer $token")
            }
        }
        /*var botonr = findViewById<FloatingActionButton>(R.id.BtnReturn)
        botonr.setOnClickListener {
            Log.d("listener ","se ejecuto")
                onBackPressed()
            }*/

        addQuoteViewModel.quoteResponse.let {  }
        observer()
    }
    private fun getToken() {
        lifecycleScope.launch(Dispatchers.IO) {
            userPreferencesRepository.token.collect { token = it }
        }
    }
    private  fun observer() {
        lifecycleScope.launch(Dispatchers.IO) {
            addQuoteViewModel.quoteResponse.collect {///
                binding.txtv2.text= it.message
            }
        }
    }

    /* private fun clearControls(){
         with(binding){
             idAgregar.setText("")
             idAgregar.setText("")
             autorAgregar.setText("")
             idAgregar.requestFocus()

         }
     }*/
    }