package avnpost.com.classmodel;

public class LifeStyleModel {

    private String Life_id,Life_title,Life_date,Life_author,Life_author_name,Life_image;

    public LifeStyleModel(String life_id, String life_title, String life_date, String life_author, String life_author_name, String life_image) {
        Life_id = life_id;
        Life_title = life_title;
        Life_date = life_date;
        Life_author = life_author;
        Life_author_name = life_author_name;
        Life_image = life_image;
    }

    public String getLife_id() {
        return Life_id;
    }

    public void setLife_id(String life_id) {
        Life_id = life_id;
    }

    public String getLife_title() {
        return Life_title;
    }

    public void setLife_title(String life_title) {
        Life_title = life_title;
    }

    public String getLife_date() {
        return Life_date;
    }

    public void setLife_date(String life_date) {
        Life_date = life_date;
    }

    public String getLife_author() {
        return Life_author;
    }

    public void setLife_author(String life_author) {
        Life_author = life_author;
    }

    public String getLife_author_name() {
        return Life_author_name;
    }

    public void setLife_author_name(String life_author_name) {
        Life_author_name = life_author_name;
    }

    public String getLife_image() {
        return Life_image;
    }

    public void setLife_image(String life_image) {
        Life_image = life_image;
    }
}
