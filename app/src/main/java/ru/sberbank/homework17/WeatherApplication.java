package ru.sberbank.homework17;

import android.app.Application;

import ru.sberbank.homework17.di.ContextModule;
import ru.sberbank.homework17.di.DaggerDataComponent;
import ru.sberbank.homework17.di.DataComponent;
import ru.sberbank.homework17.di.DataModule;
import ru.sberbank.homework17.di.WeatherComponent;


public class WeatherApplication extends Application {

    public static final String X_YANDEX_API_KEY = "fedbfe89-15ea-45e9-8374-2b0a634afbb1";
    private static WeatherComponent mWeatherComponent;
    private static DataComponent mDataComponent;

    public static WeatherComponent getWeatherComponent() {
        return mWeatherComponent;
    }

    public static DataComponent getDataComponent() {
        return mDataComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        mDataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

}
