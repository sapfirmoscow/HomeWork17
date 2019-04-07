package ru.sberbank.homework17.di;

import dagger.Component;
import ru.sberbank.homework17.data.ForecastRepositoryImpl;

@Component(modules = DataModule.class)
public interface DataComponent {
    void inject(ForecastRepositoryImpl forecastRepository);
}
