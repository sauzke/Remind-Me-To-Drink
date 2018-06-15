package com.jimmy.wang.remindmetodrink.Presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.jimmy.wang.remindmetodrink.View.CalculateActivity;
import com.jimmy.wang.remindmetodrink.View.View;

public class MainPresenter implements Presenter {
    private final View view;

    public MainPresenter(final View view){
        this.view = view;
    }
}
