package avnpost.com.classmodel;

public class StateNewsModel {

    private String State_id,State_title,State_date,State_author,State_author_name,State_image;

    public StateNewsModel(String state_id, String state_title, String state_date, String state_author, String state_author_name, String state_image) {
        State_id = state_id;
        State_title = state_title;
        State_date = state_date;
        State_author = state_author;
        State_author_name = state_author_name;
        State_image = state_image;
    }

    public String getState_id() {
        return State_id;
    }

    public void setState_id(String state_id) {
        State_id = state_id;
    }

    public String getState_title() {
        return State_title;
    }

    public void setState_title(String state_title) {
        State_title = state_title;
    }

    public String getState_date() {
        return State_date;
    }

    public void setState_date(String state_date) {
        State_date = state_date;
    }

    public String getState_author() {
        return State_author;
    }

    public void setState_author(String state_author) {
        State_author = state_author;
    }

    public String getState_author_name() {
        return State_author_name;
    }

    public void setState_author_name(String state_author_name) {
        State_author_name = state_author_name;
    }

    public String getState_image() {
        return State_image;
    }

    public void setState_image(String state_image) {
        State_image = state_image;
    }
}
