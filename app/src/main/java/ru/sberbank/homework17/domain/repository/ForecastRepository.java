package ru.sberbank.homework17.domain.repository;

import java.io.IOException;

import ru.sberbank.homework17.domain.entity.Forecasts;

public interface ForecastRepository {
    Forecasts getForecast(String latitude, String longitude) throws IOException;
}
