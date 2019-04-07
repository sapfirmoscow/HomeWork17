package ru.sberbank.homework17.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import ru.sberbank.homework17.R;
import ru.sberbank.homework17.di.ContextModule;
import ru.sberbank.homework17.di.DaggerWeatherComponent;
import ru.sberbank.homework17.di.ViewModule;
import ru.sberbank.homework17.di.WeatherModule;
import ru.sberbank.homework17.domain.entity.Forecast;
import ru.sberbank.homework17.domain.entity.Forecasts;
import ru.sberbank.homework17.presentation.presenters.MainPresenter;
import ru.sberbank.homework17.presentation.ui.recycler.MyAdapter;
import ru.sberbank.homework17.presentation.ui.recycler.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Inject
    MainPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInjector();
        initRecyclerView();
        initListeners();
    }

    private void initInjector() {
        DaggerWeatherComponent.builder()
                .weatherModule(new WeatherModule())
                .contextModule(new ContextModule(this))
                .viewModule(new ViewModule(this))
                .build().inject(this);
    }

    private void initListeners() {
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Forecast forecast = mMyAdapter.getItemForecast(position);
                Intent intent = DetailActivity.newIntent(MainActivity.this, forecast);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void displayWeather(Forecasts forecasts) {

        mMyAdapter.setData(forecasts.getForecasts());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }
}
