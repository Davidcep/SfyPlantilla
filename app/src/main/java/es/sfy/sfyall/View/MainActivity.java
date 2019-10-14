package es.sfy.sfyall.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.sfy.sfyall.R;
import es.sfy.sfyall.View.Fragment.MainFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionBar);
        setContentView(R.layout.activity_main);

        MainFragment firstFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_main, firstFragment).commit();
    }
}
