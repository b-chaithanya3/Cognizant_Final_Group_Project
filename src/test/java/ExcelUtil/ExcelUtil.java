package ExcelUtil;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtil {

    public static void writeData(List<String[]> rows1,String path) {

        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Schedule");
            int rowNum = 0;
            for (String[] rowData : rows1) {
                if (rowData == null) {
                    continue;
                }
                boolean isEmpty=true;
                for(String cell:rowData){
                    if(cell!=null && !cell.trim().isEmpty()){
                        isEmpty=false;
                        break;
                    }
                }
                if(isEmpty){
                    continue;
                }
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < rowData.length; i++) {
                    String value=rowData[i]!=null?rowData[i].trim():"";
                    row.createCell(i).setCellValue(value);
                }
            }
            FileOutputStream fos = new FileOutputStream(path);
            wb.write(fos);
            wb.close();

            System.out.println(" Excel Created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
