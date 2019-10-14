package es.sfy.sfyall.View.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.sfy.sfyall.Model.Data.Movie;
import es.sfy.sfyall.Model.Utils.BaseFragment;
import es.sfy.sfyall.Presenter.DetailsPresenter;
import es.sfy.sfyall.Presenter.DetailsPresenterImpl;
import es.sfy.sfyall.R;

public class DetailsFragment extends BaseFragment implements DetailsPresenter.view {

    private Movie movie;
    private DetailsPresenterImpl detailsPresenter;

    @BindView(R.id.iv_details) ImageView iv;
    @BindView(R.id.tv_title_details) TextView tvTitle;
    @BindView(R.id.tv_descr_details) TextView tvDesc;
    @BindView(R.id.btn_save) Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//      Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        movie = (Movie) bundle.getSerializable("movie");

        detailsPresenter = new DetailsPresenterImpl(movie, this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivBack = getActivity().findViewById(R.id.iv_back);
        ImageView ivFav = getActivity().findViewById(R.id.iv_fav);

        ivBack.setVisibility(View.VISIBLE);
        ivFav.setVisibility(View.INVISIBLE);

        ivBack.setOnClickListener(v -> onClickToMain());

        Picasso.get().load("https://image.tmdb.org/t/p/original"+ movie.getImg())
                .into(iv);
        tvTitle.setText(movie.getTitle());
        tvDesc.setText(movie.getOverview());

    }

    @OnClick(R.id.btn_save) void saveMov(){
        detailsPresenter.saveMovie();

        Snackbar.make(getView(), "Saved", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClickToMain() {
        super.onClickToMain();
    }
}
