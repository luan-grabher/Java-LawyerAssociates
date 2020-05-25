package sinapers.lawyerassociates.View;

import JExcel.JExcel;
import SimpleDotEnv.Env;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tpsdb.Model.Entities.Associate;

public class LawyerAssociatesView {

    private List<Associate> associates;
    private Map<String, JCheckBox> collumnsToPrint;
    private String lawyer;
    private String lawyerName;
    private File saveFolder;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public LawyerAssociatesView(List<Associate> associates, Map<String, JCheckBox> collumnsToPrint, String lawyer, File saveFolder) {
        this.associates = associates;
        this.collumnsToPrint = collumnsToPrint;
        this.lawyer = lawyer;
        this.saveFolder = saveFolder;

        lawyerName = lawyer.replaceAll("[^A-Za-z ]", "").trim();
    }

    public void createFile() {
        setWorkbookAndSheet();
        printLawyerName();
        printHeaders(sheet.createRow(Integer.valueOf(Env.get("initialRow")) + 2));
        saveFile();
    }

    private void setWorkbookAndSheet() {
        try {
            workbook = new XSSFWorkbook(new File("template.xlsx"));
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
        }
    }

    private void printLawyerName() {
        XSSFRow row = sheet.createRow(Integer.valueOf(Env.get("initialRow")));

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);

        XSSFCell cell = row.createCell(0);
        cell.setCellValue(lawyerName);
        cell.getCellStyle().setFont(fontBold);
    }

    private void printLawyerAssociates() {
        Integer initialRow = Integer.valueOf(Env.get("initialRow"))  + 2;
        
        
        Integer count = 0;        
        for (Associate associate : associates) {
            count++;
            
            XSSFRow row = sheet.createRow(initialRow + count);
            
            Integer countCells = -1;
            
            
        }
    }
    
    private void printHeaders(XSSFRow row){
        Integer count = -1;
        for (Map.Entry<String, JCheckBox> entry : collumnsToPrint.entrySet()) {
            String key = entry.getKey();
            JCheckBox collumn = entry.getValue();
            count++;
            
            XSSFCell cell = row.createCell(count);
            cell.setCellValue(collumn.getText());
            
            XSSFFont font =  workbook.createFont();
            font.setBold(true);
            
            cell.getCellStyle().setFont(font);
        }
    }

    private void saveFile() {
        JExcel.saveWorkbookAs(new File(saveFolder.getAbsolutePath().concat("\\").concat(lawyerName).concat(".xlsx")), workbook);
    }
}
