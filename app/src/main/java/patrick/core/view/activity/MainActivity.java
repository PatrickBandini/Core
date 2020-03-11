package patrick.core.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import patrick.core.R;
import patrick.core.view.fragment.HomeFragment;
import patrick.core.view.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector, LifecycleOwner {

    public static final String BASE_URL = "http://google.it";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(this.toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new HomeFragment(), HomeFragment.TAG).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SecondFragment(), SecondFragment.TAG).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        boolean check = true;
        HomeFragment homeFragment = new HomeFragment();
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if (currentFragment instanceof SecondFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment, HomeFragment.TAG).commit();
                check = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (check) {
            super.onBackPressed();
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
