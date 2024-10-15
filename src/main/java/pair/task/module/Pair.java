package pair.task.module;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents needed information for a pair:
 * total days spend working together and
 * list of common projects and days spend together on them.
 */
public class Pair {
    private String first;
    private String second;
    private int totalDuration = 0;
    private final Map<String, Integer> durationsPerProject= new HashMap<>();

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void addToTotalDuration(int duration) {
        this.totalDuration += duration;
    }

    public Map<String, Integer> getDurationsPerProject() {
        return durationsPerProject;
    }
}
