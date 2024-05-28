package com.dicoding.picodiploma.loginwithanimation.view.add

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toFile
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.util.Util
import com.dicoding.picodiploma.loginwithanimation.api.ApiConfig
import com.dicoding.picodiploma.loginwithanimation.api.Responses.AddStoryResponse
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityAddBinding
import com.dicoding.picodiploma.loginwithanimation.view.ViewModelFactory
import com.dicoding.picodiploma.loginwithanimation.view.main.MainActivity
import com.dicoding.picodiploma.loginwithanimation.view.main.MainViewModel
import com.dicoding.picodiploma.loginwithanimation.view.main.StoryViewModel
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AddStoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private var currentImageUri: Uri? = null

    private val addStoryViewModel by viewModels<AddStoryViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var token = ""

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addStoryViewModel.setLoading(false)

        addStoryViewModel.isLoading.observe(this){
            showLoading(it)
        }

        setupAction()

    }

    // galery
    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
    private val launcherGallery = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }
    private fun showImage() {
        addStoryViewModel.setLoading(true)
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imageView.setImageURI(it)
            binding.imageView.tag = it
        }
        addStoryViewModel.setLoading(false)
    }

    // camera
    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }
    private val launcherIntentCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setupAction() {
        binding.galeryButton.setOnClickListener {
            startGallery()
        }
        binding.cameraButton.setOnClickListener {
            startCamera()
        }
        binding.uploadButton.setOnClickListener {
            addStoryViewModel.setLoading(true)
            val description = binding.editTextDescription.text.toString()
            try{
                val photoFile = uriToFile(binding.imageView.getTag() as Uri, this)
                if(photoFile.exists() && description.isNotEmpty()){
                    if(photoFile.length() != 0L){
                        addStoryViewModel.setLoading(false)
                        lifecycleScope.launch {
                            uploadImage(photoFile, description)
                        }
                    }else {
                        Toast.makeText(this@AddStoryActivity, "anda belum mengupload foto atau mengisi deskripsi", Toast.LENGTH_SHORT).show()
                        addStoryViewModel.setLoading(false)
                    }
                }else{
                    Toast.makeText(this@AddStoryActivity, "anda belum mengupload foto atau mengisi deskripsi", Toast.LENGTH_SHORT).show()
                    addStoryViewModel.setLoading(false)
                }
            }catch (e: Exception){
                Toast.makeText(this@AddStoryActivity, "anda belum mengupload foto atau mengisi deskripsi", Toast.LENGTH_SHORT).show()
                addStoryViewModel.setLoading(false)
            }
        }
    }

    suspend fun uploadImage(imageFile: File, description: String) {
        try {
            token = addStoryViewModel.getToken()
            println("token di addstoryactivity: ${token}")
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                imageFile.name,
                requestImageFile
            )
            addStoryViewModel.setLoading(true)
            ApiConfig.getApiService(token).postStory(description, multipartBody).enqueue(object : Callback<AddStoryResponse> {
                override fun onResponse(call: Call<AddStoryResponse>, response: Response<AddStoryResponse>) {
                    addStoryViewModel.setLoading(false)
                    startActivity(Intent(this@AddStoryActivity, MainActivity::class.java)).also {
                        Toast.makeText(this@AddStoryActivity, "Added Successfully!", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<AddStoryResponse>, t: Throwable) {
                    addStoryViewModel.setLoading(false)
                    Toast.makeText(this@AddStoryActivity, "Proses gagal", Toast.LENGTH_SHORT).show()
                }

            })

//            addStoryViewModel.postStory(imageFile, description)
        } catch (e: HttpException) {
            println("Exception: ${e.message}")
        }
    }

}