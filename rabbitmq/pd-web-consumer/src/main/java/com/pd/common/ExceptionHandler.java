package com.pd.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler {
	public static void main(String[] args) {
		try {
			String string=null;
			string.toCharArray();
		} catch (Exception e) {
			handle(e);
		}		
	}

	public  static void handle(Throwable e){
		//String detail=e.getMessage();
		
		StringWriter stringWriter=new StringWriter();
		PrintWriter printWriter=new 
				PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String detail=stringWriter.toString();
		System.out.println(detail);
	}
}
