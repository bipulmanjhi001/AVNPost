package avnpost.com.classmodel;

public class BussinessNewsModel {

    private String bussiness_id,bussiness_title,bussiness_date,bussiness_author,bussiness_author_name,bussiness_image;

    public BussinessNewsModel(String bussiness_id, String bussiness_title, String bussiness_date, String bussiness_author, String bussiness_author_name, String bussiness_image) {
        this.bussiness_id = bussiness_id;
        this.bussiness_title = bussiness_title;
        this.bussiness_date = bussiness_date;
        this.bussiness_author = bussiness_author;
        this.bussiness_author_name = bussiness_author_name;
        this.bussiness_image = bussiness_image;
    }

    public String getBussiness_id() {
        return bussiness_id;
    }

    public void setBussiness_id(String bussiness_id) {
        this.bussiness_id = bussiness_id;
    }

    public String getBussiness_title() {
        return bussiness_title;
    }

    public void setBussiness_title(String bussiness_title) {
        this.bussiness_title = bussiness_title;
    }

    public String getBussiness_date() {
        return bussiness_date;
    }

    public void setBussiness_date(String bussiness_date) {
        this.bussiness_date = bussiness_date;
    }

    public String getBussiness_author() {
        return bussiness_author;
    }

    public void setBussiness_author(String bussiness_author) {
        this.bussiness_author = bussiness_author;
    }

    public String getBussiness_author_name() {
        return bussiness_author_name;
    }

    public void setBussiness_author_name(String bussiness_author_name) {
        this.bussiness_author_name = bussiness_author_name;
    }

    public String getBussiness_image() {
        return bussiness_image;
    }

    public void setBussiness_image(String bussiness_image) {
        this.bussiness_image = bussiness_image;
    }
}
