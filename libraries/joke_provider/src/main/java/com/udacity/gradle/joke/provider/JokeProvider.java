package com.udacity.gradle.joke.provider;

public class JokeProvider {

    private static JokeProvider sJokeProvider;

    public static JokeProvider getInstance(){
        if (sJokeProvider == null){
            sJokeProvider = new JokeProvider();
        }

        return sJokeProvider;
    }

    public String getJoke(){
        return "derp";
    }
}
