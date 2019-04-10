package ru.sberbank.homework17.data.net.retrofit;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.sberbank.homework17.WeatherApplication;
import ru.sberbank.homework17.domain.entity.Forecasts;


public class ApiMapper {
    private RetrofitHelper mHelper;

    public ApiMapper(RetrofitHelper helper) {
        mHelper = helper;
    }

    public Observable<Forecasts> getForecast() {
        return mHelper.getService().getCurrentForecast(WeatherApplication.X_YANDEX_API_KEY, "55.75222", "37.61556", false, false, 7).subscribeOn(Schedulers.io());

    }

}
