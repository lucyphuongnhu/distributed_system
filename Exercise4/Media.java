import java.io.Serializable;

public class Media implements Serializable {
    private String mediaName;

    public int getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

}