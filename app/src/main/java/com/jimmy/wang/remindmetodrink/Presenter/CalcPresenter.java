package com.jimmy.wang.remindmetodrink.Presenter;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.support.v7.app.AppCompatActivity;


import com.jimmy.wang.remindmetodrink.Model.WaterAmount;
import com.jimmy.wang.remindmetodrink.Model.WaterModel;
import com.jimmy.wang.remindmetodrink.View.AlertDialogFragment;
import com.jimmy.wang.remindmetodrink.View.CalculateActivity;
import com.jimmy.wang.remindmetodrink.View.View;

import static java.lang.Double.parseDouble;

public class CalcPresenter implements Presenter{
    private final View view;

    public CalcPresenter(final View view){
        this.view = view;
    }

    // Calculate water required based on weight lbs and age, returns in cups
    public double calculateWater(double weight, int age){
        double result = 0.0;
        double multiplier = 0.0;

        switch (age){
            case 0: multiplier = 40;
                break;
            case 1: multiplier = 35;
                break;
            case 2: multiplier = 30;
                break;
            default: multiplier = 0;
                break;
        }

        result = weight/2.2*multiplier/28.3/8;

        return result;
    }

    // Gets inputs from view
    public void getInput(EditText weight, SeekBar age, ToggleButton weightUnitToggle){
        if(!weight.getText().toString().matches("")) {
            Double weightAmount = Double.parseDouble(weight.getText().toString());

            // convert from KG to LBS
            if (!weightUnitToggle.isChecked()) {
                weightAmount = weightAmount * 2.20462262185;
            }

            int ageRange = age.getProgress();

            Double waterAmount = calculateWater(weightAmount, ageRange);

            view.viewAction("showDialog", String.format("%.2f",waterAmount));
        }
    }

    // Calls model to save result
    public void saveResult(Double amount, WaterModel model){
        if(model.selectAmount(1) == null) {
            model.insertAmount(amount);
            System.out.println("Inserted");
        }
        else{
            WaterAmount wAmount = new WaterAmount(1,amount,"");
            model.updateAmount(wAmount);
            System.out.println("Updated");
        }

        System.out.println(model.selectAmount(1).getAmount());

        view.viewAction("goToMain","");
    }
}
