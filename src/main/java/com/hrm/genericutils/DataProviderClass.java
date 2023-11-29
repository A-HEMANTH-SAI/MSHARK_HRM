package com.hrm.genericutils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider
	public Object[][] storeData() throws IOException{
		PropertyFileUtils pfu=new PropertyFileUtils();
		Object[][] obj=new Object[3][3];
		obj[0][0]=pfu.readDataFromPropertiesFile("userEmail");
		obj[0][1]=pfu.readDataFromPropertiesFile("password");
		obj[0][2]=pfu.readDataFromPropertiesFile("hrhddValue");
		obj[1][0]=pfu.readDataFromPropertiesFile("hroun");
		obj[1][1]=pfu.readDataFromPropertiesFile("hropw");
		obj[1][2]=pfu.readDataFromPropertiesFile("hroddValue");
		obj[2][0]=pfu.readDataFromPropertiesFile("hraun");
		obj[2][1]=pfu.readDataFromPropertiesFile("hrapw");
		obj[2][2]=pfu.readDataFromPropertiesFile("hraddValue");
		
		return obj;
	}
}
