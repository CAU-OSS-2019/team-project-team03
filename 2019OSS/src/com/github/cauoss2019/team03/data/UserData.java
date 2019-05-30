package com.github.cauoss2019.team03.data;

import java.util.ArrayList;

public class UserData {
	
	private int userId;
	private ArrayList<BookData> bookDataList = new ArrayList<BookData>();;
	
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public ArrayList<BookData> getBookDataList() {
		return bookDataList;
	}


	public void setBookDataList(ArrayList<BookData> bookDataList) {
		this.bookDataList = bookDataList;
	}
	
	public void addBookDataList(BookData data) {
		this.bookDataList.add(data);
	}

	public void printBookDataList() {
		
		//System.out.println(this.bookDataList.size() + " ±ÇÀ» ºô·È½À´Ï´Ù********");
		
		for(BookData data : this.bookDataList) {
			
			System.out.println(
					"["+userId + "] / "
					+ data.getBookId() + " / "
					+ data.getBookTitle() + " / "
					+ data.getBookWriter() + " / "
					+ data.getBookPublisher() + " / "
					+ data.getBookCategory() + " / "
					+ data.getBookPublishDate());
		}
	}
	
	
	
}
