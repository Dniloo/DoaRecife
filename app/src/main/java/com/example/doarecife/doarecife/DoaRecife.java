package com.example.doarecife.doarecife;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jose mario on 14/11/2017.
 */

public class DoaRecife extends Application {
    EventBus eventBus;

    public void onCreate(){
        super.onCreate();

        eventBus = new EventBus();
    }

    public EventBus getEventBus(){
        return eventBus;
    }

}
