package com.app.multipartapicalling


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.app.androidlocalhostfirebase.model.retrofit.ApiClient
import com.app.multipartapicalling.databinding.ActivityMainBinding
import com.app.multipartapicalling.model.UserData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File



class MainActivity : AppCompatActivity() {
    lateinit var apiclient:ApiClient

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {

            val name = binding.editTextName.text.toString()
            val description = binding.editTextDescription.text.toString()
            val price = binding.editTextPrice.text.toString()
            val cuisineIds = binding.editTextCuisineIds.text.toString()
            val isSpecial = binding.checkBoxSpecial.isChecked
            val categoryId = binding.editTextCategoryId.text.toString()

            savedAccont(name,description,price,cuisineIds,isSpecial,categoryId)

        }
    }

    private fun savedAccont(
        name: String,
        description: String,
        price: String,
        cuisineIds: String,
        special: Boolean,
        categoryId: String
    ) {
        var accesstoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM4LCJqdGkiOiI1NmI0YTFiNGUwN2UyMWRhOGYyMmI0ZjYzYTVlOTAzZjQwNmE5NWJkNTM1MDQ0NmI0OTc5OTYwN2U0NTU4MDZkIiwicGhvbmUiOiIrOTE3Nzc4ODAxNjU1Iiwicm9sZSI6MiwiaWF0IjoxNjg5NzYxNTI0LCJleHAiOjE3MjEyOTc1MjR9.vRdiMOK374HlJdjPKnglnYOk_RVJVSQeYoTELx_Y100\n"
        val apiService = apiclient.getInstance(accesstoken)

        // Get the selected image file
        val selectedImage = File("/path/to/your/image.jpg") // Replace with the actual image file path

        // Create a list of cuisine IDs (convert it to a list of integers if necessary)
        val cuisineIdsList = cuisineIds.split(",").map { it.trim().toInt() }

        // Create a RequestBody instance for the image file
        val imageRequestBody = selectedImage.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", selectedImage.name, imageRequestBody)

        // Make the API call using the apiService instance
        var user=UserData(description = description, name = name, image = selectedImage, price = price, cuisineIds = cuisineIdsList, isSpecial = special.toString(), categoryId = categoryId)
       /* val call = apiService.uploadUser(
            name = name,
            description = description,
            image = imagePart,
            price = price,
            cuisineIds = cuisineIdsList,
            isSpecial = special.toString(), // Convert the boolean to String
            categoryId = categoryId
        )*/

        apiService.uploadUser(user)

        /*call.enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful) {
                    // Handle success response
                    val userData = response.body()
                    showToast("Data uploaded successfully!")
                } else {
                    // Handle error response
                    showToast("Failed to upload data.")
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                // Handle failure
                showToast("Error: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    }*/
}