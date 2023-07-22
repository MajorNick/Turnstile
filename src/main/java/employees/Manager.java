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
    public String getId(){
        return ID_NUMBER;
    }
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

}
