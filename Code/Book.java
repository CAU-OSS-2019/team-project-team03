public class Book {

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookCategory;
    private String bookDate;
    private int rank;

    public Book() {
        // TODO Auto-generated constructor stub
    }

    public Book(String line){
        String[] temp = line.split(",");

        this.bookId = Long.parseLong((temp[0]));
        this.bookTitle = temp[1];
        this.bookAuthor = temp[2];
        this.bookPublisher = temp[3];
        this.bookCategory = temp[4];
        this.bookDate = temp[5];
        this.rank = Integer.parseInt(temp[6]);
    }


    public Book(Long bookId, String bookTitle, String bookAuthor, String bookPublisher, String bookCategory, String bookPublishDate, int rank) {
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
    public Long getBookId() {
        return this.bookId;
    }
    public void setBookId(Long bookId) {
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

    public String toString(){
        return bookId + "," + bookTitle + "," + bookAuthor + "," + bookPublisher + "," + bookCategory + "," + bookDate + "," + rank;
    }
    
    public String tostring(){        
       return bookTitle + "," + bookAuthor + "," + bookPublisher + "," + bookCategory;
    }
}
