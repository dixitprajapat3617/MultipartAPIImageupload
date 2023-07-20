package com.app.androidlocalhostfirebase.model.retrofit

import com.app.multipartapicalling.model.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Part


interface ApiService {

    fun uploadUser(@Body):Call<UserData>

}
/*@Part("name") name: RequestBody,
      @Part("description") description: RequestBody,
      @Part image: MultipartBody.Part,
      @Part("price") price: RequestBody,
      @Part("cuisineIds") cuisineIds: RequestBody,
      @Part("isSpecial") isSpecial: RequestBody,
      @Part("categoryId") categoryId: RequestBody*/