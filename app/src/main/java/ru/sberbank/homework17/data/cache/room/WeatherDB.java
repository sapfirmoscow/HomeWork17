package ru.sberbank.homework17.data.cache.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.sberbank.homework17.domain.entity.Forecast;

@Database(entities = Forecast.class, version = 2)

public abstract class WeatherDB extends RoomDatabase {

    public abstract WeatherDAO getWeatherDAO();
}
