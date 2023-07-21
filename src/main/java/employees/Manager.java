package employees;

import positions.Position;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Manager implements Employee{

    public  Position position;
    public  String ID_NUMBER;



    public Manager(String id,Position pos){
        ID_NUMBER = id;
        position = pos;

    }
    public LocalDate[] getDate(){
        return null;
    }
}
