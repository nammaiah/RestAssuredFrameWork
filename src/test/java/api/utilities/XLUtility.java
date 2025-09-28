package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String filePath;
	
		
	public int getRowCount(String filePath, String sheetName) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String filePath, String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
	    return cellcount;
	}
	
	public String getCellData(String filePath, String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
			String data;
			try {
				data=formatter.formatCellValue(cell);
			}
			catch(Exception e)
			{
				data="";
			}
			workbook.close();
			fi.close();
			return data;
	}
	public void setCellData(String filePath,String sheetName,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
		/**
		File xlfile=new File(filePath);
		if(!xlfile.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(filePath);
			workbook.write(fo);
		}
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1)
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		**/
		
	}
	
	public void fillGreenColor(String filePath,String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);		
		cell=row.createCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		//style.setFillPattern(FillPatternType.LESS_DOTS);
		cell.setCellStyle(style);			
		
		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String filePath,String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);		
		cell=row.createCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		cell.setCellStyle(style);			
		
		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

}
