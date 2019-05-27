package com.github.cauoss2019.team03.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Preprocessing {

	private final static String path = Preprocessing.class.getResource("").getPath(); // 절대경로 읽어오기
	private final static String dataFileName = "BookList.csv"; // 데이터파일 이름
	private final static String dataEncoding = "euc-kr"; // 데이터 파일 인코딩

	public Preprocessing() {

		super();

		int dataLng = 0; // 데이터파일 행 갯수
		int categoryCnt = 0;

		ArrayList<BookData> bookList = new ArrayList<>();
		ArrayList<String> categoryList;
		List<List<String>> records = new ArrayList<>();

		records = readCSV(path + dataFileName, dataEncoding);

		System.out.println("phase1> 원본 데이터 읽기");

		System.out.println("파일 읽는중..");

		for (List<String> list : records) {

			// System.out.println(list.toString());

			int bookId = Integer.parseInt(list.get(0));
			String bookTitle = list.get(1);
			String bookWriter = list.get(2);
			String bookPublisher = list.get(3);
			String bookCategory = list.get(4);
			String bookPublishDate = list.get(5);

			BookData bookData = new BookData(bookId, bookTitle, bookWriter, bookPublisher, bookCategory,
					bookPublishDate);

			bookList.add(bookData);

			// System.out.println(bookCategory);

		}

		dataLng = bookList.size();

		System.out.println("모든 파일을 읽었습니다.");

		System.out.println(dataLng + " 개의 데이터가 있습니다.");

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (BookData bookData : bookList) {

			Integer count = map.get(bookData.getBookCategory());
			// System.out.println(bookData.getBookCategory());
			map.put(bookData.getBookCategory(), (count == null) ? 1 : count++);

		}
		System.out.println("카테고리 개수:" + map.size());
		System.out.println(map.toString());

		// categoryCnt = map.size();

		// map을 토대로 카테고리 목록을 따로 생성
		categoryList = new ArrayList<String>(map.keySet());

		categoryCnt = categoryList.size();

		// System.out.println(categoryList.toString());

		System.out.println("=============================");

		System.out.println("phase2> 편향적인 유저 생성");

		ArrayList<UserData> userDataList = generateBiasUser(100, bookList, categoryList);
		
		//System.out.println(userDataList.size()+"개");
		
		/*
		 * for(UserData data : userDataList) { data.printBookDataList(); }
		 */
		createCSV(userDataList, "resultData");
		System.out.println("생성성공");
		
	}

	// data.csv 를 읽어와 List<List<String>>을 return합니다.
	public List<List<String>> readCSV(String path, String encoding) {

		List<List<String>> records = new ArrayList<>();

		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

			String line;

			while ((line = br.readLine()) != null) {

				// String[] values = line.split(",");
				String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // 내용에 콤마가 들어간경우 정규식으로 처리

				records.add(Arrays.asList(values));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return records;

	}
	

	public ArrayList<UserData> generateBiasUser(int mUserCnt, ArrayList<BookData> mBookDataList,
			ArrayList<String> mCategoryList) {

		int userCount = mUserCnt;
		int categoryCount = mCategoryList.size();
		ArrayList<BookData> bookList = mBookDataList;
		ArrayList<String> categoryList = mCategoryList;
		ArrayList<UserData> userDataList = new ArrayList<UserData>();
		double randMax = 0.7;
		double randMin = 0.6;

		for (String category : categoryList) { // 카테고리 순서대로 주 카테고리를 설정

			int fromIndex = 0;
			int toIndex = fromIndex + 100;
			List<BookData> subList = new ArrayList<BookData>(); // 주 카테고리로 자른 list
			subList = bookList.subList(fromIndex, toIndex);// 주 카테고리별로 list 분할함
			List<Integer> subListBookIdList = new ArrayList<Integer>();
			
			
			for(int n=0; n<subList.size(); n++) {
				
				subListBookIdList.add(subList.get(n).getBookId());
			}
			
			List<BookData> remainList = (List<BookData>) bookList.clone();
			
			Iterator<BookData> iter = remainList.iterator();
			
			while(iter.hasNext()) {
				BookData data = iter.next();
				
				if(subListBookIdList.contains(data.getBookId())) {
					iter.remove();
				}
			}
			
			
			for (int i = 0; i < userCount; i++) { // 유저 100명씩 만들기

				List<Integer> duplicateCheck = new ArrayList<Integer>(); // 중복 체크를 위한 integer 리스트
				UserData userData = new UserData(); // 유저 한명분의 데이터 생성 ~ 이를 100번 반복하는 것임
				userData.setUserId(i); // 유저 아이디를 설정해줌 여기서는 그냥 생성 순서대로 0~99까지있음.

				// 주 카테고리의 60~70% 비율로 할당
				double randPercent = Math.round(ThreadLocalRandom.current().nextDouble(randMin, randMax) * 100) / 100.0;
				int borrowMainCategoryBookCount = (int) (100 * randPercent); // 메인 카테리고 책을 빌린 개수 할당
				// System.out.println(borrowMainCategoryBookCount);

				// System.out.println(randPercent);
				int overBest50 = (int) Math.round(borrowMainCategoryBookCount * 0.7); // 메인카테고리에서 랭크가 best50 이상 책의(70%)
																						// 개수 반올림
				int underBest50 = borrowMainCategoryBookCount - overBest50; // 메인카테고리에서 랭크가 best50 이하 책(30%) 개수

				// System.out.println("overBest50 :"+overBest50 +
				// "///underBest50:"+underBest50);

				for (int j = 0; j < overBest50; j++) { // best50이상에 할당 된 책의 개수만큼 반복하면서 userData에 BookData 추가

					int overBest50BookIndex;

					do {
						overBest50BookIndex = ThreadLocalRandom.current().nextInt(overBest50); // best50 중에서 랜덤 index 뽑기 중복이면 다시 뽑음

					} while (duplicateCheck.contains(overBest50BookIndex));

					duplicateCheck.add(overBest50BookIndex);

					//System.out.println("overBest50BookIndex : "+ overBest50BookIndex);
					//System.out.println(subList.size());
					//System.out.println(subList.get(overBest50BookIndex).getBookTitle());
					userData.addBookDataList(subList.get(overBest50BookIndex));

				}

				for (int k = 0; k < underBest50; k++) { // best50 미만에 할당 된 책의 개수만큼 반복하면서 userData에 BookData 추가

					int underBest50BookIndex;

					do {
						underBest50BookIndex = ThreadLocalRandom.current().nextInt(overBest50, subList.size()); // overBest50
																												// 초과부터
																												// 나머지까지

					} while (duplicateCheck.contains(underBest50BookIndex));

					duplicateCheck.add(underBest50BookIndex);

					// System.out.println(underBest50BookIndex);
					// System.out.println(subList.get(underBest50BookIndex).getBookTitle());
					userData.addBookDataList(subList.get(underBest50BookIndex));

				}

				userDataList.add(userData);
				// userData.printBookDataList();

				// 나머지 카테고리에서 뽑기
				int remainCategroyBookCount = 100 - borrowMainCategoryBookCount;

				for (int l = 0; l < remainCategroyBookCount; l++) {

					int remainBookIndex;

					do {
						remainBookIndex = ThreadLocalRandom.current().nextInt(remainList.size()); // remainList index
																									// 중에서 랜덤

					} while (duplicateCheck.contains(remainBookIndex));

					duplicateCheck.add(remainBookIndex);

					// System.out.println(underBest50BookIndex);
					// System.out.println(subList.get(underBest50BookIndex).getBookTitle());
					
					userData.addBookDataList(remainList.get(remainBookIndex));

				}

			} // user 1명분 끝

			fromIndex += 100;

		}

		return userDataList;

	}
	
	
	public void createCSV(List<UserData> userDataList, String fileNameInput) { //기본 filePath : C:\\, file이름 ; resultData.csv
		
		Path path = Paths.get("C:\\OSSRecommendSystem\\data"); //추가 경로 
		
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			
			BufferedWriter fw = new BufferedWriter(new FileWriter(path+"//"+fileNameInput+".csv", false));
			
			for(UserData userData : userDataList) {
				
				for(BookData bookData : userData.getBookDataList()) {
					fw.write(
							userData.getUserId()
							+ "," + bookData.getBookId()
							+ "," + bookData.getBookTitle()
							+ "," + bookData.getBookWriter()
							+ "," + bookData.getBookPublisher()
							+ "," + bookData.getBookCategory()
							+ "," + bookData.getBookPublishDate());
					
					fw.newLine();
				}
				
			}
			fw.flush();
			fw.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
