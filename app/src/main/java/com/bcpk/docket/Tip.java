package com.bcpk.docket;

/**
 * Created by ledzee on 3/24/15.
 */

//This class is no longer used as the TipsFragment now uses a webview

//Creating custom class of a location and giving properties such as image, title and description

public class Tip {
    //private int imageId;
    //private String title;
    private String desc;

    public Tip(String desc) {
        //this.imageId = imageId;
        //this.title = title;
        this.desc = desc;
    }


    public String getDesc() {

        return desc;
    }



    public void setDesc(String desc) {

        this.desc = desc;
    }


}
