package ru.sberbank.homework17.di;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.homework17.presentation.presenters.MainPresenter;

@Module
public class ViewModule {
    private final MainPresenter.View mView;


    public ViewModule(MainPresenter.View view) {
        mView = view;
    }

    @Provides
    public MainPresenter.View provideView() {
        return mView;
    }
}
