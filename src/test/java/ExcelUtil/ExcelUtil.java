package ExcelUtil;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtil {
    // Write full table (for Home Loan amortization)
	public static void writeFullTable(String fileName, List<String[]> tableData) throws Exception {
	    Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("Schedule");
	    int rowNum = 0;
	    for (String[] rowData : tableData) {
	        Row row = sheet.createRow(rowNum++);
	        for (int i = 0; i < rowData.length; i++) {
	            row.createCell(i).setCellValue(rowData[i]);
	        }
	    }

	    
	    FileOutputStream fos = new FileOutputStream("test-output/" + fileName + ".xlsx");
	    wb.write(fos);
	    wb.close();
	}

}
