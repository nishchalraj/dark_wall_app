package com.hackerkernel.user.sqrfactor.Pojo;
import java.io.Serializable;

public class PortfolioClass implements Serializable {
    String articleImageUrl,articleName,articleWirterName,likes,comments;

    public PortfolioClass(String articleImageUrl, String articleName, String articleWirterName, String likes, String comments) {
        this.articleImageUrl = articleImageUrl;
        this.articleName = articleName;
        this.articleWirterName = articleWirterName;
        this.likes = likes;
        this.comments = comments;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        this.articleImageUrl = articleImageUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleWirterName() {
        return articleWirterName;
    }

    public void setArticleWirterName(String articleWirterName) {
        this.articleWirterName = articleWirterName;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
