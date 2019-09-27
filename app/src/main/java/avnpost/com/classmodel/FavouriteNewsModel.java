package avnpost.com.classmodel;

public class FavouriteNewsModel {
    private String Favourite_id,Favourite_title,Favourite_date,Favourite_author,Favourite_author_name,Favourite_image;

    public FavouriteNewsModel(String favourite_id, String favourite_title, String favourite_date, String favourite_author, String favourite_author_name, String favourite_image) {
        Favourite_id = favourite_id;
        Favourite_title = favourite_title;
        Favourite_date = favourite_date;
        Favourite_author = favourite_author;
        Favourite_author_name = favourite_author_name;
        Favourite_image = favourite_image;
    }

    public String getFavourite_id() {
        return Favourite_id;
    }

    public void setFavourite_id(String favourite_id) {
        Favourite_id = favourite_id;
    }

    public String getFavourite_title() {
        return Favourite_title;
    }

    public void setFavourite_title(String favourite_title) {
        Favourite_title = favourite_title;
    }

    public String getFavourite_date() {
        return Favourite_date;
    }

    public void setFavourite_date(String favourite_date) {
        Favourite_date = favourite_date;
    }

    public String getFavourite_author() {
        return Favourite_author;
    }

    public void setFavourite_author(String favourite_author) {
        Favourite_author = favourite_author;
    }

    public String getFavourite_author_name() {
        return Favourite_author_name;
    }

    public void setFavourite_author_name(String favourite_author_name) {
        Favourite_author_name = favourite_author_name;
    }

    public String getFavourite_image() {
        return Favourite_image;
    }

    public void setFavourite_image(String favourite_image) {
        Favourite_image = favourite_image;
    }
}
