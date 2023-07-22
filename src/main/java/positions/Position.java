package positions;
import employees.Employee;
import employees.Manager;

import java.time.LocalTime;

public class Position {
    public final String position_name;
    private Manager manager;
    public LocalTime start;
    public LocalTime end;
    public Position(String position,LocalTime start, LocalTime end){
        position_name = position;
        this.manager = manager;
        this.start  = start;
        this.end = end;
    }

    // working hours from 11  to 19  by default
    public Position(String position){
        position_name = position;
        this.manager = manager;
        this.start  = LocalTime.of(11,0);
        this.end = LocalTime.of(19,0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Position)){
            return false;
        }
        Position pos = (Position)obj;
        return this.position_name.equals(pos.position_name);
    }

    public Employee getManager(){
        return manager;
    }
    public void setManager(Manager h){
        manager = h;
    }
    public String getPosition_name(){
        return position_name;
    }
}
