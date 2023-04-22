package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.Cat
import com.example.myapplication.domain.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CatsViewModel(
    private val repository: CatsRepository = CatsModule.provideCatsRepository()
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Cat>>()
    val liveData: LiveData<List<Cat>> = mutableLiveData

    fun loadCats() {
        viewModelScope.launch(Dispatchers.IO) {
            val cats = repository.fetchCats()
            launch(Dispatchers.Main) {
                mutableLiveData.postValue(cats)
            }
        }
    }
}
