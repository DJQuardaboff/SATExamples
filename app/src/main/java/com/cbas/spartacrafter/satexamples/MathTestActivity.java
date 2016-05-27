/**
 * Created by smith1246 on 5/12/2016.
 */

package com.cbas.spartacrafter.satexamples;

import android.app.ProgressDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_test);
        answerOptions = (RadioGroup) findViewById(R.id.answer_options);
        questionView = (WebView) findViewById(R.id.question_view);
        boolean isCalc = getIntent().getBooleanExtra("isCalc", true);
        int questionNum = getIntent().getIntExtra("questionNum", 1);
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
            Elements temp = result.select("div.field-item even");
            final String newPage = temp.html();
            HtmlCleaner cleaner = new HtmlCleaner();
            CleanerProperties props = cleaner.getProperties();
            TagNode tagNode = new HtmlCleaner(props).clean(newPage);
            SimpleHtmlSerializer htmlSerializer = new SimpleHtmlSerializer(props);
            questionView.loadDataWithBaseURL(null,htmlSerializer.getAsString(tagNode), "text/html", "charset=UTF-8",null);
            temp = result.select(".answer-body");
            for(int i = 0; i < temp.size(); i++) {
                Element answer = temp.get(i);
                RadioButton b = (RadioButton) answerOptions.getChildAt(i);
                if(!answer.select(".Wirisformula").isEmpty()) {
                    //unfinished
                } else {
                    b.setText(answer.text());
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
