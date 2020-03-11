package patrick.core.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import patrick.core.App;
import patrick.core.model.HomeModel;
import patrick.core.model.SecondModel;
import patrick.core.view.activity.MainActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * iniettare qui le dipendenze a livello di applicazione
 */
@Module
class AppModule {

    @Provides
    Context provideContent(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // inserire qui i model

    @Singleton
    @Provides
    HomeModel provideHomeService(Context context) {
        return new HomeModel(context);
    }

    @Singleton
    @Provides
    SecondModel provideSecondService() {
        return new SecondModel();
    }


}
