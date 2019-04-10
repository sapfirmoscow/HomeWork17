package ru.sberbank.homework17.di;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.homework17.data.ForecastRepositoryImpl;
import ru.sberbank.homework17.domain.repository.ForecastRepository;
import ru.sberbank.homework17.presentation.presenters.MainPresenter;
import ru.sberbank.homework17.presentation.presenters.impl.MainPresenterImpl;

@Module
public class WeatherModule {

    @Provides
    public MainPresenter provideMainPresenter(MainPresenter.View view, ForecastRepository forecastRepository) {
        return new MainPresenterImpl(view, forecastRepository);
    }

    @Provides
    public ForecastRepository provideForecastRepository() {
        return new ForecastRepositoryImpl();
    }


}
