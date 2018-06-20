package com.jimmy.wang.remindmetodrink.View;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jimmy.wang.remindmetodrink.Model.WaterModel;
import com.jimmy.wang.remindmetodrink.Presenter.MainPresenter;
import com.jimmy.wang.remindmetodrink.R;

public class MainActivity extends AppCompatActivity implements View{
    private MainPresenter presenter;
    private WaterModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calButton = findViewById(R.id.calculateButton);
        TextView percentage = findViewById(R.id.waterPercentage);
        TextView timer = findViewById(R.id.waterTimer);
        calButton.setText("Calculate");
        timer.setText("");

        presenter = new MainPresenter(this);
        model = new WaterModel(this);

        percentage.setText(presenter.getValue(model));
        presenter.startCountDown(timer,model);

    }

    public void calcClick(android.view.View view){
        Intent intent = new Intent(this,CalculateActivity.class);
        startActivity(intent);
    }

    public void viewAction(String action,String optional){
        if(action.matches("notify"))
            notify(optional);
    }

    public void notify(String message){
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (getApplicationContext()).setContentTitle("RMTD").setContentText(message).
                setContentTitle("Remind me to Drink").setSmallIcon(R.drawable.ic_launcher_foreground).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }
}
