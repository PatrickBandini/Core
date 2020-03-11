package patrick.core.viewmodel.interactors;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import patrick.core.model.HomeModel;

public class HomeInteractor {

    private final HomeModel homeModel;

    @Inject
    public HomeInteractor(HomeModel homeModel) {
        this.homeModel = homeModel;
    }

    public Single<HomeModel> read() {
        return this.homeModel.getHomeModel();
    }

    public void write(String helloWorld) {
        this.homeModel.setHelloWorld(helloWorld);
    }

}
