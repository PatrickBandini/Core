package patrick.core.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import patrick.core.view.activity.MainActivity;

@Module
public abstract class BuildersModule {

    //Inserisci qui Module e Activity

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity bindMainActivity();

//    Es:
//    @ContributesAndroidInjector(modules = SecondaModule.class)
//    abstract SecondaActivity bindSecondaActivity();

}
