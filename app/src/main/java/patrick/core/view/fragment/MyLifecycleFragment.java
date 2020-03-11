package patrick.core.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import io.reactivex.rxjava3.annotations.Nullable;
import patrick.core.viewmodel.utility.Response;

public class MyLifecycleFragment<T extends MutableLiveData<?>> extends Fragment implements LifecycleOwner {

    public void observeResponse(MutableLiveData<Response<?>> response) {
        response.observe(this, new Observer<Response<?>>() {
            @Override
            public void onChanged(@Nullable Response<?> response) {
                processResponse(response);
            }
        });
    }

    public void observeLoadingStatus(MutableLiveData<Boolean> load) {
        load.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loading) {
                processLoadingStatus(loading);
            }
        });
    }

    public void processResponse(Response<?> response) {}
    public void processLoadingStatus(Boolean isLoading) {}

}
