package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import base.DLG;

public class EnvironmentStatusUtils extends DLG {

	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private int rowNumber;
	private int index;
	private int cellNumber = 6;
	private int coloredCell = 5;
	private int endTestcase = 3;
	private String path;
	private String sheetName;
	private CellStyle style;
	private XSSFFont font;

	public EnvironmentStatusUtils() {
		String environment = System.getProperty("environment");
		if (environment.equalsIgnoreCase("CD")) {
			path = Config.getProperty("CDvmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("CD2")) {
			path = Config.getProperty("CD2vmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("CT")) {
			path = Config.getProperty("CTvmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("CT2")) {
			path = Config.getProperty("CT2vmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("UAT")) {
			path = Config.getProperty("UATvmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("UAT2")) {
			path = Config.getProperty("UAT2vmpathStatusSheet");
		} else if (environment.equalsIgnoreCase("TRG")) {
			path = Config.getProperty("TRGvmpathStatusSheet");
		}
		sheetName = Config.getProperty("sheetname");
	}

	public void writeExcel(String result, String env, int testcaseNumber) throws IOException{
		
		if (path == null)
			throw new IOException("Selenium code issue");

		if (testcaseNumber < 1 || testcaseNumber > 3) {
			Assert.fail("Selenium script issue. Please make sure testcase number lies between 1 and 3");
		}
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			// System.out.println(path);
			File file = new File(path);
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			style = workbook.createCellStyle();

			setCellStyle(result);

			if (env.equalsIgnoreCase("CD")) {
				rowNumber = 1;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("CD2")) {
				rowNumber = 7;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("CT")) {
				rowNumber = 13;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("CT2")) {
				rowNumber = 19;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("UAT")) {
				rowNumber = 25;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("UAT2")) {
				rowNumber = 31;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			} else if (env.equalsIgnoreCase("TRG")) {
				rowNumber = 37;
				index = rowNumber + testcaseNumber;
				row = sheet.getRow(index);
				cell = row.getCell(cellNumber);
				cell.setCellValue(result);

				cell = row.getCell(coloredCell);
				cell.setCellStyle(style);
			}

			if (!result.equalsIgnoreCase("NA")) {

				for (int i = testcaseNumber; i < endTestcase; i++) {
					row = sheet.getRow(rowNumber + i + 1);
					cell = row.getCell(cellNumber);
					cell.setCellValue(result);

					cell = row.getCell(coloredCell);
					cell.setCellStyle(style);
				}
			}
			outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			
				Thread.sleep(5000);
			
			inputStream.close();
			outputStream.close();

		} 
		catch(Exception e)
		{e.printStackTrace();}
		finally {
			inputStream = null;
			outputStream = null;
		}

	}

	private void setCellStyle(String result) throws IOException {

		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		font = workbook.createFont();

		if (result.equalsIgnoreCase("NA")) {
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			font.setColor(IndexedColors.WHITE.getIndex());
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		} else {
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			font.setColor(IndexedColors.WHITE.getIndex());
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
	}

}
