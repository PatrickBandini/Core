package patrick.core.viewmodel.interactors;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class HelloInteractor {

    @Inject
    Retrofit retrofit;

    @Inject
    public HelloInteractor() {}

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}
