package com.dicoding.picodiploma.loginwithanimation.view.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

class AddStoryViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun getToken() : String{
        return repository.getToken()
    }

    fun setLoading(loading: Boolean){
        _isLoading.value = loading
    }
}