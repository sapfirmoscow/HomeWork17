package ru.sberbank.homework17.domain.usecase;

import ru.sberbank.homework17.domain.entity.Forecasts;
import ru.sberbank.homework17.domain.usecase.base.UseCase;

public interface GetForecast extends UseCase {
    interface Callback {
        void onMessageRetrieved(Forecasts forecasts);

        void onRetrievalFailed();
    }

}
