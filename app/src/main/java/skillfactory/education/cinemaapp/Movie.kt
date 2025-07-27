package skillfactory.education.cinemaapp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie (

    @param:JsonProperty("Title") val title: String,
    @param:JsonProperty("Year") val year: String,
    @param:JsonProperty("Rated") val rated: String,
    @param:JsonProperty("Released") val released: String,
    @param:JsonProperty("Runtime") val runtime: String,
    @param:JsonProperty("Genre") val genre: String,
    @param:JsonProperty("Director") val director: String,
    @param:JsonProperty("Writer") val writer: String,
    @param:JsonProperty("Actors") val actors: String,
    @param:JsonProperty("Plot") val plot: String,
    @param:JsonProperty("Language") val language: String,
    @param:JsonProperty("Country") val country: String,
    @param:JsonProperty("Awards") val awards: String,
    @param:JsonProperty("Poster") val poster: String,
    @param:JsonProperty("imdbRating") val imdbRating: String,
    @param:JsonProperty("Type") val type: String,
    @param:JsonProperty("BoxOffice") val boxOffice: String

)