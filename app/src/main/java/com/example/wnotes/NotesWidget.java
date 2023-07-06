package com.example.wnotes;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NotesWidget extends AppWidgetProvider {

    ArrayList<String> notizen = MainActivity.getNotizenliste();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            Intent intent = new Intent(context, NotesWidgetService.class);
            intent.putStringArrayListExtra("notizen", notizen);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.notes_widget);
            view.setRemoteAdapter(R.id.listview, intent);

            appWidgetManager.updateAppWidget(id, view);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
