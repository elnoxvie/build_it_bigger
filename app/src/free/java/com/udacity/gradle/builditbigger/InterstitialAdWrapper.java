package com.udacity.gradle.builditbigger;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by elnoxvie on 1/10/15.
 */
public class InterstitialAdWrapper {

    private InterstitialAd mInterstitial;

    public InterstitialAdWrapper(Context context, String interstitialId) {
        mInterstitial = new InterstitialAd(context);
        mInterstitial.setAdUnitId(interstitialId);
    }

    public void load(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("2E8E41377B96159BDF8C0B3E1B3E295B")
                .build();
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
        mInterstitial.loadAd(adRequest);
    }

    public void show(){
        if (mInterstitial.isLoaded()){
            mInterstitial.show();
        }
    }
}
