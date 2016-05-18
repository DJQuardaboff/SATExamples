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

public class MathTestActivity extends AppCompatActivity {
    private static LinearLayout questionView;
    private static RadioGroup answerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        questionView = (LinearLayout) findViewById(R.id.question_view);
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        answerOptions.setVisibility(RadioGroup.INVISIBLE);
        boolean isCalc = getIntent().getBooleanExtra("isCalc", true);
        int questionNum = getIntent().getIntExtra("questionNum", 1);
        new Test().execute(TestSelectActivity.URL + ((isCalc)?(TestSelectActivity.MATH_CALC_TEST):(TestSelectActivity.MATH_NO_CALC_TEST)) + questionNum);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        questionView = null;
        answerOptions = null;
    }

    private class Test extends AsyncTask<String, Void, Document> {
        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MathTestActivity.this);
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
            answerOptions.setVisibility(RadioGroup.VISIBLE);
            progress.dismiss();
            progress = null;
        }
    }
}
