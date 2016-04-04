package com.unit_03.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.unit_03.template.LineCallback;

public class Calculator {

	public Integer calcSum(String filepath) throws IOException{

		LineCallback sumCallback=
				new LineCallback() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value+Integer.valueOf(line);
			}
		};

		return lineReadTemplate(filepath,sumCallback,0); //���ø��� �ݹ��� �־ ����!

	}


	public Integer calcMultiply(String filepath)throws IOException{

		LineCallback multiplyCallback=
				new LineCallback() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value*Integer.valueOf(line);
			}
		};

		return lineReadTemplate(filepath,multiplyCallback,1); //���ø��� �ݹ��� �־ ����!

	}

	
	public Integer lineReadTemplate(String filepath, LineCallback callback, 
			int initVal)throws IOException{
		//���ο� ���ø�!!
		
		BufferedReader br=null;

		try {
			br=new BufferedReader(new FileReader(filepath));
			
			Integer res=initVal;	
			//���� �ʱ�ȭ ����! ������ 0, �����϶� 1�̿�����.
			
			String line = null;
			while((line=br.readLine())!=null){ 
				// ������ �� ������ ������ ���鼭 �������� �͵� ���ø��� ���!
				
				res=callback.doSomethingWithLine(line, res);
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