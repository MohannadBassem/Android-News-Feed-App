package com.example.finalprototypeudacitynewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<TheNewsAppGetMethods>> {
    final private String TheGuardianApiLink = "https://content.guardianapis.com/search";
    private TheAdapter theAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.TheListView);
        textView = (TextView) findViewById(R.id.TheTextView);
        listView.setEmptyView(textView);

        theAdapter = new TheAdapter(this, new ArrayList<TheNewsAppGetMethods>());
        listView.setAdapter(theAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TheNewsAppGetMethods theNewsAppGetMethods = theAdapter.getItem(position);

                Uri uri = Uri.parse(theNewsAppGetMethods.getTheNewsLink());

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(0, null, this);
        }
        else {
            View view = findViewById(R.id.TheProgressBar);
            view.setVisibility(View.GONE);

            textView.setText("Opsssss. There is No Internet Connection Please Try Again");
        }
    }

    @Override
    public Loader<List<TheNewsAppGetMethods>> onCreateLoader(int id, Bundle args)
    {
        String url = buildUri();
        return new TheLoaderOfNews(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<TheNewsAppGetMethods>> loader, List<TheNewsAppGetMethods> NewsMethod)
    {
        View view = findViewById(R.id.TheProgressBar);
        view.setVisibility(View.GONE);

        textView.setText("There is No News Found");

        if (NewsMethod != null && !NewsMethod.isEmpty())
        {
            theAdapter.addAll(NewsMethod);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<TheNewsAppGetMethods>> loader)
    {
        theAdapter.clear();
    }

    public String buildUri()
    {
        Uri baseUri = Uri.parse(TheGuardianApiLink);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "test");
        String url = uriBuilder.toString();
        Log.e("URL TEST", url);
        return url;
    }
}