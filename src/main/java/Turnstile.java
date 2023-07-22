import employees.Employee;
import employees.Manager;
import employees.Specialist;
import positions.Position;
import reports.WorkHours;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Turnstile {
    private final HashSet<Manager> managers;
    private final HashSet<Specialist> specialists;
    private final HashSet<Position> positions;

    private final HashMap<String, WorkHours> dailyHistory;

    public Turnstile(){
        specialists = new HashSet<>();
        managers = new HashSet<>();
        positions = new HashSet<>();
        dailyHistory = new HashMap<>();
    }

    public void addPosition(String posName, LocalTime start, LocalTime end){
        positions.add(new Position(posName,start,end));

    }
    public void addPosition(String posName){
        positions.add(new Position(posName));
    }

    public void addManager(String ID, String posName){
        for(Position pos : positions){
            if (pos.position_name.equals(posName)) {
                Manager m = new Manager(ID, pos);
                pos.setManager(m);
                managers.add(m);
                return;
            }
        }
    }

    public void addSpecialist(String ID,String posName) {
        for(Position pos : positions){
            if (pos.position_name.equals(posName)) {
                specialists.add(new Specialist(ID,pos));
                break;
            }
        }
    }

    public boolean enter(String id){
        for(Specialist spec : specialists){

        }
        for(Manager m : managers){

        }
        return false;
    }
    public void exit(String id){
        Specialist spec = new Specialist(id,null);
        if (specialists.contains(spec)){
            WorkHours work = dailyHistory.get(id);

        }
    }



}
