package com.bcpk.docket;

/**
 * Created by ledzee on 3/24/15.
 */

//Creating custom class of a location and giving properties such as image, title and description

public class Location {
    private int imageId;
    private String title;
    private String desc;
    private String webAdd;

    public Location(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;

    }


    //creating a constructor to be used in ResourceTabFragment
    public Location(int imageId, String title, String desc, String webAdd) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.webAdd = webAdd;
    }

    public void setWebAdd(String webAdd) {
        this.webAdd = webAdd;
    }

    public String getWebAdd() {
        return webAdd;
    }



    public int getImageId() {

        return imageId;
    }

    public void setImageId(int imageId) {

        this.imageId = imageId;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}
