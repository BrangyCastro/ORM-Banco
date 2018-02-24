package com.facci.brangycastro.examen.confi;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by brangycastro on 24/2/18.
 */

public class MiAplicacion extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

}
