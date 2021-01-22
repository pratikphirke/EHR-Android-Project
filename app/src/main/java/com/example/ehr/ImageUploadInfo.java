package com.example.ehr;


public class ImageUploadInfo {

    public String catName;

    public String catURL;

    public ImageUploadInfo()
    {

    }

    public ImageUploadInfo(String name, String url) {

        this.catName = name;
        this.catURL= url;
    }

    public String getCatName() {
        return catName;
    }

    public String getCatURL() {
        return catURL;
    }

}

