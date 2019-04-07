package ru.sberbank.homework17.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ru.sberbank.homework17.WeatherApplication;
import ru.sberbank.homework17.data.cache.room.WeatherDB;
import ru.sberbank.homework17.data.net.retrofit.ApiMapper;
import ru.sberbank.homework17.domain.entity.Forecast;
import ru.sberbank.homework17.domain.entity.Forecasts;
import ru.sberbank.homework17.domain.repository.ForecastRepository;


public class ForecastRepositoryImpl implements ForecastRepository {

    @Inject
    ApiMapper apiMapper;
    @Inject
    WeatherDB weatherDB;
    @Inject
    Context mContext;


    public ForecastRepositoryImpl() {

        WeatherApplication.getDataComponent().inject(this);

    }

    @Override
    public Forecasts getForecast(String latitude, String longitude) throws IOException {
        List<Forecast> forecast = null;
        if (isOnline()) {
            forecast = getForecastsFromNetwork();
        } else {
            forecast = getForecastsFromDataBase();
        }

        Forecasts forecasts = new Forecasts();
        forecasts.setForecasts(forecast);
        return forecasts;
    }

    private List<Forecast> getForecastsFromNetwork() throws IOException {
        List<Forecast> forecasts = apiMapper.getForecastSync().getForecasts();

        weatherDB.getWeatherDAO().setDailyForecasts(forecasts);
        return forecasts;
    }

    private List<Forecast> getForecastsFromDataBase() {

        return weatherDB.getWeatherDAO().getForecast();
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
}
