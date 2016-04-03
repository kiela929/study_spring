package com.unit_03.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	
	public Integer calcSum(String filepath) throws IOException{
		
		BufferedReader br =new BufferedReader(new FileReader(filepath));
		//���� �� �б� ���ϰ� BufferedReader�� ������ �����´�.
		Integer sum = 0;
		String line=null;
		while((line=br.readLine())!=null){
			//������ ���α��� �о�鼭 ���ڸ� ���Ѵ�.
			sum+=Integer.valueOf(line);
		}
		br.close();
		return sum;
	}

}
