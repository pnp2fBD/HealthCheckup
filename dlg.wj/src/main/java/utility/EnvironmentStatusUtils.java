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

public class EnvironmentStatusUtils extends DLG{

	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private XSSFFont font = null;
	private int rowNumber;
	private int index;
	private int cellNumber = 2;
	private int coloredCell = 1;
	private int endTestcase = 6;
	private String path = null;
	
	//Properties Config = new Properties();
	FileInputStream fis;
	private String sheetName = Config.getProperty("sheetname");
	private CellStyle style;
	//private Font font;

	public EnvironmentStatusUtils() throws IOException {
		String env = System.getProperty("environment");	
		/*fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\Config.properties");*/
		//Config.load(fis);
		//sheetName = Config.getProperty("sheetname");
		if (env.equalsIgnoreCase("CD")) {
			path = Config.getProperty("CDvmpathStatusSheet");
		} else if (env.equalsIgnoreCase("CD2")) {
			path = Config.getProperty("CD2vmpathStatusSheet");
		} else if (env.equalsIgnoreCase("CT")) {
			path = Config.getProperty("CTvmpathStatusSheet");
		} else if (env.equalsIgnoreCase("CT2")) {
			path = Config.getProperty("CT2vmpathStatusSheet");
		} else if (env.equalsIgnoreCase("UAT")) {
			path = Config.getProperty("UATvmpathStatusSheet");
		} else if (env.equalsIgnoreCase("UAT2")) {
			path = Config.getProperty("UAT2vmpathStatusSheet");
		} else if (env.equalsIgnoreCase("TRG")) {
			path = Config.getProperty("TRGvmpathStatusSheet");
		}
	}

	public void writeExcel(String result, String env, int testcaseNumber) throws IOException {
		if (path == null)
			throw new IOException("Selenium code issue");
		if (testcaseNumber < 1 || testcaseNumber > 6) {
			Assert.fail("Selenium script issue. Please make sure testcase number lies between 1 and 6");
		}
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
			inputStream.close();
			outputStream.close();
		} finally {
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
