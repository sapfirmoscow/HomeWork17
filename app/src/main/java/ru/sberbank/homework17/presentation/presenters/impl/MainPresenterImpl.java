package ru.sberbank.homework17.presentation.presenters.impl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.sberbank.homework17.domain.repository.ForecastRepository;
import ru.sberbank.homework17.domain.usecase.GetForecast;
import ru.sberbank.homework17.domain.usecase.impl.GetForecastImpl;
import ru.sberbank.homework17.presentation.presenters.MainPresenter;

public class MainPresenterImpl implements MainPresenter {

    MainPresenter.View mView;
    ForecastRepository mForecastRepository;

    @Inject
    public MainPresenterImpl(View view, ForecastRepository forecastRepository) {
        mView = view;
        mForecastRepository = forecastRepository;
    }


    @Override
    public void resume() {
        mView.showProgress();
        GetForecast getForecast = new GetForecastImpl(mForecastRepository);
        getForecast.getWeather().
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mView.displayWeather(s));

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

}
