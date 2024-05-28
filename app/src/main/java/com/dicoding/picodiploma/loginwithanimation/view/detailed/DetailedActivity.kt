package com.dicoding.picodiploma.loginwithanimation.view.detailed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.api.Responses.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedBinding

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading

//    private val _detailedStory = MutableLiveData<ListStoryItem>()
//    val detailedStory: LiveData<ListStoryItem> = _detailedStory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storyItem = intent.getParcelableExtra<ListStoryItem>("listStoryItem")

        Glide.with(this)
                .load(storyItem!!.photoUrl)
                .into(binding.detailedPhoto)
            binding.detailedTitle.text = storyItem.name
            binding.detailedDescription.text = storyItem.description
    }

//    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }
}