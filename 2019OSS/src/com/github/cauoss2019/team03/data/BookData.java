package com.github.cauoss2019.team03.data;

public class BookData {
	
	private int bookId;
	private String bookTitle;
	private String bookWriter;
	private String bookPublisher;
	private String bookCategory;
	private String bookPublishDate;
	
	public BookData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BookData(int bookId, String bookTitle, String bookWriter, String bookPublisher, String bookCategory, String bookPublishDate) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.bookCategory = bookCategory;
		this.bookPublishDate = bookPublishDate;
	}
	
	
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookPublishDate() {
		return bookPublishDate;
	}
	public void setBookPublishDate(String bookPublishDate) {
		this.bookPublishDate = bookPublishDate;
	}
	
	
	
}
