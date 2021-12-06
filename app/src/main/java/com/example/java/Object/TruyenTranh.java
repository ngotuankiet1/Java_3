package com.example.java.Object;

import org.json.JSONException;
import org.json.JSONObject;

public class TruyenTranh {
    private String NameComic,NumberChapter,LinkPicture;


    public TruyenTranh(){}
    public TruyenTranh(JSONObject o)throws JSONException {
        NameComic = o.getString("NameComic");
        NumberChapter = o.getString("NumberChapter");
        LinkPicture = o.getString("LinkPicture");
    }

    public TruyenTranh(String nameComic, String numberChapter, String linkPicture) {
        NameComic = nameComic;
        NumberChapter = numberChapter;
        LinkPicture = linkPicture;
    }

    public String getNameComic() {
        return NameComic;
    }

    public void setNameComic(String nameComic) {
        NameComic = nameComic;
    }

    public String getNumberChapter() {
        return NumberChapter;
    }

    public void setNumberChapter(String numberChapter) {
        NumberChapter = numberChapter;
    }

    public String getLinkPicture() {
        return LinkPicture;
    }

    public void setLinkPicture(String linkPicture) {
        LinkPicture = linkPicture;
    }
}
