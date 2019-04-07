package ru.sberbank.homework17.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.homework17.data.cache.room.WeatherDB;
import ru.sberbank.homework17.data.net.retrofit.ApiMapper;
import ru.sberbank.homework17.data.net.retrofit.RetrofitHelper;

@Module(includes = ContextModule.class)
public class DataModule {

    @Provides
    public WeatherDB provideWeatherDB(Context context) {
        return Room.databaseBuilder(context, WeatherDB.class, "weather")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public ApiMapper provideApiMapper(RetrofitHelper helper) {
        return new ApiMapper(helper);
    }

    @Provides
    public RetrofitHelper provideHelper() {
        return new RetrofitHelper();
    }
}
