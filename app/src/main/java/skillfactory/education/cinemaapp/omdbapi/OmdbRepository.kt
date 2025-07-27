package skillfactory.education.cinemaapp.omdbapi

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File

class OmdbRepository {
    private val retrofit = Retrofit.Builder().baseUrl("https://www.omdbapi.com")
        .addConverterFactory(ScalarsConverterFactory.create()).build()

    private val api = retrofit.create(OmdbApi::class.java)

    suspend fun fetchAndSaveMovieJson(context: Context, id: String, apiKey: String) {
        val file = File(context.filesDir, "$id.json")
        if (!file.exists()) {
            withContext(Dispatchers.IO) {
                val response = api.getMovie(id, apiKey).execute()
                if (response.isSuccessful) {
                    val json = response.body()
                    if (json != null) {
                        file.writeText(json)
                    }
                }
            }
        }
    }

}