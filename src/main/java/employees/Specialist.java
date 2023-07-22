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


    public Specialist(String id,Position pos){
        ID_NUMBER = id;
        position = pos;

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

    public LocalDate[] getDate(){
        return null;
    }

}
