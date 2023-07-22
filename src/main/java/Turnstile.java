import employees.Employee;
import employees.Manager;
import employees.Specialist;
import positions.Position;

import javax.naming.Name;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Turnstile {
    private ArrayList<Manager> managers;
    private ArrayList<Specialist> specialists;
    private HashSet<Position> positions;

    public Turnstile(){

    }

    public void addPosition(String posName, LocalTime start, LocalTime end){
        positions.add(new Position(posName,start,end));

    }
    public void addPosition(String posName){
        positions.add(new Position(posName));
    }

    public void addManager(String ID, String posName){
        for(Position pos : positions){
            if (pos.position_name.equals(posName)){

            }
        }
    }

}
