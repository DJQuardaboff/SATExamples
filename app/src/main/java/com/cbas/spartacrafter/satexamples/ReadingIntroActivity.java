package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by smith1246 on 5/2/2016.
 */
public class ReadingIntroActivity extends AppCompatActivity {
    private static ProgressDialog progressDialog;
    private static Button calcAllowedButton;
    private static Button calcNotAllowedButton;

    public static View createView(LayoutInflater inflater, ViewGroup container) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.math_intro, container, false);
        calcAllowedButton = (Button) layout.findViewById(R.id.calc_allowed_button);
        calcAllowedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        calcNotAllowedButton = (Button) layout.findViewById(R.id.calc_not_allowed_button);
        calcNotAllowedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return layout;
    }

    public void startReadingTest(View v) {

    }
}
