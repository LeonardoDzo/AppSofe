package com.leonardodurazo.appsofe.Model;

/**
 * Created by Leonardo Durazo on 19/11/2015.
 */
public class todos {
    private String _id;
    private String title;
    private boolean isCompleted;

    public todos(String _id, String title, boolean isCompleted) {
        this._id = _id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
