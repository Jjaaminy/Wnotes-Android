package com.example.wnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

public class NotesRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private ArrayList<String> notizenliste;
    public NotesRemoteViewsFactory(Context applicationContext,  ArrayList<String> notizenliste) {
        context = applicationContext;
        this.notizenliste = notizenliste;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount(){
        return notizenliste.size();
    }

    @Override
    public RemoteViews getViewAt(int position){
        if (notizenliste.isEmpty() || position >= notizenliste.size()) {
            return null;
        }

        @SuppressLint("RemoteViewLayout") RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.notes_widget_item);
        views.setTextViewText(R.id.note_textview,notizenliste.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
