package dev.cardoso.quotesmvvm.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.databinding.ActivityDeleteQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.DeleteQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeleteQuoteActivity: AppCompatActivity() {
        private lateinit var binding: ActivityDeleteQuoteBinding
        private lateinit var userPreferencesRepository: UserPreferencesRepository
        private val deleteQuoteViewModel: DeleteQuoteViewModel by viewModels()///
        private var token=""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityDeleteQuoteBinding.inflate(layoutInflater)
            userPreferencesRepository = UserPreferencesRepository(this@DeleteQuoteActivity)
            setContentView(binding.root)
            getToken()
            binding.btneliminar.setOnClickListener {
                val id= binding.txtid.text.toString().toInt()

                lifecycleScope.launch(Dispatchers.IO) {
                    deleteQuoteViewModel.deleteQuote("Bearer $token", id)
                }
            }
            /*var botond = findViewById<FloatingActionButton>(R.id.idBtnRetornarM)
            botond.setOnClickListener {
                Log.d("listener ","se ejecuto")
                onBackPressed()
            }*/
            deleteQuoteViewModel.quoteResponse.let {  }
            observer()
        }
        private fun getToken() {
            lifecycleScope.launch(Dispatchers.IO) {
                userPreferencesRepository.token.collect { token = it }
            }
        }
        private  fun observer() {
            lifecycleScope.launch(Dispatchers.IO) {
                deleteQuoteViewModel.quoteResponse.collect {///
                    binding.txt3.text= it.message
                }
            }
        }


}



