package pair.task.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class to read a csv file and prepare a structure of data like:
 *  project1: <employee1,startDate,endDate>
 *            <employee2,startDate,endDate>
 *  project2: <employee3,startDate,endDate>
 */
public class CsvReader {

    private final Map<String, List<CsvData>> records = new HashMap<>();

    public Map<String, List<CsvData>> getCsvData() {
        return records;
    }

    public void read(File file) {
        try {
            readFile(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void readFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                getRecordFromLine(scanner.nextLine());
            }
        }
    }
    private CsvData getRecordFromLine(String line) {
        if(line.isBlank() || line.contains("EmpID")) {
            //Discard header
            return null;
        }

        String[] value = line.split(",", 4);
        if(value.length != 4) {
            //Discard incorrect lines
            return null;
        }

        String employee = value[0].trim();
        String project = value[1].trim();
        Date fromDate = DateParser.getDate(value[2]);
        Date toDate = DateParser.getDate(value[3]);

        CsvData data = new CsvData(employee, project, fromDate, toDate);
        List<CsvData> current = records.get(project);
        if(current == null) {
            records.put(project, new ArrayList<>(List.of(data)));
        } else {
            current.add(data);
        }
        return data;
    }

}
