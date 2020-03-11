package patrick.core.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import patrick.core.model.HomeModel;
import patrick.core.model.remote.Hello;
import patrick.core.model.remote.HelloService;
import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.interactors.HelloInteractor;
import patrick.core.viewmodel.interactors.HomeInteractor;
import patrick.core.viewmodel.utility.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HomeViewModel extends ViewModel {

    private final HelloInteractor loadHelloInteractor;
    private final HomeInteractor loadHomeInteractor;
    private final SchedulersFacade schedulersFacade;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<Response<?>> response = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();
    private final MutableLiveData<Response<?>> responseCall = new MutableLiveData<>();
    private final MutableLiveData<Boolean> statusCall = new MutableLiveData<>();

    private Retrofit retrofit;

    public HomeViewModel(HomeInteractor home, HelloInteractor hello, SchedulersFacade sf) {
        this.loadHomeInteractor = home;
        this.loadHelloInteractor = hello;
        this.schedulersFacade = sf;
        this.retrofit = this.loadHelloInteractor.getRetrofit();
    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

    public void readHome() {
        Single<HomeModel> single = this.loadHomeInteractor.read();
        disposable.add(single
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        loadingStatus.setValue(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        loadingStatus.setValue(false);
                    }
                })
                .subscribe(new Consumer<HomeModel>() {
                    @Override
                    public void accept(@NonNull HomeModel home) throws Exception {
                        response.setValue(Response.success(home));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        response.setValue(Response.error(throwable));
                    }
                })
        );
    }

    public void callHello(String id, String modalita) {
        HelloService helloService = this.retrofit.create(HelloService.class);
        Call<Hello> call = helloService.xml(id,modalita);
        call.enqueue(new Callback<Hello>() {
            @Override
            public void onResponse(Call<Hello> call, retrofit2.Response<Hello> response) {
                String risposta = response.body().getHelloWorld();
                Single<String> single = Single.just(risposta);
                disposable.add(single
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                statusCall.setValue(true);
                            }
                        })
                        .doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                statusCall.setValue(false);
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String risposta) throws Exception {
                                responseCall.setValue(Response.success(risposta));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                responseCall.setValue(Response.error(throwable));
                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<Hello> call, Throwable t) {
                responseCall.setValue(Response.error(t));
            }
        });
    }

    public MutableLiveData<Response<?>> getResponse() {
        return this.response;
    }

    public MutableLiveData<Boolean> getLoadingStatus() {
        return this.loadingStatus;
    }

    public MutableLiveData<Response<?>> getResponseCall() {
        return this.responseCall;
    }

    public MutableLiveData<Boolean> getStatusCall() {
        return this.statusCall;
    }

}
