package patrick.core.view.module;

import dagger.Module;
import dagger.Provides;
import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.factory.SecondViewModelFactory;
import patrick.core.viewmodel.interactors.SecondInteractor;

@Module
public class SecondModule {

    @Provides
    SecondViewModelFactory provideSecondViewModelFactory (
            SecondInteractor secondInteractor,
            SchedulersFacade schedulersFacade
    ) {
        return new SecondViewModelFactory(secondInteractor, schedulersFacade);
    }

}
