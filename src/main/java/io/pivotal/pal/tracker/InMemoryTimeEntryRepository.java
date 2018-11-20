package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    public static Map<Long, TimeEntry> timeEntryHashMap = new HashMap<Long, TimeEntry>();
    public static int counter = 0;

    public TimeEntry find(long id) {
        if(!timeEntryHashMap.containsKey(id))
            return null;
        TimeEntry data = timeEntryHashMap.get(id);
        return data;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        Long id = new Long(counter + 1);
        counter = counter + 1;
        timeEntryHashMap.put(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        for (Map.Entry<Long, TimeEntry> entry : timeEntryHashMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (!timeEntryHashMap.containsKey(id)) {
            return null;
        }
        timeEntry.setId(id);
        timeEntryHashMap.put(id, timeEntry);
        return timeEntry;
    }

    public void delete(long id) {
        timeEntryHashMap.remove(id);
    }
}
