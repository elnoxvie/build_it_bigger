package com.udacity.gradle.builditbigger.service;

import android.os.Build;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.udacity.gradle.joke.backend.myApi.MyApi;
import com.udacity.gradle.joke.backend.myApi.model.MyBean;

import java.io.IOException;

/**
 * Created by elnoxvie on 16/9/15.
 */
public class JokeEndpointManager {

    private static JokeEndpointManager sEndpoint;

    public static JokeEndpointManager getInstance() {
        if (sEndpoint == null) {
            sEndpoint = new JokeEndpointManager();
        }

        return sEndpoint;
    }

    private MyApi mService;

    public JokeEndpointManager() {

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), getJsonFactory(), null)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        if (Settings.isProduction()){
            builder.setRootUrl(Settings.ENDPOINT_PRODUCTION_ROOT_URL);
        }else{
            builder.setRootUrl(Settings.ENDPOINT_ROOT_URL);
        }

        mService = builder.build();
    }

    public MyBean getJoke() throws IOException {
        return mService.getJoke().execute();
    }

    private static final JsonFactory getJsonFactory() {
        // only for honeycomb and newer versions
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new AndroidJsonFactory();
        } else {
            return new JacksonFactory();
        }
    }

}
