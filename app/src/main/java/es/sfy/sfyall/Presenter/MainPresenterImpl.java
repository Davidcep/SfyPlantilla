package es.sfy.sfyall.Presenter;

import android.util.Log;

import java.net.UnknownHostException;

import es.sfy.sfyall.Model.Data.Response;
import es.sfy.sfyall.Model.Retrofit.NetworkClient;
import es.sfy.sfyall.Model.Retrofit.Service;
import es.sfy.sfyall.Model.Utils.HttpCustom;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MainPresenterImpl implements MainPresenter.Presenter{

    private MainPresenter.View fragmentInterface;

    public MainPresenterImpl(MainPresenter.View fragmentInterface) {
        this.fragmentInterface = fragmentInterface;
    }

    @Override
    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<Response> getObservable(){

        //API KEY: f663e4c56cc039c837109c82c78bbd69
        Observable<Response> mov = NetworkClient.getRetrofit()
                .create(Service.class)
                .getMovies("f663e4c56cc039c837109c82c78bbd69")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return mov;
    }

    public Observer<Response> getObserver(){
        Observer<Response> response = new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("test", "onSubscribe");
            }

            @Override
            public void onNext(Response response) {
                Log.d("test", "onNext");
                fragmentInterface.showMovies(response);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("test", "onError");
                e.printStackTrace();

                if(e instanceof HttpCustom){
                    String msg = ((HttpCustom) e).getErrorMessage();
                    fragmentInterface.catchError(msg);
                }
                if (e instanceof UnknownHostException){
                    fragmentInterface.catchError("Check your internet connection");
                }
            }

            @Override
            public void onComplete() {
                Log.d("test", "onComplete");
            }
        };

        return response;
    }
}
