package com.jimmy.wang.remindmetodrink.View;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jimmy.wang.remindmetodrink.Presenter.CalcPresenter;
import com.jimmy.wang.remindmetodrink.R;

public class CalculateActivity extends AppCompatActivity implements com.jimmy.wang.remindmetodrink.View.View {
    private CalcPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        presenter = new CalcPresenter(this);

        TextView viewLabel = findViewById(R.id.viewName);
        TextView weightLabel = findViewById(R.id.weightLabel);
        TextView ageLabel = findViewById(R.id.ageLabel);
        EditText weightText = findViewById(R.id.weightField);
        ToggleButton weightToggle = findViewById(R.id.weightToggle);
        SeekBar ageBar = findViewById(R.id.ageBar);
        Button calcButton = findViewById(R.id.calcButton);
        TextView ageRange1 = findViewById(R.id.ageRange1);
        TextView ageRange2 = findViewById(R.id.ageRange2);
        TextView ageRange3 = findViewById(R.id.ageRange3);

        viewLabel.setText("Water Calculator");
        weightLabel.setText("Select Weight");
        ageLabel.setText("Select Age Range");
        weightText.setText("");
        weightToggle.setTextOn("LBS");
        weightToggle.setTextOff("KG");
        weightToggle.setChecked(true);
        ageBar.setMax(2);
        calcButton.setText("Calculate");
        ageRange1.setText("< 30");
        ageRange2.setText("30 - 55");
        ageRange3.setText("55+");
    }

    public void calcButtonClick(View view){
        EditText weightText = findViewById(R.id.weightField);
        SeekBar ageBar = findViewById(R.id.ageBar);
        ToggleButton weightToggle = findViewById(R.id.weightToggle);

        presenter.getInput(weightText,ageBar,weightToggle);
    }

    public void showDialog(String message){
        DialogFragment newFrag = AlertDialogFragment.newInstance(message);
        newFrag.show(getFragmentManager(),"dialog");
    }

    public void viewAction(String action,String param){
        if(action == "showDialog"){
            showDialog(param);
        }
    }
}
