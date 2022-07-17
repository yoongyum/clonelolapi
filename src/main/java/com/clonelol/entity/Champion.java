package com.clonelol.entity;

import lombok.Data;

import javax.persistence.Embedded;
import java.util.ArrayList;

@Data
public class Champion {
    public String version;
    public String id;
    public String key;
    public String name;
    public String title;
    public String blurb;
    @Embedded
    private Info info;
    public Image image;
    public ArrayList<String> tags;
    public String partype;
    public Stats stats;
}
