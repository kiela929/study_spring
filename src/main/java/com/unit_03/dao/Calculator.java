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

		return lineReadTemplate(filepath,sumCallback,0); //���ø��� �ݹ��� �־ ����!

	}


	public Integer calcMultiply(String filepath)throws IOException{

		LineCallback<Integer> multiplyCallback=
				new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value*Integer.valueOf(line);
			}
		};

		return lineReadTemplate(filepath,multiplyCallback,1); //���ø��� �ݹ��� �־ ����!

	}

	
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, 
			T initVal)throws IOException{
		//���ο� ���ø�!!
		
		BufferedReader br=null;

		try {
			br=new BufferedReader(new FileReader(filepath));
			
			T res=initVal;	
			//���� �ʱ�ȭ ����! ������ 0, �����϶� 1�̿�����.
			
			String line = null;
			while((line=br.readLine())!=null){ 
				// ������ �� ������ ������ ���鼭 �������� �͵� ���ø��� ���!
				
				res=(T)callback.doSomethingWithLine(line, res);
				//�� ������ ������ ������ ����ϴ� �۾��� �ݹ����� �ش�!
				//��, ��ȭ�� �ִ� �κ��̴ϱ�~~~ 
				//���� while�� ���������� ��� �ݹ��� �θ�����.
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