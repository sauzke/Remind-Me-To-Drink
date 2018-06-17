package com.jimmy.wang.remindmetodrink.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.jimmy.wang.remindmetodrink.Model.WaterModel;
import com.jimmy.wang.remindmetodrink.Presenter.CalcPresenter;


public class AlertDialogFragment extends DialogFragment implements View {
    String message;
    static CalculateActivity view;
    WaterModel model;

    static AlertDialogFragment newInstance(String message,CalculateActivity cView){
        view = cView;
        AlertDialogFragment f = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("message",message);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.message = getArguments().getString("message");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Results: " + this.message + " Cups of water per day")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Confirm water
                        view.saveAmount(Double.parseDouble(message));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        return builder.create();
    }

    public void viewAction(String action,String optional){
    }
}
