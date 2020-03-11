package patrick.core.viewmodel.interactors;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import patrick.core.model.SecondModel;

public class SecondInteractor {

    private final SecondModel secondModel;

    @Inject
    public SecondInteractor(SecondModel secondModel) {
        this.secondModel = secondModel;
    }

    public Single<SecondModel> read() {
        return this.secondModel.getSecondModel();
    }

    public void write(String helloWorld) {
        this.secondModel.setHelloWorld(helloWorld);
    }

}
