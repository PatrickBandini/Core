package patrick.core.viewmodel.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.HomeViewModel;
import patrick.core.viewmodel.interactors.HelloInteractor;
import patrick.core.viewmodel.interactors.HomeInteractor;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final HelloInteractor loadHelloInteractor;
    private final HomeInteractor loadHomeInteractor;
    private final SchedulersFacade schedulersFacade;

    public HomeViewModelFactory(HomeInteractor home, HelloInteractor hello, SchedulersFacade sf) {
        this.loadHelloInteractor = hello;
        this.loadHomeInteractor = home;
        this.schedulersFacade = sf;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(loadHomeInteractor, loadHelloInteractor,schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
