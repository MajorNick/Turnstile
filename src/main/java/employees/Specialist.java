package employees;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

import positions.Position;
import reports.WorkHours;
public class Specialist implements Employee{
    public  Position position;
    public  String ID_NUMBER;
    public HashMap<String,WorkHours> dates;

    public Specialist(String id,Position pos){
        ID_NUMBER = id;
        position = pos;
        dates = new HashMap<>();

    }
    public LocalDate[] getDate(){
        return null;
    }

}
