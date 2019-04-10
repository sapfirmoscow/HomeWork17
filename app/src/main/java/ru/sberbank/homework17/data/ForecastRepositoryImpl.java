package ru.sberbank.homework17.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public Observable<Forecasts> getForecast(String latitude, String longitude) {
        if (isOnline()) {
            return getForecastsFromNetwork();
        } else return getForecastsFromDataBase();

    }

    private Observable<Forecasts> getForecastsFromNetwork() {
        return apiMapper.getForecast()
                .doOnNext(s -> weatherDB.getWeatherDAO().setDailyForecasts(s.getForecasts()));

    }

    private Observable<Forecasts> getForecastsFromDataBase() {

        List<Forecast> list = weatherDB.getWeatherDAO().getForecast();
        Forecasts forecasts = new Forecasts();
        forecasts.setForecasts(list);
        return Observable.just(forecasts);
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
}
