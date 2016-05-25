/**
 * Created by smith1246 on 5/12/2016.
 */

package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MathTestActivity extends AppCompatActivity {
    private static LinearLayout questionView;
    private static RadioGroup answerOptions;
    private static int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        questionView = (LinearLayout) findViewById(R.id.question_view);
        boolean isCalc = getIntent().getBooleanExtra("isCalc", true);
        int questionNum = getIntent().getIntExtra("questionNum", 2);
        new Test().execute(TestSelectActivity.TEST_SELECT + ((isCalc)?(TestSelectActivity.CALC):(TestSelectActivity.NO_CALC)) + questionNum);
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
            Elements elements = result.select(".answer-body");
            for(int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i).firstElementSibling();
                RadioButton b = (RadioButton) answerOptions.getChildAt(i);
                if(!e.select(".Wirisformula").isEmpty()) {
                    b.setText(elements.text());
                } else {
                    b.setText(elements.text());
                }
            }
            answerOptions.setVisibility(View.VISIBLE);
            progress.dismiss();
            progress = null;
        }
    }

    public void onClickNext(View v) {

    }

    public void onClickPrev(View v) {

    }

    public void onClickAnswerA(View v) {

    }

    public void onClickAnswerB(View v) {

    }

    public void onClickAnswerC(View v) {

    }

    public void onClickAnswerD(View v) {

    }
}
