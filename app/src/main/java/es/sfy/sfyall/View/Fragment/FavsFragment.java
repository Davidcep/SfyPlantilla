package es.sfy.sfyall.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.sfy.sfyall.Model.Data.Movie;
import es.sfy.sfyall.Model.Utils.BaseFragment;
import es.sfy.sfyall.R;

public class FavsFragment extends BaseFragment {

    private List<String> titles;

    @BindView(R.id.lv_favs) ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivBack = getActivity().findViewById(R.id.iv_back);
        ImageView ivFav = getActivity().findViewById(R.id.iv_fav);

        ivBack.setVisibility(View.VISIBLE);
        ivFav.setVisibility(View.INVISIBLE);

        List<Movie> movies = Movie.findWithQuery(Movie.class, "SELECT * FROM MOVIE");

        titles = new ArrayList<>();
        for(Movie m : movies){
            titles.add(m.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext()
                , android.R.layout.simple_list_item_1, titles);


        listView.setAdapter(adapter);

        ivBack.setOnClickListener(v -> onClickToMain());

    }

    @Override
    public void onClickToMain() {
        super.onClickToMain();
    }
}
