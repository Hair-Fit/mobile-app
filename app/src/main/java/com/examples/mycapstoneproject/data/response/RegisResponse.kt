package com.examples.mycapstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class RegisResponse(

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
