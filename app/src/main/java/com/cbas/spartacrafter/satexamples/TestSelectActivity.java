package com.cbas.spartacrafter.satexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TestSelectActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://collegereadiness.collegeboard.org/";
    public static final String TEST_SELECT = "https://collegereadiness.collegeboard.org/sample-questions/";
    public static final String MATH_INTRO = "math/";
    public static final String READING_INTRO = "reading/";
    public static final String WRITING_LANGUAGE_INTRO = "writing-language/";
    public static final String ESSAY_INTRO = "essay/";
    public static final String CALC = "math/calculator-permitted/";
    public static final String NO_CALC = "math/calculator-not-permitted/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_select);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }*/

    public void startMathIntro(View v) {
        startActivity(new Intent(this, MathIntroActivity.class));
    }

    public void startReadingIntro(View v) {
        startActivity(new Intent(this, ReadingIntroActivity.class));
    }

    public void startWritingLanguageIntro(View v) {
        startActivity(new Intent(this, WritingLanguageIntroActivity.class));
    }

    public void startEssayIntro(View v) {
        startActivity(new Intent(this, EssayIntroActivity.class));
    }
}
