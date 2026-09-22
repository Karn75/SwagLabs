package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static Workbook wb;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;

	public static int getRowcount(String xlfile, String xlsheet) throws IOException {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;
	}
	
	public static int getCellcount(String xlfile, String xlsheet, int rownum) throws IOException {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellcount;
	}
	
	public static String getCelldata(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		
		try {
			DataFormatter formatter = new DataFormatter();
			String celldata = formatter.formatCellValue(cell);
			return celldata;
		}
		catch (Exception e) {
			data = "";
		}
		wb.close();
		fis.close();
		return data;	
	}
	
	public static void setCelldata(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.createCell(colnum);
		fos = new FileOutputStream(xlfile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}

}
