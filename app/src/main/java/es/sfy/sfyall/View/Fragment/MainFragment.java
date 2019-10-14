package es.sfy.sfyall.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.sfy.sfyall.Model.Data.Movie;
import es.sfy.sfyall.Model.Data.Response;
import es.sfy.sfyall.Model.Utils.BaseFragment;
import es.sfy.sfyall.Presenter.MainPresenter;
import es.sfy.sfyall.Presenter.MainPresenterImpl;
import es.sfy.sfyall.R;
import es.sfy.sfyall.View.Adapter.RecyclerAdapter;
import es.sfy.sfyall.View.Adapter.RecyclerViewClick;

public class MainFragment extends BaseFragment implements MainPresenter.View, RecyclerViewClick,
        SwipeRefreshLayout.OnRefreshListener {

    private List<Movie> movies;

    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager viewManager;
    private MainPresenterImpl mainPresenterImpl;

    @BindView(R.id.rv_fragment) RecyclerView rv;
    @BindView(R.id.swipe_main) SwipeRefreshLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewManager = new GridLayoutManager(getContext(), 2);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe.setOnRefreshListener(this);

        refreshData();

        ImageView ivBack = getActivity().findViewById(R.id.iv_back);
        ImageView ivFav = getActivity().findViewById(R.id.iv_fav);

        ivFav.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.INVISIBLE);

        ivFav.setOnClickListener(v -> onClickToFav());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(viewManager);
        rv.setAdapter(viewAdapter);
    }

    @Override
    public void onClickToFav() {
        super.onClickToFav();
    }

    @Override
    public void showMovies(Response movies) {

        this.movies = movies.getMovies();
        viewAdapter = new RecyclerAdapter(movies.getMovies(), this);
        rv.setAdapter(viewAdapter);
    }

    @Override
    public void catchError(String msg) {

        if(getView() != null){
            Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onClick(int position) {

        //Map of Serializable Object
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movies.get(position));

        DetailsFragment nextFrag= new DetailsFragment();
        nextFrag.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, nextFrag)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);

        refreshData();
    }

    public void refreshData(){
        movies = new ArrayList<>();

        mainPresenterImpl = new MainPresenterImpl(this);
        mainPresenterImpl.getMovies();
        viewAdapter = new RecyclerAdapter(movies, this);

        swipe.setRefreshing(false);
    }
}
