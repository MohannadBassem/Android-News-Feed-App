package com.example.finalprototypeudacitynewsapp;

public class TheNewsAppGetMethods
{

    String TheNewsType;
    String TheNewsLink;
    String TheNewsDate;
    String TheNewsTitle;
    String TheNewsAuthor;
    int TheNewsAuthorsCounter;

    public TheNewsAppGetMethods(String TheTitleOfNews, String TheTypeOfNews, String TheNewsDate, String TheLinkOfNews, String TheAuthorOfNews, int TheAuthorsCounter)
    {
        this.TheNewsTitle = TheTitleOfNews;
        this.TheNewsDate = TheNewsDate;
        this.TheNewsAuthorsCounter = TheAuthorsCounter;
        this.TheNewsAuthor = TheAuthorOfNews;
        this.TheNewsType = TheTypeOfNews;
        this.TheNewsLink = TheLinkOfNews;
    }

    public String getTheNewsTitle()
    {
        return TheNewsTitle;
    }

    public String getTheNewsLink()
    {
        return TheNewsLink;
    }

    public String getTheNewsDate()
    {
        return TheNewsDate;
    }

    public String getTheNewsType()
    {
        return TheNewsType;
    }

    public int getTheNewsAuthorsCounter()
    {
        return TheNewsAuthorsCounter;
    }

    public String getTheNewsAuthor()
    {
        return TheNewsAuthor;
    }
}

