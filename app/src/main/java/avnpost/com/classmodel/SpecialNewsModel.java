package avnpost.com.classmodel;

public class SpecialNewsModel {

    private String Special_id,Special_title,Special_date,Special_author,Special_author_name,Special_image;

    public SpecialNewsModel(String special_id, String special_title, String special_date, String special_author, String special_author_name, String special_image) {
        Special_id = special_id;
        Special_title = special_title;
        Special_date = special_date;
        Special_author = special_author;
        Special_author_name = special_author_name;
        Special_image = special_image;
    }

    public String getSpecial_id() {
        return Special_id;
    }

    public void setSpecial_id(String special_id) {
        Special_id = special_id;
    }

    public String getSpecial_title() {
        return Special_title;
    }

    public void setSpecial_title(String special_title) {
        Special_title = special_title;
    }

    public String getSpecial_date() {
        return Special_date;
    }

    public void setSpecial_date(String special_date) {
        Special_date = special_date;
    }

    public String getSpecial_author() {
        return Special_author;
    }

    public void setSpecial_author(String special_author) {
        Special_author = special_author;
    }

    public String getSpecial_author_name() {
        return Special_author_name;
    }

    public void setSpecial_author_name(String special_author_name) {
        Special_author_name = special_author_name;
    }

    public String getSpecial_image() {
        return Special_image;
    }

    public void setSpecial_image(String special_image) {
        Special_image = special_image;
    }
}
