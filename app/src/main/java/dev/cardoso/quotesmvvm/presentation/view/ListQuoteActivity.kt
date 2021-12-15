package dev.cardoso.quotesmvvm.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityListarQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.EditQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.ListQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private val listQuoteViewModel: ListQuoteViewModel by viewModels()
    private lateinit var quoteList: List<QuoteModel>
    private lateinit var listQuoteAdapter: QuotesAdapter
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@ListQuoteActivity)
        setContentView(binding.root)
        getToken()
        getQuotes(token)
        observer()
    }
    private fun getToken() {
        lifecycleScope.launch(Dispatchers.IO) {
            userPreferencesRepository.token.collect { token = it }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            Log.e("OBSERVER", "interceptando datos en la observer")
            listQuoteViewModel.quoteResponse.collect {
                if (it != null) {
                    if (it.success) {
                        quoteList = it.data
                        binding.idlayout.adapter = QuotesAdapter(quoteList)
                    } else if (it.message.trim() != "") {
                        Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                        //alert.showDialog(this@ListQuoteActivity, it.message)
                    }
                }
            }
        }
    }

    private fun getQuotes(token: String) {
        lifecycleScope.launch {
            listQuoteViewModel.getQuotes("Bearer $token")


        }
    }

}