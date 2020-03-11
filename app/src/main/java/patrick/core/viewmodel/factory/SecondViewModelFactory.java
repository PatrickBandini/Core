package patrick.core.viewmodel.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.SecondViewModel;
import patrick.core.viewmodel.interactors.SecondInteractor;

public class SecondViewModelFactory implements ViewModelProvider.Factory {

    private SecondInteractor secondInteractor;
    private final SchedulersFacade schedulersFacade;

    public SecondViewModelFactory(SecondInteractor secondInteractor, SchedulersFacade schedulersFacade) {
        this.secondInteractor = secondInteractor;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SecondViewModel.class)) {
            return (T) new SecondViewModel(secondInteractor, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
