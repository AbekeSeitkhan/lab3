package com.example.nesteam.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nesteam.data.repository.ResultRepository
import kotlinx.coroutines.launch
import com.example.nesteam.data.model.entity.Result

class ResultViewModel(private val repository: ResultRepository): ViewModel() {

    private val _results = MutableLiveData<List<Result>>()
    val results: LiveData<List<Result>> = _results

    fun loadGames() {
        viewModelScope.launch {
            val gamesList = repository.getGames()
            _results.postValue(gamesList.map { it })
        }
    }

}

class ResultViewModelFactory(private val repository: ResultRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}