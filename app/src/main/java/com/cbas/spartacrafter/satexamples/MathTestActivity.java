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
    private static int answerAId;
    private static int answerBId;
    private static int answerCId;
    private static int answerDId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        answerOptions.setVisibility(RadioGroup.INVISIBLE);
        questionView = (LinearLayout) findViewById(R.id.question_view);
        answerAId = answerOptions.getChildAt(0).getId();
        answerBId = answerOptions.getChildAt(1).getId();
        answerCId = answerOptions.getChildAt(2).getId();
        answerDId = answerOptions.getChildAt(3).getId();
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
            Elements elements = result.select(".answer-body");
            for(int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i).firstElementSibling();
                String text;
                if(!e.select(".Wirisformula").isEmpty()) {
                    //((RadioButton) answerOptions.getChildAt(i)).setText("\"" + e.child(0).child(0).attr("data-mathml") + " with MathML" + "\"");
                    ((RadioButton) answerOptions.getChildAt(i)).setText("\"" + e.child(0).child(0).attr("src") + " as Image" + "\"");
                } else {
                    ((RadioButton) answerOptions.getChildAt(i)).setText("\"" + e.text() + " as Text" + "\"");
                }
            }
            answerOptions.setVisibility(RadioGroup.VISIBLE);
            progress.dismiss();
            progress = null;
        }
    }

    public void onSelectAnswerA(View v) {
    }

    public void onSelectAnswerB(View v) {

    }

    public void onSelectAnswerC(View v) {

    }

    public void onSelectAnswerD(View v) {

    }
}
