package com.github.cauoss2019.team03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Read {
	
	List<List<String>> records = new ArrayList<>();
	
	public Read() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Read(String path, String encoding) {
		
		BufferedReader br = null;
		
		try {
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			
			String line;
			
			while((line = br.readLine()) != null) {
					
					String[] values = line.split(",");
					records.add(Arrays.asList(values));
				}
	
		} catch(Exception e) {
			
			e.printStackTrace();
		}

	
	}

	public List<List<String>> getRecords() {
		return records;
	}

	public void setRecords(List<List<String>> records) {
		this.records = records;
	}
}
