package patrick.core.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import patrick.core.view.fragment.HomeFragment;
import patrick.core.view.fragment.SecondFragment;
import patrick.core.view.module.HomeModule;
import patrick.core.view.module.SecondModule;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector(modules = SecondModule.class)
    abstract SecondFragment bindSecondFragment();
}
