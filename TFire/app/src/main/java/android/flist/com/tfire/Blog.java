package android.flist.com.tfire;

/**
 * Created by Rohan on 17-Nov-16.
 */

public class Blog {

    private String title;
    private String des;
    private String image;

    public Blog(){

    }

    public Blog(String title, String des, String image){
        this.title = title;
        this.des = des;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}