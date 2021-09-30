package service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ParseService {
    public static HashMap<String, ArrayList<String>> parse(String name) {

        StringBuilder result = new StringBuilder();
        InputStream in;
        XSSFWorkbook wb = null;
        try {
            in = new FileInputStream(name);
            wb = new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert wb != null;
        XSSFSheet sheet = wb.getSheetAt(0);
        TopicService topicService = new TopicService();
        HashMap<String, ArrayList<String>> listData = new HashMap<>();
        boolean importantRow = false;
        for (Row row : sheet) {
            ArrayList<String> answers = new ArrayList<>();
            String potentialTopic = "";
            for (Cell cell : row) {
                importantRow = true;
                int rowNum = row.getRowNum();
                if (rowNum >= 4) {
                    String cellValue = cell.toString();
                    int column = cell.getColumnIndex();
                    result.append(rowNum).append(" = ").append(column).append(" = ").append(cell.toString());

                    switch (column) {
                        case 0:
                            potentialTopic = cellValue;
                            break;
                        case 1:
                            if (cellValue == "") {
                                importantRow = false;
                            }
                            answers.add(cellValue);
                            break;
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            answers.add(cellValue);
                            break;
                    }
                    result.append("\n");

                }
            }
            if (importantRow && !Objects.equals(potentialTopic, "")) {
                listData.put(potentialTopic, answers);
            }
        }

        return listData;
    }
}
