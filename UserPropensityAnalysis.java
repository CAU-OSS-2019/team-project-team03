package com.github.cauoss2019.team03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserPropensityAnalysis {
	
	public UserPropensityAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserPropensityAnalysis(String inputID) {
		
		List<String> genre = new ArrayList<String>();
		
		String path = UserPropensityAnalysis.class.getResource("").getPath();
		
		List<List<String>> records = new Read(path + "BookList_sample_"+inputID+".csv", "euc-kr").getRecords();
		
		for(int i=1; i<records.size(); i++) {
				
			genre.add(records.get(i).get(4)); // 장르만 추가
			
		}
		
		Map<String, Integer> counts = new HashMap<String, Integer>();
		
		for (String str : genre) {
			
			if(counts.containsKey(str)) {
				counts.put(str, counts.get(str)+1);
				
			}
			else {
				counts.put(str, 1);
			}
			
		}
		
		int sum = records.size(); // 전체 빌린 책의 수
		
		
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			System.out.println(entry.getKey() + " = " + String.format("%.2f", (double)(entry.getValue() / (double)sum) * 100.00) + " % ");
			
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
