package com.example.wnotes;

import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

public class NotesWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ArrayList<String> notizen = MainActivity.getNotizenliste();
        return new NotesRemoteViewsFactory(this.getApplicationContext(),notizen);
    }
}
