package emicalculatorExcel;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	String data[] = new String[4];
	
	public String[] readExcel() throws Exception
	{
		
		
			//Read data from excel file
			File filepath = new File("C:\\Users\\user\\Desktop\\Codes\\TestData\\emiCalculatorTest.xlsx");
			
			FileInputStream fis = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			Row row = sh.getRow(1);
			for(int i=0;i<3;i++)
			{
				double input = row.getCell(i).getNumericCellValue();
				data[i] = Double.toString(input);
			}
			
			return data;
			
	}
	
}
