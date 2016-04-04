package com.unit_03.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.unit_03.template.LineCallback;

public class Calculator {

	public Integer calcSum(String filepath) throws IOException{

		LineCallback<Integer> sumCallback=
				new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value+Integer.valueOf(line);
			}
		};

		return lineReadTemplate(filepath,sumCallback,0); //템플릿에 콜백을 넣어서 리턴!

	}


	public Integer calcMultiply(String filepath)throws IOException{

		LineCallback<Integer> multiplyCallback=
				new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value*Integer.valueOf(line);
			}
		};

		return lineReadTemplate(filepath,multiplyCallback,1); //템플릿에 콜백을 넣어서 리턴!

	}

	
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, 
			T initVal)throws IOException{
		//새로운 템플릿!!
		
		BufferedReader br=null;

		try {
			br=new BufferedReader(new FileReader(filepath));
			
			T res=initVal;	
			//요기는 초기화 숫자! 덧셈은 0, 곱셈일땐 1이여야함.
			
			String line = null;
			while((line=br.readLine())!=null){ 
				// 파일의 각 라인을 루프를 돌면서 가져오는 것도 템플릿이 담당!
				
				res=(T)callback.doSomethingWithLine(line, res);
				//각 라인의 내용을 가지고 계산하는 작업만 콜백한테 준다!
				//즉, 변화가 있는 부분이니까~~~ 
				//물론 while이 끝날때까지 계속 콜백을 부를것임.
			}
			return res;


		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		finally{
			if(br!=null){
				try {br.close();}
				catch(IOException e){System.out.println(e.getMessage());}
			}

		}
	}
	
	
}