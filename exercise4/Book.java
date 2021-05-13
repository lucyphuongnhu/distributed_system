public class Book extends Media{
    private String bookAuthor;

    public Book(String mediaName, String bookAuthor){
        super(mediaName);
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}