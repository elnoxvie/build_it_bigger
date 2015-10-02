package com.udacity.gradle.builditbigger;

import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by elnoxvie on 1/10/15.
 */
public class AdsUtility {
    public static void loadAds(View view){

        //we will load if it's the instance of AdView
        if (view != null && view instanceof AdView){
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            ((AdView)view).loadAd(adRequest);
        }
    }
}
