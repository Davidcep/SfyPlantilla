package es.sfy.sfyall.Model.Retrofit;

import es.sfy.sfyall.Model.Data.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Observable<Response> getMovies(@Query("api_key") String api_key);
}
