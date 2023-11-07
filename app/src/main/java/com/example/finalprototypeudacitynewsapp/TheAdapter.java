package com.example.finalprototypeudacitynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TheAdapter extends ArrayAdapter<TheNewsAppGetMethods>
{

    public TheAdapter(Context context, List<TheNewsAppGetMethods> news)
    {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View TheView, ViewGroup parent)
    {
        View view = TheView;
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.theitems, parent, false);
        }
        TheNewsAppGetMethods theNewsAppGetMethods = getItem(position);

        TextView theNewsHeadText = view.findViewById(R.id.TheNewsHeadText);

        theNewsHeadText.setText(theNewsAppGetMethods.getTheNewsTitle());

        TextView theNewsTypeText = view.findViewById(R.id.TheNewsTypeText);

        theNewsTypeText.setText(theNewsAppGetMethods.getTheNewsType());

        TextView theNewsAuthorText = view.findViewById(R.id.TheNewsAuthorText);

        if (theNewsAppGetMethods.getTheNewsAuthorsCounter() > 1)

            theNewsAuthorText.setText("by " + theNewsAppGetMethods.getTheNewsAuthor() + ", and Other Authors");

        else

            theNewsAuthorText.setText("by " + theNewsAppGetMethods.getTheNewsAuthor());

        TextView dateTextView = view.findViewById(R.id.TheNewsDateText);

        String TheString1 = theNewsAppGetMethods.getTheNewsDate();

        String TheString2 = TheString1.substring(0, TheString1.indexOf("T"));

        dateTextView.setText(TheString2);

        return view;
    }
}

