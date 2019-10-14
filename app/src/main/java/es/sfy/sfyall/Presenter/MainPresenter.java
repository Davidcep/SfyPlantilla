package es.sfy.sfyall.Presenter;

import es.sfy.sfyall.Model.Data.Response;

public interface MainPresenter {

    interface Presenter{
        void getMovies();
    }

    interface View{
        void showMovies(Response movies);
        void catchError(String msg);
    }
}
