package com.hackerkernel.user.sqrfactor;

import java.io.Serializable;

public class CreditsClass implements Serializable {
    private String article;
    private String totalViews,firstWeekViews;
    private String credits;

    public CreditsClass(String article, String totalViews, String firstWeekViews, String credits) {
        this.article = article;
        this.totalViews = totalViews;
        this.firstWeekViews = firstWeekViews;
        this.credits = credits;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
    }


    public String getfirstWeekViews() {
        return firstWeekViews;
    }

    public void setfirstWeekViews(String firstWeekViews) {
        firstWeekViews = firstWeekViews;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
