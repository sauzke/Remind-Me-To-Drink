package com.jimmy.wang.remindmetodrink.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jimmy.wang.remindmetodrink.Presenter.MainPresenter;
import com.jimmy.wang.remindmetodrink.R;

public class MainActivity extends AppCompatActivity implements View{
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calButton = findViewById(R.id.calculateButton);
        TextView percentage = findViewById(R.id.waterPercentage);
        TextView timer = findViewById(R.id.waterTimer);
        calButton.setText("Calculate");
        percentage.setText("Press Calulate to begin");
        timer.setText("");

        presenter = new MainPresenter(this);
    }

    public void calcClick(android.view.View view){
        Intent intent = new Intent(this,CalculateActivity.class);
        startActivity(intent);
    }

    public void settingClick(android.view.View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}
