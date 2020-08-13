package emicalculatorExcel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelNegative 
{
	String negativeData[] = new String[4];
	
	public String[] readExcelNegative() throws Exception
	{
		//Read data for negative test
		File filepath = new File("C:\\Users\\user\\Desktop\\Codes\\TestData\\emiCalculatorNegativeTest.xlsx");
		
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		
		Row row1 = sh.getRow(1);
		for(int i=0;i<3;i++)
		{
			double input2 = row1.getCell(i).getNumericCellValue();
			negativeData[i] = Double.toString(input2);
			wb.close();
		}
		return negativeData;
		
	}

}
