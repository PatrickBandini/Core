package patrick.core.model;

import io.reactivex.rxjava3.core.Single;

public class SecondModel {

    private String helloWorld;

    public SecondModel() {
        this.helloWorld = "Second Hello Model";
    }

    public String getHelloWorld() {
        return this.helloWorld;
    }

    public Single<SecondModel> getSecondModel() {
        return Single.just(this);
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }

}
