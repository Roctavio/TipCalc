package com.octavio.ruber.tipcalc;

import android.app.Application;

/**
 * Created by Ruber Octavio on 6/17/2016.
 */
public class TipCalcClass extends Application {
    private final String ABOUT_URL="https://about.me/adriancatalan";

    public String getAboutUrl() { //colocamos un getter mas no setter
        return ABOUT_URL;
    }
}
