package avnpost.com.classmodel;

public class InternationalNewsModel {

    private String international_id,international_title,international_date,international_author,international_author_name,international_image;

    public InternationalNewsModel(String international_id, String international_title, String international_date, String international_author, String international_author_name, String international_image) {
        this.international_id = international_id;
        this.international_title = international_title;
        this.international_date = international_date;
        this.international_author = international_author;
        this.international_author_name = international_author_name;
        this.international_image = international_image;
    }

    public String getInternational_id() {
        return international_id;
    }

    public void setInternational_id(String international_id) {
        this.international_id = international_id;
    }

    public String getInternational_title() {
        return international_title;
    }

    public void setInternational_title(String international_title) {
        this.international_title = international_title;
    }

    public String getInternational_date() {
        return international_date;
    }

    public void setInternational_date(String international_date) {
        this.international_date = international_date;
    }

    public String getInternational_author() {
        return international_author;
    }

    public void setInternational_author(String international_author) {
        this.international_author = international_author;
    }

    public String getInternational_author_name() {
        return international_author_name;
    }

    public void setInternational_author_name(String international_author_name) {
        this.international_author_name = international_author_name;
    }

    public String getInternational_image() {
        return international_image;
    }

    public void setInternational_image(String international_image) {
        this.international_image = international_image;
    }
}
