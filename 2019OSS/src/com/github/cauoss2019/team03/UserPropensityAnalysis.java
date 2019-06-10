package com.github.cauoss2019.team03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*1~100 : 자연과학
101~200 : IT
201~300 : 에세이
301~400 : 역사
401~500 : 예술
501~600 : 인문
601~700 : 자기계발*/

public class UserPropensityAnalysis {
	
	private final String genre1 = "자연과학";
	final private String genre2 = "IT";
	final private String genre3 = "에세이";
	final private String genre4 = "역사";
	final private String genre5 = "예술";
	final private String genre6 = "인문";
	final private String genre7 = "자기계발";
	
	public UserPropensityAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserPropensityAnalysis(String inputID) {
		
		List<String> genre = new ArrayList<String>();
		
		String path = UserPropensityAnalysis.class.getResource("").getPath();
		
		List<List<String>> records = new Read(path + "resultData_ver4.csv", "euc-kr").getRecords();
		
		int sum = 0;
		
		for(int i=0; i<records.size(); i++) {
			
			if(records.get(i).get(0).equals(inputID)) {
				
				int bookId = Integer.parseInt(records.get(i).get(1));
				
				if(bookId < 101) {
					genre.add(genre1); // 자연과학
				}
				else if(bookId < 201) {
					genre.add(genre2); // IT
				}
				else if(bookId < 301) {
					genre.add(genre3); // 에세이
				}
				else if(bookId < 401) {
					genre.add(genre4); // 역사
				}
				else if(bookId < 501) {
					genre.add(genre5); // 예술
				}
				else if(bookId < 601) {
					genre.add(genre6); // 인문
				}
				else if(bookId < 701){
					genre.add(genre7); // 자기계발
				}
				sum++;
			}
		
		}
		System.out.println("\n==========================");
		System.out.println("* "+inputID+" 님은 총 "+sum+" 권을 빌리셨습니다.");
		System.out.println("==========================\n");
		
		Map<String, Integer> counts = new HashMap<String, Integer>();
		
		for (String str : genre) {
			
			if(counts.containsKey(str)) {
				counts.put(str, counts.get(str)+1);
				
			}
			else {
				counts.put(str, 1);
			}
			
		}
		
		
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue() + " 권 \t("
					+ String.format("%.2f", (double)(entry.getValue() / (double)sum) * 100.00) + " %)");
			
		}
		//index(4) >> 장르
		
		System.out.println("======================\n\n");
		
		System.out.println("메뉴로 돌아가려면 아무키나 누르세요.");
		
		try {
			
			System.in.read();
			
			System.out.println("\n\n======================");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	
}
