package com.jimmy.wang.remindmetodrink.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.jimmy.wang.remindmetodrink.Presenter.CalcPresenter;


public class AlertDialogFragment extends DialogFragment implements View {
    String message;
    CalcPresenter presenter;

    static AlertDialogFragment newInstance(String message){

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
        this.presenter = new CalcPresenter(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Results: " + this.message + " Cups of water per day")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Confirm water
                        presenter.saveResult(Double.parseDouble(message));
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
        if(action.matches("goToMain"))
            goToMain();
    }

    public void goToMain(){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}
