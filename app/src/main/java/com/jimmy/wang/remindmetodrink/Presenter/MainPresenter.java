package com.jimmy.wang.remindmetodrink.Presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.jimmy.wang.remindmetodrink.Model.Alert;
import com.jimmy.wang.remindmetodrink.Model.WaterAmount;
import com.jimmy.wang.remindmetodrink.Model.WaterModel;
import com.jimmy.wang.remindmetodrink.R;
import com.jimmy.wang.remindmetodrink.View.CalculateActivity;
import com.jimmy.wang.remindmetodrink.View.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

public class MainPresenter implements Presenter {
    private final View view;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int totalDrinkingHours = 12;

    public MainPresenter(final View view){
        this.view = view;
    }

    public String getValue(WaterModel model){
        WaterAmount amount = model.selectAmount(1);

        String result = "Press Calculate to Begin";

        if(amount != null) {
            try{
                Date timeStamp = dateFormat.parse(amount.getTimestamp());
                Date now = new Date();
                long diff = now.getTime() - timeStamp.getTime();

                long diffHours = diff / (60 * 60 * 1000) % 24;

                double remainingHours = totalDrinkingHours - diffHours;

                double remainingPercentage = remainingHours/totalDrinkingHours;

                double remaingingWater = remainingPercentage * amount.getAmount();

                if(remaingingWater < 0)
                    remaingingWater = 0;

                if(remainingPercentage < 0)
                    remainingPercentage = 0;

                result = remaingingWater + "/" + amount.getAmount() + " oz remaining, " + (int) remainingPercentage*100 + "%";
            }
            catch(ParseException e){
                System.out.println("Date Parse Exception");
            }
        }

        return result;
    }

    public void startCountDown(final TextView countDownText,final WaterModel model){
        if(model.selectAlert(1) != null){
            WaterAmount amount = model.selectAmount(1);
            Alert alert = model.selectAlert(1);

            try {
                Date timeStamp = dateFormat.parse(amount.getTimestamp());
                Date now = new Date();

                long diff = now.getTime() - timeStamp.getTime();

                long timeToNext = (totalDrinkingHours*60*60*1000) - diff - (3 - alert.getCount())*3*60*60*1000;

                System.out.println(timeToNext);

                new CountDownTimer(timeToNext, 1000) {
                    public void onTick(long millisUntilFinished) {
                        String time = String.format("%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                        countDownText.setText("Next Alert: " + time);
                    }

                    public void onFinish() {
                        if(model.selectAlert(1).getCount() < 3){
                            Alert alert = new Alert(1,model.selectAlert(1).getCount() + 1);
                            model.updateAlert(alert);

                            countDownText.setText("Time to Drink");
                            view.viewAction("notify","Time to Drink!");
                        }
                    }
                }.start();
            }
            catch(ParseException e){
            }
        }
    }
}
