/**
 * Created by smith1246 on 5/12/2016.
 */

package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleHtmlSerializer;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MathTestActivity extends AppCompatActivity {
    private static WebView questionView;
    private static RadioGroup answerOptions;
    private static int correctAnswer;
    private static int questionNum;
    private static boolean isCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        questionView = (WebView) findViewById(R.id.question_view);
        questionNum = getIntent().getIntExtra("questionNum", 1);
        isCalc = getIntent().getBooleanExtra("isCalc", true);
        getTest();
    }

    private void getTest() {
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
            questionView.loadDataWithBaseURL(null, new SimpleHtmlSerializer(new HtmlCleaner().getProperties()).getAsString(new HtmlCleaner(new HtmlCleaner().getProperties()).clean(result.select("div.col-sm-7").html())), "text/html", "charset=UTF-8", null);
            Elements temp = result.select(".field-name-field-answers").first().child(0).children();
            for(int i = 0; i < temp.size(); i++) {
                Element answer = temp.get(i);
                RadioButton b = (RadioButton) answerOptions.getChildAt(i);
                if(!answer.select(".Wirisformula").isEmpty()) {
                    //unfinished
                } else {
                    b.setText(answer.child(0).child(0).child(0).text());

                }
                if(answer.child(0).child(0).child(1).text().equalsIgnoreCase("No")) {
                    correctAnswer = i;
                }
            }
            answerOptions.setVisibility(View.VISIBLE);
            progress.dismiss();
            progress = null;
        }
    }

    public void onClickNext(View v) {
        questionNum++;
        getTest();
        ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
    }

    public void onClickPrev(View v) {
        questionNum--;
        getTest();
        ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
        ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
    }

    public void onClickAnswerA(View v) {
        if(correctAnswer == 0) {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.GREEN);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        } else {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.RED);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        }
    }

    public void onClickAnswerB(View v) {
        if(correctAnswer == 1) {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.GREEN);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        } else {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.RED);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        }
    }

    public void onClickAnswerC(View v) {
        if(correctAnswer == 2) {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.GREEN);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        } else {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.RED);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.BLACK);
        }
    }

    public void onClickAnswerD(View v) {
        if(correctAnswer == 3) {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.GREEN);
        } else {
            ((RadioButton) answerOptions.getChildAt(0)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(1)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(2)).setTextColor(Color.BLACK);
            ((RadioButton) answerOptions.getChildAt(3)).setTextColor(Color.RED);
        }
    }
}
