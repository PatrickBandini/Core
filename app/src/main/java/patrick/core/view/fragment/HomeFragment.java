package patrick.core.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.annotations.Nullable;
import patrick.core.R;
import patrick.core.model.HomeModel;
import patrick.core.viewmodel.HomeViewModel;
import patrick.core.viewmodel.factory.HomeViewModelFactory;
import patrick.core.viewmodel.utility.Response;

public class HomeFragment extends MyLifecycleFragment<MutableLiveData<?>> {

    public static final String TAG = "HomeFragment";

    @Inject
    HomeViewModelFactory viewModelFactory;

    @Inject
    Context context;

    @BindView(R.id.text_home)
    TextView text;

    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        observeLoadingStatus(viewModel.getLoadingStatus());
        observeResponse(viewModel.getResponse());

        viewModel.readHome();
    }

    public void processResponse(Response<?> response) {
        switch (response.status) {
            case SUCCESS:
                HomeModel homeModel = (HomeModel) response.data;
                text.setText(homeModel.getHelloWorld());
                break;
            case ERROR:
                break;
        }
    }

    public void processLoadingStatus(Boolean isLoading) {
        if (isLoading) {

        } else {

        }
    }

}
