package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testCases.BaseClassReusableComponents;
 
 
public class ExcelUtilis extends BaseClassReusableComponents{	
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public String getCellData(String path,String sheetName,int rowNum,int colNum) throws IOException {
		
		try{
			fis=new FileInputStream(path);
			wbook=new XSSFWorkbook(fis);
		}catch(Exception fileNotFount) {
			System.out.println("File Not Found");
		}
		
		
		
		try {
			sheet=wbook.getSheet(sheetName);
		}catch(Exception sheetNotFound) {
			System.out.println("SheetNotFound.....");
			sheet=wbook.createSheet(sheetName);
		}
		try {
			row=sheet.getRow(rowNum);
		}catch(Exception RowNotFound) {
			System.out.println("RowIsNull");
			row=sheet.createRow(rowNum);
		}
		try {
			cell=row.getCell(colNum);
		}catch(Exception RowNotFound) {
			System.out.println("RowIsNull");
			cell=row.createCell(colNum);
		}
		
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			data=formatter.formatCellValue(cell);
		}catch(Exception e) {
			data="";
		}
		wbook.close();
		fis.close();
		return data;
		
	}
	
	public void setData(String sheetName,int rowNum,int colNum,String data,int colorStatus) throws IOException {
		try {
			sheet=super.wbook.createSheet(sheetName);
		}catch(Exception eeee ) {
			sheet=super.wbook.getSheet(sheetName);
		}
		try {
			if(sheet.getRow(rowNum)==null) {
				row=sheet.createRow(rowNum);
			}else {
				row=sheet.getRow(rowNum);
			}
		}catch(Exception HandlingTheRowIssue) {
			System.out.println("Problem while creating ROW or getting the ROW..");
		}
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		if(colorStatus==1) {
			XSSFCellStyle style=super.wbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            sheet.setColumnWidth(colNum, 50 * 256);
            style.setWrapText(true);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
		    cell.setCellStyle(style);      
		}else if(colorStatus==0){
			XSSFCellStyle style=super.wbook.createCellStyle(); 
            style.setFillForegroundColor(IndexedColors.YELLOW.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            sheet.setColumnWidth(colNum, 50 * 256);
            style.setWrapText(true);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
		    cell.setCellStyle(style); 
		}else {
			XSSFCellStyle style=super.wbook.createCellStyle(); 
            style.setFillForegroundColor(IndexedColors.RED.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            sheet.setColumnWidth(colNum, 50 * 256);
            style.setWrapText(true);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
		    cell.setCellStyle(style); 
		}
		

	}

	
}
