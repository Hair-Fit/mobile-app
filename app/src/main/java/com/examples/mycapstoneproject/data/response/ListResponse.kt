package com.examples.mycapstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class ListResponse(

	@field:SerializedName("Heart")
	val heart: List<HairItem>,

	@field:SerializedName("Oblong")
	val oblong: List<HairItem>,

	@field:SerializedName("Oval")
	val oval: List<HairItem>,

	@field:SerializedName("Triangle")
	val triangle: List<HairItem>,

	@field:SerializedName("Square")
	val square: List<HairItem>
)

data class HairItem(

	@field:SerializedName("hair_type")
	val hairType: String,

	@field:SerializedName("img")
	val img: String,

	@field:SerializedName("haircut_name")
	val haircutName: String,

	@field:SerializedName("gender_fit")
	val genderFit: String
)
