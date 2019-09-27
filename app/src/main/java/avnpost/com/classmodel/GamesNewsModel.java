package avnpost.com.classmodel;

public class GamesNewsModel {

    private String games_id,games_title,games_date,games_author,games_author_name,games_image;

    public GamesNewsModel(String games_id, String games_title, String games_date, String games_author, String games_author_name, String games_image) {
        this.games_id = games_id;
        this.games_title = games_title;
        this.games_date = games_date;
        this.games_author = games_author;
        this.games_author_name = games_author_name;
        this.games_image = games_image;
    }

    public String getGames_id() {
        return games_id;
    }

    public void setGames_id(String games_id) {
        this.games_id = games_id;
    }

    public String getGames_title() {
        return games_title;
    }

    public void setGames_title(String games_title) {
        this.games_title = games_title;
    }

    public String getGames_date() {
        return games_date;
    }

    public void setGames_date(String games_date) {
        this.games_date = games_date;
    }

    public String getGames_author() {
        return games_author;
    }

    public void setGames_author(String games_author) {
        this.games_author = games_author;
    }

    public String getGames_author_name() {
        return games_author_name;
    }

    public void setGames_author_name(String games_author_name) {
        this.games_author_name = games_author_name;
    }

    public String getGames_image() {
        return games_image;
    }

    public void setGames_image(String games_image) {
        this.games_image = games_image;
    }
}
