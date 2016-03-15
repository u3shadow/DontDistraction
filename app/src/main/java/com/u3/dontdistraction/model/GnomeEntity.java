package com.u3.dontdistraction.model;

/**
 * Created by ${U3} on 2016/3/14.
 */
public class GnomeEntity {
    public GnomeEntity(String gnome){
        setGnomeString(gnome);
    }
    public String getGnomeString() {
        return gnomeString;
    }

    public void setGnomeString(String gnomeString) {
        this.gnomeString = gnomeString;
    }

    private String gnomeString;

}
