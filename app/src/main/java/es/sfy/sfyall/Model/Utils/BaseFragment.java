package es.sfy.sfyall.Model.Utils;

import androidx.fragment.app.Fragment;
import es.sfy.sfyall.R;
import es.sfy.sfyall.View.Fragment.FavsFragment;
import es.sfy.sfyall.View.Fragment.MainFragment;

public abstract class BaseFragment extends Fragment {

    public void onClickToFav(){

        FavsFragment favsFrag = new FavsFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, favsFrag)
                .addToBackStack(null)
                .commit();
    }

    public void onClickToMain(){

        MainFragment nextFrag= new MainFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, nextFrag)
                .addToBackStack(null)
                .commit();
    }
}
