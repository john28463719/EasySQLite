package com.sample.easysqlite.johnwu.easysqlite;

/**
 * Created by JohnWu on 2016/5/17.
 */
public class Article {

    private String title;
    private String subtitle;

    public Article(String title,String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public static final String[] TITLE = {"john","stanley","sharon","regina","strike",
            "winne","tracy","lesly","hans","aaron","mama","freda"};

    public static final String[] SUBTITLE = {"john","stanley","sharon","regina","strike",
            "winne","tracy","lesly","hans","aaron","mama","freda"};
}
