package avnpost.com.classmodel;

public class TechnologyNewsModel {

    private String Technology_id,Technology_title,Technology_date,Technology_author,Technology_author_name,Technology_image;

    public TechnologyNewsModel(String technology_id, String technology_title, String technology_date, String technology_author, String technology_author_name, String technology_image) {
        Technology_id = technology_id;
        Technology_title = technology_title;
        Technology_date = technology_date;
        Technology_author = technology_author;
        Technology_author_name = technology_author_name;
        Technology_image = technology_image;
    }

    public String getTechnology_id() {
        return Technology_id;
    }

    public void setTechnology_id(String technology_id) {
        Technology_id = technology_id;
    }

    public String getTechnology_title() {
        return Technology_title;
    }

    public void setTechnology_title(String technology_title) {
        Technology_title = technology_title;
    }

    public String getTechnology_date() {
        return Technology_date;
    }

    public void setTechnology_date(String technology_date) {
        Technology_date = technology_date;
    }

    public String getTechnology_author() {
        return Technology_author;
    }

    public void setTechnology_author(String technology_author) {
        Technology_author = technology_author;
    }

    public String getTechnology_author_name() {
        return Technology_author_name;
    }

    public void setTechnology_author_name(String technology_author_name) {
        Technology_author_name = technology_author_name;
    }

    public String getTechnology_image() {
        return Technology_image;
    }

    public void setTechnology_image(String technology_image) {
        Technology_image = technology_image;
    }
}
