package skillfactory.education.cinemaapp.omdbapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    fun getMovie(
        @Query("i") id: String,
        @Query("apikey") apiKey: String
    ): Call<String>

}