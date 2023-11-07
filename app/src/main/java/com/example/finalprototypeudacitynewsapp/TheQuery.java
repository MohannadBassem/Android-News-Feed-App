package com.example.finalprototypeudacitynewsapp;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TheQuery
{

    public TheQuery()
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<TheNewsAppGetMethods> TheDataFetcherNews(String requestUrl)
    {
        URL url = TheLinkCreator(requestUrl);

        String TheResponseForJson = null;
        try {
            TheResponseForJson = TheRequestOfHttpProtocol(url);

        } catch (IOException e) {
            Log.e("Error in Log", "Error closing the input stream for the program", e);
        }

        List<TheNewsAppGetMethods> TheNewsFeature = TheJsonFeatures(TheResponseForJson);
        return TheNewsFeature;
    }

    private static List<TheNewsAppGetMethods> TheJsonFeatures(String TheResponseForJson)
    {

        List<TheNewsAppGetMethods> TheNewsFeature = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(TheResponseForJson);
            JSONObject jsonObject = root.getJSONObject("response");
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONArray tags =jsonObject1.getJSONArray("tags");
                JSONObject firstAuthor =tags.getJSONObject(0);
                TheNewsFeature.add(new TheNewsAppGetMethods(jsonObject1.getString("webTitle"), jsonObject1.getString("sectionName"), jsonObject1.getString("webPublicationDate"), jsonObject1.getString("webUrl"),firstAuthor.getString("webTitle"),tags.length()));
            }
        }
        catch (JSONException e)
        {
            Log.e("TheQuery", "Problem parsing JSON File", e);
        }
        return TheNewsFeature;
    }

    private static URL TheLinkCreator(String stringUrl)
    {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("3", "Error with Link", e);
        }
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String TheRequestOfHttpProtocol(URL url) throws IOException
    {
        String TheResponseForJson = "";

        if (url == null)
        {
            return TheResponseForJson;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try
        {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200)
            {
                inputStream = httpURLConnection.getInputStream();
                TheResponseForJson = readFromStream(inputStream);
            } else
                {
                Log.e("Error1", "Error response code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e)
        {
            Log.e("Error2", "Problem retrieving the earthquake JSON results.", e);
        }
        finally
        {
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return TheResponseForJson;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private static String readFromStream(InputStream inputStream) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String TheReader = bufferedReader.readLine();
            while (TheReader != null)
            {
                stringBuilder.append(TheReader);
                TheReader = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }
}

