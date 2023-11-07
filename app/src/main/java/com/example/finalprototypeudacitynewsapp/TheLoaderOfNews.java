package com.example.finalprototypeudacitynewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class TheLoaderOfNews extends AsyncTaskLoader<List<TheNewsAppGetMethods>>
{

    private String TheLink;

    public TheLoaderOfNews(Context context, String TheUrlLink)
    {
        super(context);
        this.TheLink = TheUrlLink;
    }

    @Override
    protected void onStartLoading()
    {
        forceLoad();
    }

    @Override
    public List<TheNewsAppGetMethods> loadInBackground()
    {
        if (TheLink == null) {
            return null;
        }

        List<TheNewsAppGetMethods> news = TheQuery.TheDataFetcherNews(TheLink);
        return news;
    }
}

