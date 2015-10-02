package com.udacity.gradle.joke.preview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by elnoxvie on 16/9/15.
 */
public class PreviewActivity extends AppCompatActivity{

    public static final String EXTRA_JOKE = "com.udacity.gradle.joke.preview.joke";
    public static final String EXTRA_AUTHOR = "com.udacity.gradle.joke.preview.author";
    private TextView mJokeView;
    private TextView mAuthorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        mAuthorView = (TextView)findViewById(R.id.author_textview);
        mJokeView = (TextView) findViewById(R.id.joke_textview);

        String jokeText;
        String authorText;

        if (savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            jokeText = bundle.getString(EXTRA_JOKE);
            authorText = bundle.getString(EXTRA_AUTHOR);
        }else{
            jokeText = savedInstanceState.getString(EXTRA_JOKE);
            authorText = savedInstanceState.getString(EXTRA_AUTHOR);
        }
        
        if (TextUtils.isEmpty(jokeText)){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        
        mJokeView.setText(jokeText);
        mAuthorView.setText(authorText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_JOKE, mJokeView.getText().toString());
        outState.putString(EXTRA_AUTHOR, mAuthorView.getText().toString());
    }
}
