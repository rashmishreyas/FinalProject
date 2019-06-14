package com.servicenow.genericlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static XSSFWorkbook excelWorkbook; 
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	public static XSSFSheet getSheet(String fileName,String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException{
		String filePath = Capabilities.getPropertyValue("DataDir") + fileName;
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		excelWorkbook = new XSSFWorkbook(inputStream);
		return excelWorkbook.getSheet(sheetName);
	}
	
	/*
     * Read Data from any cell of xls or xlsx file
     * This method will read data from the desired cell specified in the data sheet
     * Row num is customized to Serial Num
     */
	public static String getData(String fileName,String sheetName,int serialNo,int cellNum) throws IOException{
		String cellValue = " ";
		try{
			cellValue = getSheet(fileName, sheetName).getRow(serialNo).getCell(cellNum).getStringCellValue();
		}catch(Exception e){
			ReporterLogs.log("Exception encountered :"+e.getCause(), "error");
		}
		return cellValue;
	}

	/*
	 * Read Data from any range of cell of xls or xlsx file
	 * This method is used to read Data from excel file from a specified range of cell against a specified row
	 * @param File Name
	 * @param Sheet Name
	 * @param Row from which data is required
	 * @param row till which the data is needed
	 * @param cell corresponding to row from where data is needed
	 * @param cell corresponding to row till where data is needed
	 * @return string of array
	 */
	public static Object[][] getExcelObjects(String fileName, String sheetName, int startRow,int targetRow, int startCol, int targetCol) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		String[][] arrayExcelData = null;
		try {
			ExcelWSheet = getSheet(fileName, sheetName);
			arrayExcelData = new String[targetRow-startRow+1][targetCol-startCol+1];
			for(int i=startRow ; i<=targetRow; i++) {
					for(int j=startCol; j<=targetCol; j++) { 
							arrayExcelData[i-startRow][j-startCol] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					}
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} 
		return (arrayExcelData);
	}
	
	/*
     * Writing Data into any cell of xls or xlsx file
     * This method will write data to the desired cell in the data sheet
     */
	public static void writeDataIntoCell(String fileName, String sheetName,int serialNo, int cellNo,String dataToSet) throws IOException, FileNotFoundException{
        
        String filePath = Capabilities.getPropertyValue("DataDir") + fileName;
        try{
                      ReporterLogs.log("Writing Data to :"+filePath, "info");
                      File file=new File(filePath);
                   FileInputStream ioFile=new FileInputStream(file);
                   Workbook wb = WorkbookFactory.create(ioFile);
                      Sheet sheet=wb.getSheet(sheetName);
                      Row row=sheet.getRow(serialNo);
                      Cell cell=row.createCell(cellNo);
                      cell.setCellType(cell.CELL_TYPE_STRING);
                      ReporterLogs.log("Writing data : " + dataToSet, "info");
                      cell.setCellValue(dataToSet);    
                      CellStyle headerStyle = wb.createCellStyle();
                      Font headerFont = wb.createFont();
                      if (dataToSet.equalsIgnoreCase("Passed")) {
                            headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                            headerFont.setColor(IndexedColors.WHITE.getIndex());
                            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                            headerStyle.setFont(headerFont);
                            cell.setCellStyle(headerStyle);
                      } else if(dataToSet.equalsIgnoreCase("Failed")){
                            headerStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                            headerFont.setColor(IndexedColors.WHITE.getIndex());
                            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                            headerStyle.setFont(headerFont);
                            cell.setCellStyle(headerStyle);
                      }                          
                      FileOutputStream outFile=new FileOutputStream(filePath);
                      wb.write(outFile);
                      outFile.close();
        }catch(Exception e){
               ReporterLogs.log("Unable to write Data to XLS or XLSX File", "error");
               ReporterLogs.log("Exception encountered :"+e.getCause(), "error");
      }
  }

	
	
}
