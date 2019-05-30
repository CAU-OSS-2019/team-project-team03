public class BookData {

    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookCategory;
    private String bookDate;
    private int rank;

    public BookData() {
        super();
        // TODO Auto-generated constructor stub
    }


    public BookData(int bookId, String bookTitle, String bookAuthor, String bookPublisher, String bookCategory, String bookPublishDate, int rank) {
        super();
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookCategory = bookCategory;
        this.bookDate = bookPublishDate;
        this.rank = rank;
    }


    public String getBookCategory() {
        return this.bookCategory;
    }
    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
    public int getBookId() {
        return this.bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getBookTitle() {
        return this.bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookAuthor() {
        return this.bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookPublisher() {
        return this.bookPublisher;
    }
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    public String getBookDate() {
        return this.bookDate;
    }
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
}