package dietplan.app.oqvvwe.data.remote.entities

import androidx.lifecycle.LiveData
import com.example.edamantestapp.data.remoteData.entities.RecipeItem
import com.example.edamantestapp.data.remoteData.entities.ResponseBody
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("?")
    suspend fun getRecipeResponse(
        @Query("type") type:String,
        @Query("q") food:String,
        @Query("app_id") appId:String,
        @Query("app_key") appKey:String)
    : Response<ResponseBody>

    companion object {

        val appId = "c8b8b822"
        val appKey = "fb21f581222bc2971e070661bea636b9"

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build()
        }

        private fun createLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        const val BASE_URL = "https://api.edamam.com/api/recipes/v2/"
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(createOkHttpClient())
                    .addConverterFactory(Json{
                        ignoreUnknownKeys = true
                        allowSpecialFloatingPointValues = true
                    }.asConverterFactory("application/json".toMediaType()))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
