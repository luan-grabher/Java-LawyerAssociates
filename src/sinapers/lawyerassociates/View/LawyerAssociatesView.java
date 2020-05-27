package sinapers.lawyerassociates.View;

import JExcel.JExcel;
import SimpleDotEnv.Env;
import SimpleView.View;
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
    
    private short lastCell = 0;

    public LawyerAssociatesView(List<Associate> associates, Map<String, JCheckBox> collumnsToPrint, String lawyer, File saveFolder) {
        this.associates = associates;
        this.collumnsToPrint = collumnsToPrint;
        this.lawyer = lawyer;
        this.saveFolder = saveFolder;

        lawyerName = lawyer.replaceAll("[^A-Za-z ]", "").trim();
    }

    public void createFile() {
        System.out.println("Criando Workbook");
        setWorkbookAndSheet();
        System.out.println("Imprimindo nome advogado");
        printLawyerName();
        System.out.println("Imprimindo cabeçalhos");
        printHeaders();
        System.out.println("Imprimindo associados");
        printLawyerAssociates();
        System.out.println("Auto size colls");
        sizeCollumns();
        System.out.println("Salvando arquivo");
        saveFile();
    }

    private void setWorkbookAndSheet() {
        try {
            workbook = new XSSFWorkbook(new File("template.xlsx"));
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
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

    private void printValueIfSelected(XSSFRow row, String name, String value, boolean bold) {
        if (collumnsToPrint.get(name).isSelected()) {
            Short cellNumber = row.getLastCellNum() != -1 ? row.getLastCellNum() : 0;
            XSSFCell cell = row.createCell(cellNumber);
            
            cell.setCellValue(value);

            XSSFFont font = workbook.createFont();
            font.setBold(bold);
            cell.getCellStyle().setFont(font);
        }
    }

    private void printLawyerAssociates() {
        Integer initialRow = Integer.valueOf(Env.get("initialRow")) + 2;

        Integer count = 0;
        for (Associate associate : associates) {
            count++;

            XSSFRow row = sheet.createRow(initialRow + count);

            printValueIfSelected(row, "name", associate.getNome(), false);
            printValueIfSelected(row, "cpf", associate.getCpf(), false);
            printValueIfSelected(row, "rg", associate.getRg(), false);
            printValueIfSelected(row, "birthDay", associate.getDtNascimento(), false);
        }
    }

    private void printHeaders() {
        XSSFRow row = sheet.createRow(Integer.valueOf(Env.get("initialRow")) + 2);
                
        printValueIfSelected(row, "name", "Nome", true);
        printValueIfSelected(row, "cpf", "CPF", true);
        printValueIfSelected(row, "rg", "RG", true);
        printValueIfSelected(row, "birthDay", "Data de Nascimento", true);
        
        lastCell = row.getLastCellNum();
    }
    
    private void sizeCollumns(){
        for (int i = 0; i < lastCell; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void saveFile() {
        JExcel.saveWorkbookAs(new File(saveFolder.getAbsolutePath().concat("\\").concat(lawyerName).concat(".xlsx")), workbook);
    }
}
