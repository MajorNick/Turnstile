package employees;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import positions.Position;
import reports.WorkHours;
public class Specialist implements Employee{
    public  Position position;
    public  String ID_NUMBER;
    private HashMap<String, WorkHours> history;

    public Specialist(String id,Position pos){
        ID_NUMBER = id;
        position = pos;
        history = new HashMap<>();
    }
    @Override
    public boolean Equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Position)){
            return false;
        }

        Employee man = (Employee)obj;
        return this.ID_NUMBER.equals(man.getId());
    }
    @Override
    public String getId(){
        return ID_NUMBER;
    }
    public void putStartTime(String date){
         WorkHours w = new WorkHours(LocalTime.now());
        history.put(date,w);
    }
    public void putEndTime(String date){
        WorkHours w = history.getOrDefault(date,new WorkHours(null));
        w.setEndTime(LocalTime.now());
        history.put(date,w);
    }
    public LocalDate[] getDate(){
        return null;
    }

}
