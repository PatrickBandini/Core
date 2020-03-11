package patrick.core.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import patrick.core.model.SecondModel;
import patrick.core.rx.SchedulersFacade;
import patrick.core.viewmodel.interactors.SecondInteractor;
import patrick.core.viewmodel.utility.Response;

public class SecondViewModel extends ViewModel {

    private final SecondInteractor secondInteractor;
    private final SchedulersFacade schedulersFacade;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<Response<?>> response = new MutableLiveData<>();

    public SecondViewModel(SecondInteractor secondInteractor, SchedulersFacade schedulersFacade) {
        this.secondInteractor = secondInteractor;
        this. schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

    public void loadSecond() {
        Single<SecondModel> single = this.secondInteractor.read();
        disposable.add(single
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe(new Consumer<SecondModel>() {
                    @Override
                    public void accept(@NonNull SecondModel second) throws Exception {
                        response.setValue(Response.success(second));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        response.setValue(Response.error(throwable));
                    }
                })
        );
    }

    public MutableLiveData<Response<?>> getResponse() {
        return this.response;
    }

}
