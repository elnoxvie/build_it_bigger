package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.loader.JokeLoader;
import com.udacity.gradle.joke.backend.myApi.model.MyBean;
import com.udacity.gradle.joke.preview.PreviewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final int JOKE_ACTIVITY_REQUEST_CODE = 200;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.jokeButtonView)
    public void tellJoke() {

        JokeLoader loader = new JokeLoader();
        loader.setCallback(mCallback);
        loader.execute();
    }

    private String mJokeText;
    private JokeLoader.LoaderCallback mCallback = new JokeLoader.LoaderCallback() {
        @Override
        public void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPostExecute(MyBean myBean) {
            mProgressBar.setVisibility(View.GONE);
            if (myBean == null) {
                Toast.makeText(getContext(), R.string.err_unable_to_get_joke, Toast.LENGTH_SHORT).show();
            } else {
                mJokeText = myBean.getData();
                startJokePreview();
            }
        }
    };

    private void startJokePreview(){
        Intent intent = new Intent(getActivity(), PreviewActivity.class);
        intent.putExtra(PreviewActivity.EXTRA_JOKE, mJokeText);
        startActivityForResult(intent, JOKE_ACTIVITY_REQUEST_CODE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
