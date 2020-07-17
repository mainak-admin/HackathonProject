package emicalculatorExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel 
{
	public void getValues(String month, String principal, String interest) throws IOException
	{


		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh= wb.createSheet("Output_Data");

		Row row1 = sh.createRow(0);

		//Creating header cell
		Cell c1 = row1.createCell(0);
		c1.setCellValue("Month");

		Cell c2 = row1.createCell(1);
		c2.setCellValue("Principal amount");

		Cell c3 = row1.createCell(2);
		c3.setCellValue("Interest");

		//Inserting values into cells
		Row row2 = sh.createRow(1);

		Cell c4 = row2.createCell(0);
		c4.setCellValue(month);

		Cell c5 = row2.createCell(1);
		c5.setCellValue(principal);

		Cell c6 = row2.createCell(2);
		c6.setCellValue(interest);

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File file = new File("C:\\Users\\user\\Desktop\\Codes\\TestData\\Output_Data"+timestamp+".xlsx");
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		



	}




}
