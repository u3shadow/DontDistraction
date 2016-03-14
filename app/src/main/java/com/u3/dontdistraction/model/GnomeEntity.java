package com.u3.dontdistraction.model;

/**
 * Created by ${U3} on 2016/3/14.
 */
public class GnomeEntity {
    public GnomeEntity(String gnome){
        setGenomeString(gnome);
    }
    public String getGenomeString() {
        return genomeString;
    }

    public void setGenomeString(String genomeString) {
        this.genomeString = genomeString;
    }

    private String genomeString;

}
