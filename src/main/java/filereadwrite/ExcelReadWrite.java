package filereadwrite;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

public class ExcelReadWrite {

    Map<Integer, Object[]> studentDetails = new HashMap<>();

    private static final String FILE_NAME = "student_data.xlsx";

    {
        studentDetails.put(0,new Object[]{"name","age"});
        studentDetails.put(1, new Object[]{"Aayu",35});
        studentDetails.put(2, new Object[]{"Samadhi",26});
        studentDetails.put(3, new Object[]{"Amadi",21});
        studentDetails.put(4, new Object[]{"Kamal",54});
        studentDetails.put(5, new Object[]{"Yash",87});
    }

    public void writeExcelFile() throws IOException {
        OutputStream outputStream = new FileOutputStream(FILE_NAME);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("studentDetails");

    //    for(Map.Entry<Integer, Object[]> data : studentDetails.entrySet());



        studentDetails.forEach((id, value) -> {

                    XSSFRow xssfRow = xssfSheet.createRow(id);
                    IntStream.range(0, value.length).forEach(i -> {
                        XSSFCell xssfCell = xssfRow.createCell(i);
                        if (value[i] instanceof Integer) {
                            xssfCell.setCellType(CellType.NUMERIC);
                            xssfCell.setCellValue((Integer) value[i]);
                        } else {
                            xssfCell.setCellType(CellType.STRING);
                            xssfCell.setCellValue((String) value[i]);
                        }
                    });

                });

        xssfWorkbook.write(outputStream);

    }

    public void readExcelFile() throws IOException {
        InputStream inputStream = new FileInputStream(FILE_NAME);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("studentDetails");
        Iterator<Row> iterator = xssfSheet.rowIterator();
        while (iterator.hasNext()){
            XSSFRow xssfRow = (XSSFRow) iterator.next();
            Iterator<Cell> cellIterator = xssfRow.cellIterator();
            while (cellIterator.hasNext()){
                String value = String.valueOf(cellIterator.next());
            //    System.out.print(value+"\t");
                System.out.printf("%s\t", value);
            }
        }
    }
    public static void main(String[] args) throws IOException {
         //   new ExcelReadWrite().writeExcelFile();
            new ExcelReadWrite().readExcelFile();
        }


}

