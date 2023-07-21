package positions;
import employees.Employee;
import employees.Manager;

import java.time.LocalTime;

public class Position {
    public final String position_name;
    private Manager manager;
    public LocalTime start;
    public LocalTime end;
    public Position(String position, Manager manager,LocalTime start, LocalTime end){
        position_name = position;
        this.manager = manager;
        this.start  = start;
        this.end = end;
    }

    // working hours from 11  to 19  by default
    public Position(String position, Manager manager){
        position_name = position;
        this.manager = manager;
        this.start  = LocalTime.of(11,0);
        this.end = LocalTime.of(19,0);
    }

    public Human getManager(){
        return manager;
    }
    public void setManager(Human h){
        manager = h;
    }
    public String getPosition_name(){
        return position_name;
    }
}
