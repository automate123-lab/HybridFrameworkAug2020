package com.qa.linkedin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

public class ExcelUtil {
	
		 
  private static Logger log=Logger.getLogger(ExcelUtil.class);

		public FileInputStream fis=null;
		public FileOutputStream fileOut=null;
		private XSSFWorkbook workbook=null;
		private XSSFSheet sheet=null;
	/**
	 * this method is used to read the data from the excel sheet	
	 * @param fpath
	 * @param sheetName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
		  
		 public Object[][] getTestData(String path,String sheetName) throws IOException{
			 log.debug("specify the path of file");
			 File srcFile=new File(path);
			 log.debug("load the file");
			 fis=new FileInputStream(srcFile);
			 log.debug("load the workbook");
			 workbook =new XSSFWorkbook(fis);
			 log.debug("load sheet-->here we are loading 1st sheet only");
			 sheet=workbook.getSheet(sheetName);
			 log.debug("fetch the row count");
			 int rowCount=sheet.getLastRowNum();
			 log.debug("no of columns in excel sheet is--->"+rowCount);
			 log.debug("fetch the column count");
			 int colCount=sheet.getRow(0).getLastCellNum();
			 log.debug("no of columns in excel sheet is--->"+colCount);
			 log.debug("get the data");
			 //two dimensional Array declaration
			
			 Object[][] data=new Object[rowCount][colCount];
			 //System.out.println(sheet.getLastRowNum()+"------"+sheet.getRow(0).getLastCellNum());
			 for(int i=0;i<rowCount;i++) {
				 for(int j=0;j<colCount;j++) {
					 data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				 }
			 }
			 return data;
		 }


}
