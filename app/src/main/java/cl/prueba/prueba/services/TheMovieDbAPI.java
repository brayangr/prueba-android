package cl.prueba.prueba.services;

import cl.prueba.prueba.models.Genre;
import cl.prueba.prueba.models.Movies;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbAPI {

    @GET("3/movie/popular")
    Call<Movies> getPopular(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Call<Movies> getTopRated(@Query("api_key") String apiKey);

    @GET("3/genre/movie")
    Call<Genre> getGenre(@Query("api_key") String apiKey);

    @GET("t/p/w500/{imageName}")
    Call<ResponseBody> getImage(@Path("imageName") String imageName);

}
