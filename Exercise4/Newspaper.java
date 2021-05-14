public class Newspaper extends Media{
    private String newspaperType;

    public Book(String mediaName, String newspaperType){
        super(mediaName);
        this.newspaperType = newspaperType;
    }

    public String getNewspaperType() {
        return newspaperType;
    }

    public void setNewspaperType(String newspaperType) {
        this.newspaperType = newspaperType;
    }
}