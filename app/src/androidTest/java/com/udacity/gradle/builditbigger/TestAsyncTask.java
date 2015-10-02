package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import com.udacity.gradle.builditbigger.loader.JokeLoader;
import com.udacity.gradle.joke.backend.myApi.model.MyBean;

import java.util.concurrent.CountDownLatch;

/**
 * Created by elnoxvie on 1/10/15.
 */
public class TestAsyncTask extends AndroidTestCase{

    public void testAsyncTask() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        JokeLoader loader = new JokeLoader();
        loader.setCallback(new JokeLoader.LoaderCallback() {
            @Override
            public void onPreExecute() {}

            @Override
            public void onPostExecute(MyBean myBean) {
                assertNotNull(myBean.getData());
                latch.countDown();
            }
        });

        loader.execute();
        latch.await();
    }
}
