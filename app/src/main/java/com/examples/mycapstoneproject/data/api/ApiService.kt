package com.examples.mycapstoneproject.data.api

import com.examples.mycapstoneproject.data.response.ListResponse
import com.examples.mycapstoneproject.data.response.LoginResponse
import com.examples.mycapstoneproject.data.response.RefreshResponse
import com.examples.mycapstoneproject.data.response.RegisResponse
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

data class FileUploadResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
interface ApiService {
    @FormUrlEncoded
    @POST("/api/auth/login")
    fun login(
        @Field("email")
        email:String,
        @Field("password")
        password:String
    ): Call<LoginResponse>

    @FormUrlEncoded
//    @Headers(
//        "Content-Type: application/json"
//    )
    @POST("/api/auth/register")
    fun register(
        @Field("name")
        name:String,
        @Field("email")
        email: String,
        @Field("gender")
        gender: String,
        @Field("password")
        password: String


    ):Call<RegisResponse>
    @GET("/api/get_img/by_gender")
    fun getList(@Header("Authorization") token : String):Call<ListResponse>

    @GET("/api/auth/refresh")
    fun refresh(@Header("refreshToken")token : String):Call<RefreshResponse>

    @DELETE("/api/auth/logout")
    fun logout(@Header("refreshToken")token : String)

    @Multipart
    @POST("/api/predict")
    fun uploadImage(
        @Header("Authorization") token : String,
        @Part file: MultipartBody.Part,

    ): Call<ListResponse>

//    @Multipart
//    @POST("/v1/stories")
//    fun uploadImage(
//        @Header("Authorization") token : String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): Call<FileUploadResponse>
//
//    @GET("stories/{id}")
//    fun getDetail(
//        @Header("Authorization") token : String,
//        @Path("id") id: String
//    ): Call<DetailResponse>
}