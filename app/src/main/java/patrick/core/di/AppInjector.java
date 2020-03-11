package patrick.core.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import dagger.android.AndroidInjection;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import patrick.core.App;

public class AppInjector {

    private AppInjector() {}

    public static void init(App app) {
        DaggerAppComponent
                .builder()
                .application(app)
                .build()
                .inject(app);

        app
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                        handleActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(@NonNull Activity activity) {

                    }
                });
    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof HasAndroidInjector) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentCreated(FragmentManager fm, Fragment fragment, Bundle savedInstanceState) {
                    if (fragment instanceof Injectable) {
                        AndroidSupportInjection.inject(fragment);
                    }
                }
            }, true);
        }
    }


}
