package skillfactory.education.cinemaapp.omdbapi

import android.content.Context
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import skillfactory.education.cinemaapp.Movie
import java.io.File

class ReaderMovie {

    fun readMovieFromFile(context: Context, id: String): Movie? {

        val file = File(context.filesDir, "$id.json")
        if (file.exists()) {
            val json = file.readText()
            return jacksonObjectMapper().readValue<Movie>(json)
        } else return null

    }

}