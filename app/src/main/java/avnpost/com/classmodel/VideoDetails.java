package avnpost.com.classmodel;

public class VideoDetails {
    private String VideoName;
    private String VideoDesc;
    private String URL;
    private String VideoId;

    public void setVideoName(String VideoName){
        this.VideoName=VideoName;
    }
    public String getVideoName(){
        return VideoName;
    }
    public void setVideoDesc(String VideoDesc){
        this.VideoDesc=VideoDesc;
    }
    public String getVideoDesc(){
        return VideoDesc;
    }
    public void setURL(String URL){
        this.URL=URL;
    }
    public String getURL(){
        return URL;
    }
    public void setVideoId(String VideoId){
        this.VideoId=VideoId;
    }
    public String getVideoId(){
        return VideoId;
    }
}
