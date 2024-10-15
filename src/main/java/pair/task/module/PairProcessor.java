package pair.task.module;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Processor for scv file with the following format:
 * EmpID, ProjectID, DateFrom, DateTo
 * That uses produces by {@see CSVReader.class} map like
 *  project1: <employee1,startDate,endDate>
 *            <employee2,startDate,endDate>
 *  project2: <employee3,startDate,endDate>
 * To find for each project those pairs working on overlapping period of time on it.
 * For each matching pair a {@see Pair.class} instance is created to keep
 * common projects and their duration and
 * increment the total common days if employees match on more than one project.
 * getTopPair returns the pair working of same or different projects for the longest period.
 * If there is more than one pairs with same result the last processed is returned.
 */
public class PairProcessor {

    private final Map<Integer, Pair> pairsData = new HashMap<>();
    private Pair topPair = null;

    public void processPairs(File file) {
        CsvReader reader = new CsvReader();
        reader.read(file);
        Map<String, List<CsvData>> csvData = reader.getCsvData();
        processData(csvData);
        topPair = processTopPair();
    }

    public Pair getTopPair() {
        return topPair;
    }

    private void processData(Map<String, List<CsvData>> csvData) {
        for(String project : csvData.keySet()) {
            if(csvData.get(project).size() > 1) {
                processProject(project, csvData.get(project));
            }
        }
    }

    private void processProject(String project, List<CsvData> csvData) {
        for(int i = 0; i < csvData.size(); i++) {
            CsvData first = csvData.get(i);
            for(int j = i+1; j < csvData.size(); j++) {
                CsvData second = csvData.get(j);

                if(first.getEndDate().before(second.getStartDate()) || second.getEndDate().before(first.getStartDate())) {
                    continue;
                }
                Date start = first.getStartDate().before(second.getStartDate()) ? second.getStartDate() : first.getStartDate();
                Date end = first.getEndDate().before(second.getEndDate()) ? first.getEndDate() : second.getEndDate();

                long diff = end.getTime() - start.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
                int duration = (int) diffDays;

                int hash = first.getEmployee().hashCode() + second.getEmployee().hashCode();
                Pair pair = pairsData.get(hash);
                if(pair == null) {
                    Pair newPair = new Pair(first.getEmployee(), second.getEmployee());
                    newPair.getDurationsPerProject().put(project, duration);
                    newPair.setTotalDuration(duration);
                    pairsData.put(hash, newPair);
                } else {
                    pair.getDurationsPerProject().put(project, duration);
                    pair.addToTotalDuration(duration);
                }
            }

        }
    }

    private Pair processTopPair() {
        Pair currentTop = null;
        int topDuration = -1;
        for(Map.Entry<Integer, Pair> pair : pairsData.entrySet()) {
            if(pair.getValue().getTotalDuration() > topDuration) {
                topDuration = pair.getValue().getTotalDuration();
                currentTop = pair.getValue();
            }
        }
        return currentTop;
    }
}
