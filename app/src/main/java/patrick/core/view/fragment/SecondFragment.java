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
import patrick.core.model.SecondModel;
import patrick.core.viewmodel.SecondViewModel;
import patrick.core.viewmodel.factory.SecondViewModelFactory;
import patrick.core.viewmodel.utility.Response;

public class SecondFragment extends MyLifecycleFragment<MutableLiveData<?>> {

    public static final String TAG = "SecondFragment";

    @Inject
    SecondViewModelFactory viewModelFactory;

    @Inject
    Context context;

    @BindView(R.id.text_second)
    TextView text;

    private SecondViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SecondViewModel.class);

        observeResponse(viewModel.getResponse());

        viewModel.loadSecond();
    }

    @Override
    public void processResponse(Response<?> response) {
        switch (response.status) {
            case SUCCESS:
                SecondModel secondModel = (SecondModel) response.data;
                text.setText(secondModel.getHelloWorld());
                break;
            case ERROR:
                break;
        }
    }

}
