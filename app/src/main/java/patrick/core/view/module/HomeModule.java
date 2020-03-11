package patrick.core.view.module;

import dagger.Module;
import dagger.Provides;
import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.factory.HomeViewModelFactory;
import patrick.core.viewmodel.interactors.HelloInteractor;
import patrick.core.viewmodel.interactors.HomeInteractor;

/**
 * Define HomeFragment-specific dependencies here.
 */
@Module
public class HomeModule {

    @Provides
    HomeViewModelFactory provideHelloViewModelFactory(
            HomeInteractor home,
            HelloInteractor hello,
            SchedulersFacade sv) {
        return new HomeViewModelFactory(home, hello, sv);
    }
}
