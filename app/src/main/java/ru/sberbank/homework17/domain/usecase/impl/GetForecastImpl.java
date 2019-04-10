package ru.sberbank.homework17.domain.usecase.impl;

import io.reactivex.Observable;
import ru.sberbank.homework17.domain.entity.Forecasts;
import ru.sberbank.homework17.domain.repository.ForecastRepository;
import ru.sberbank.homework17.domain.usecase.GetForecast;

public class GetForecastImpl implements GetForecast {

    private ForecastRepository mForecastRepository;


    public GetForecastImpl(ForecastRepository forecastRepository) {
        mForecastRepository = forecastRepository;
    }

    @Override
    public Observable<Forecasts> getWeather() {
        return mForecastRepository.getForecast("", "");
    }

}
