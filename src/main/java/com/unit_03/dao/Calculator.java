package com.unit_03.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	
	public Integer calcSum(String filepath) throws IOException{
		
		BufferedReader br =new BufferedReader(new FileReader(filepath));
		//한줄 씩 읽기 편하게 BufferedReader로 파일을 가져온다.
		Integer sum = 0;
		String line=null;
		while((line=br.readLine())!=null){
			//마지막 라인까지 읽어가면서 숫자를 더한다.
			sum+=Integer.valueOf(line);
		}
		br.close();
		return sum;
	}

}
