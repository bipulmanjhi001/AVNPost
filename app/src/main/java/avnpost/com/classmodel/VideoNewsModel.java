package avnpost.com.classmodel;

public class VideoNewsModel {

    private String Video_id,Video_title,Video_date,Video_author,Video_author_name,Video_image;

    public VideoNewsModel(String video_id, String video_title, String video_date, String video_author, String video_author_name, String video_image) {
        Video_id = video_id;
        Video_title = video_title;
        Video_date = video_date;
        Video_author = video_author;
        Video_author_name = video_author_name;
        Video_image = video_image;
    }

    public String getVideo_id() {
        return Video_id;
    }

    public void setVideo_id(String video_id) {
        Video_id = video_id;
    }

    public String getVideo_title() {
        return Video_title;
    }

    public void setVideo_title(String video_title) {
        Video_title = video_title;
    }

    public String getVideo_date() {
        return Video_date;
    }

    public void setVideo_date(String video_date) {
        Video_date = video_date;
    }

    public String getVideo_author() {
        return Video_author;
    }

    public void setVideo_author(String video_author) {
        Video_author = video_author;
    }

    public String getVideo_author_name() {
        return Video_author_name;
    }

    public void setVideo_author_name(String video_author_name) {
        Video_author_name = video_author_name;
    }

    public String getVideo_image() {
        return Video_image;
    }

    public void setVideo_image(String video_image) {
        Video_image = video_image;
    }
}
