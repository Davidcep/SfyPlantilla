package es.sfy.sfyall.Presenter;

import java.util.List;

import es.sfy.sfyall.Model.Data.Movie;

public class DetailsPresenterImpl implements DetailsPresenter.presenter {

    private Movie movie;
    private DetailsPresenter.view view;

    public DetailsPresenterImpl(Movie movies, DetailsPresenter.view view) {
        this.movie = movies;
        this.view = view;
    }

    @Override
    public void saveMovie() {
        movie.save();
    }
}
