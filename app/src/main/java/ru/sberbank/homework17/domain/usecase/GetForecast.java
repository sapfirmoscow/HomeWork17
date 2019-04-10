package ru.sberbank.homework17.domain.usecase;


import io.reactivex.Observable;
import ru.sberbank.homework17.domain.entity.Forecasts;

public interface GetForecast {
    Observable<Forecasts> getWeather();
}
