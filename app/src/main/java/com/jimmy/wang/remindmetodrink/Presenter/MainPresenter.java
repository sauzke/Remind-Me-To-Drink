package com.jimmy.wang.remindmetodrink.Presenter;

import com.jimmy.wang.remindmetodrink.View.View;

public class MainPresenter implements Presenter {
    private final View view;

    public MainPresenter(final View view){
        this.view = view;
    }
}
