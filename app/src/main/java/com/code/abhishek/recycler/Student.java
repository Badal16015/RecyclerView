package com.code.abhishek.recycler;

import java.util.UUID;

/**
 * Created by ABHISHEK GUPTA on 11/6/2016.
 */
public class Student {
    int id;
    String title;
    private UUID sId;
    String describe;
    public Student() {

        sId = UUID.randomUUID();
        describe=this.describe;
    }

    public UUID getsId() {
        return sId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
