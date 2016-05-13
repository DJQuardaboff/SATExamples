/**
 * Created by smith1246 on 5/2/2016.
 */
package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MathIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_intro);
    }

    public void startMathCalcTest(View v) {
        Intent calcTest = new Intent(this, MathTestActivity.class);
        calcTest.putExtra("isCalc", true);
        startActivity(calcTest);
    }

    public void startMathNoCalcTest(View v) {
        Intent calcTest = new Intent(this, MathTestActivity.class);
        calcTest.putExtra("isCalc", false);
        startActivity(calcTest);
    }

    /*
    private static class IntroText extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setTitle("Getting Stuff From The Webs\u2122");
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("https://collegereadiness.collegeboard.org/sample-questions/math").get();
                Element element = document.getElementsByClass("field-items").first();
                String temp = element.getAllElements().get(2).text();
                temp += "";
                return temp;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }
    }*/
}
