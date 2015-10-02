package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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

    InterstitialAd mInterstitial;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        View view = root.findViewById(R.id.adView);
        AdsUtility.loadAds(view);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initializeInterstitialId(context);
    }

    private void initializeInterstitialId(final Context context){
        mInterstitial = new InterstitialAd(context);
        mInterstitial.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startJokePreview();
            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("2E8E41377B96159BDF8C0B3E1B3E295B")
                .build();

        mInterstitial.loadAd(adRequest);
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


                //We will show the interstitial if it is loaded otherwise, we will start the joke preview instead.
                if (mInterstitial.isLoaded()){
                    mInterstitial.show();
                }else{
                    startJokePreview();
                }
            }
        }
    };

    private void startJokePreview(){


        //we will re-initialize the interstitial again so that
        //the next time the button is click, the interstitial will be shown
        initializeInterstitialId(getContext());

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
