package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import helpers.LoggerHelper;

/**
 * Class for read and write data from excel sheet.
 * @author priyankag@dayalgroup.hq
 *
 */
public class ExcelUtils {
	//String resultFilePath = "C:/Users/ankitra/Desktop/Results.xlsx";
	Logger log = LoggerHelper.getLogger(ExcelUtils.class);
	
	
	/**
	 * Method for read data from excel sheet.
	 * @param filePath
	 * @param sheetName
	 * @return array of excel data
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public String[][] getExcelData(String filePath, String sheetName) {
		String[][] arrayExcelData = null;
		try {			
			FileInputStream fs = new FileInputStream(filePath);
			Workbook readBook = null;
			
			String fileExtensionName = filePath.substring(filePath.indexOf("."));
			if(fileExtensionName.equals(".xlsx")) {
				readBook = new XSSFWorkbook(fs);
			}
			else if(fileExtensionName.equals(".xls")) {
				readBook = new HSSFWorkbook(fs);
			}
			
			FormulaEvaluator evaluator = readBook.getCreationHelper().createFormulaEvaluator();
			
			Sheet sheet = readBook.getSheet(sheetName);
			
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			arrayExcelData = new String[rowCount ][columnCount];
			
			for (int i = 1; i <= rowCount; i++) {
				Row row = sheet.getRow(i);
				
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					
					if (cell == null) {
						arrayExcelData[i-1][j] = "";
					}
					else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
				    	arrayExcelData[i-1][j] = cell.getStringCellValue();
				        
					}
					/*switch (cell.getCellType()) {
		
						case Cell.CELL_TYPE_FORMULA:
					    	cell.setCellType(Cell.CELL_TYPE_STRING);
					    	arrayExcelData[i-1][j] = cell.getStringCellValue();
					        break;
	
					    case Cell.CELL_TYPE_NUMERIC:
					    	cell.setCellType(Cell.CELL_TYPE_STRING);
					    	arrayExcelData[i-1][j] = cell.getStringCellValue();
					        break;
	
					    case Cell.CELL_TYPE_STRING:
					    	arrayExcelData[i-1][j] = cell.getStringCellValue();
					        break;
	
					    case Cell.CELL_TYPE_BLANK:
					    	arrayExcelData[i-1][j] = "";
					        break;
	
					    case Cell.CELL_TYPE_ERROR:
					    	arrayExcelData[i-1][j] = "error";
					        break;
	
					    default:
					        break;
					}*/
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	/**
	 * Method for write in excel sheet.
	 * @param resultData
	 * @param resultFilePath
	 */
	public void writeFile(Object[][] resultData, String resultFilePath) {			
		
		try {
			File file = new File(resultFilePath);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			
			Row row;
			if (rowCount < 1) {
				row = sheet.createRow(rowCount);
				row.createCell(0).setCellValue("TestCase");
				row.createCell(1).setCellValue("Status");
				row.createCell(2).setCellValue("Message");
				row.createCell(3).setCellValue("Exceution Time");
			}
			
			for (Object[] result : resultData) {
				row = sheet.createRow(++rowCount);
				int columnCount = 0;
				for (Object field : result) {
					Cell cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} 
					else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
					else if (field instanceof Long) {
						cell.setCellValue((Long) field);
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
