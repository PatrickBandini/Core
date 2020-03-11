package patrick.core.model;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;

public class HomeModel {

    private String helloWorld;

    public HomeModel(Context context) {
        this.helloWorld = "Hello World";
    }

    public String getHelloWorld() {
        return this.helloWorld;
    }

    public Single<HomeModel> getHomeModel() {
        return Single.just(this);
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }

}
