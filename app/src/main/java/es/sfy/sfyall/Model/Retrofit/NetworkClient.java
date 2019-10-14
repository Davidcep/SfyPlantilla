package es.sfy.sfyall.Model.Retrofit;

import android.util.Log;

import java.io.IOException;

import es.sfy.sfyall.Model.Utils.HttpCustom;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    //KEY: f663e4c56cc039c837109c82c78bbd69
    public static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .build();

        return retrofit;
    }

   public static OkHttpClient okHttpClient(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);

                    if (response.code() != 200) {
                        throw new HttpCustom(response);
                    }
                    return response;
                })
                .build();

        return okHttpClient;
   }
}
