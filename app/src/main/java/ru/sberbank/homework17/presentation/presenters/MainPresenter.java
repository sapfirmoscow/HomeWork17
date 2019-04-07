package ru.sberbank.homework17.presentation.presenters;

import ru.sberbank.homework17.domain.entity.Forecasts;
import ru.sberbank.homework17.presentation.presenters.base.BasePresenter;
import ru.sberbank.homework17.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {
    interface View extends BaseView {
        void displayWeather(Forecasts forecasts);
    }
}
