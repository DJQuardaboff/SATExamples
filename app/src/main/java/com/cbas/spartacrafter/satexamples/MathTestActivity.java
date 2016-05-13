/**
 * Created by smith1246 on 5/12/2016.
 */

package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MathTestActivity extends AppCompatActivity{
    private RadioGroup answerOptions;
    private LinearLayout questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        System.out.println("Intent Bundle: " + getIntent().getBooleanExtra("isCalc", false));
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        questionView = (LinearLayout) findViewById(R.id.question_view);
        new Test().execute(TestSelectActivity.URL + TestSelectActivity.MATH_CALC_TEST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class Test extends AsyncTask<String, Void, Document> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(getApplicationContext());
            progress.setTitle("Getting Stuff From The Webs\u2122");
            progress.setMessage("Loading...");
            progress.setIndeterminate(false);
            progress.show();
        }

        @Override
        protected Document doInBackground(String... params) {
            try {
                return Jsoup.connect(params[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Document result) {
            Element element = result.getElementsByClass("field-items").first();
            String temp = element.getAllElements().get(2).text();
            temp += "";
            progress.dismiss();
        }
    }
}
