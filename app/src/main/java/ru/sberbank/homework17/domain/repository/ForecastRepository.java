package ru.sberbank.homework17.domain.repository;

import io.reactivex.Observable;
import ru.sberbank.homework17.domain.entity.Forecasts;

public interface ForecastRepository {
    Observable<Forecasts> getForecast(String latitude, String longitude);
}
