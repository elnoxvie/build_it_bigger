package com.udacity.gradle.builditbigger.loader;

import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.service.JokeEndpointManager;
import com.udacity.gradle.joke.backend.myApi.model.MyBean;

import java.io.IOException;

/**
 * Created by elnoxvie on 16/9/15.
 */
public class JokeLoader extends AsyncTask<Void,Void,MyBean>{

    public interface LoaderCallback{
        public void onPreExecute();
        public void onPostExecute(MyBean myBean);
    }

    private LoaderCallback mCallback;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mCallback != null){
            mCallback.onPreExecute();
        }
    }


    public void setCallback(LoaderCallback callback){
        mCallback = callback;
    }


    @Override
    protected MyBean doInBackground(Void... voids) {
        try {
            return JokeEndpointManager.getInstance().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(MyBean myBean) {
        super.onPostExecute(myBean);
        if (mCallback != null){
            mCallback.onPostExecute(myBean);
        }
    }
}
