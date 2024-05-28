package com.dicoding.picodiploma.loginwithanimation.api.Responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AddStoryResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
