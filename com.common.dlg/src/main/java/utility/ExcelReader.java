package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFRow row;
	XSSFSheet sheet;

	public final static Logger log = Logger.getLogger(ExcelReader.class.getName());

	public ExcelReader() throws IOException {
		log.debug("Entered in ExcelReader() constructor of ExcelReader class");
		File file = new File(System.getProperty("inputexcelFile"));
		// File file = new File("C:/DLG HealthCheck/InputData.xlsx");
		FileInputStream fileInput = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheetAt(0);
		log.info("Read the Excel file at first index from : " + System.getProperty("inputexcelFile"));
		log.debug("Exit from ExcelReader() constructor of ExcelReader class");
	}

	public int readEnvColumn(String env) throws IOException {
		log.debug("Entered in readEnvColumn(String env) constructor of ExcelReader class");
		row = sheet.getRow(0);
		int col_num = -1;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(env))
				col_num = i;
		}
		log.info("Fetch the column number from the excel sheet : " + col_num);
		log.debug("Exit from readEnvColumn(String env) constructor of ExcelReader class");
		return col_num;
	}

	public String getPolicyNumber(String env) throws IOException {
		log.debug("Entered in getPolicyNumber(String env) constructor of ExcelReader class");
		int col_num = this.readEnvColumn(env);
		XSSFCell cell = sheet.getRow(2).getCell(col_num);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String policyNumber = cell.getStringCellValue();
		log.info("Fetch the policy number " + policyNumber + " for " + env + " environment from the excel sheet");
		log.debug("Exit from getPolicyNumber(String env) constructor of ExcelReader class");
		return policyNumber;
	}

	public String getUserName(String env) throws IOException {
		log.debug("Entered in getUserName(String env) constructor of ExcelReader class");
		int col_num = this.readEnvColumn(env);
		XSSFCell cell = sheet.getRow(1).getCell(col_num);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String username = cell.getStringCellValue().split("/")[0];
		log.debug("Exit from getUserName(String env) constructor of ExcelReader class");
		return username;
	}

	public String getPassword(String env) throws IOException {
		log.debug("Entered in getPassword(String env) constructor of ExcelReader class");
		int col_num = this.readEnvColumn(env);
		XSSFCell cell = sheet.getRow(1).getCell(col_num);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String password = cell.getStringCellValue().split("/")[1];
		log.debug("Exit from getPassword(String env) constructor of ExcelReader class");
		return password;
	}

	/*
	 * public static void main(String[] args) throws IOException { ExcelReader
	 * excelRead = new ExcelReader();
	 * System.out.println(excelRead.getPolicyNumber("UAT"));
	 * System.out.println(excelRead.getUserName("UAT"));
	 * System.out.println(excelRead.getPassword("UAT")); }
	 */
}
