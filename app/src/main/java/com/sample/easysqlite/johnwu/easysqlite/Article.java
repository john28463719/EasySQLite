package com.sample.easysqlite.johnwu.easysqlite;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sample.easysqlite.johnwu.easysqlite.BR;


/**
 * Created by JohnWu on 2016/5/17.
 */
public class Article extends BaseObservable {

    private String title;
    private String subtitle;

    public Article(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getSubtitle() {
        return subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        notifyPropertyChanged(BR.subtitle);
    }

    public static final String[] TITLE = {"john", "stanley", "sharon", "regina", "strike",
            "winne", "tracy", "lesly", "hans", "aaron", "mama", "freda"};

    public static final String[] SUBTITLE = {"john", "stanley", "sharon", "regina", "strike",
            "winne", "tracy", "lesly", "hans", "aaron", "mama", "freda"};
}
