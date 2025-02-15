package patrick.core.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import patrick.core.App;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }

    void inject(App app);

}
