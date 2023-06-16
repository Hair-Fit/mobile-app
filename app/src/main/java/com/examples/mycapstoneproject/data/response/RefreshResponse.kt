package com.examples.mycapstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class RefreshResponse(

	@field:SerializedName("accessToken")
	val accessToken: String
)
