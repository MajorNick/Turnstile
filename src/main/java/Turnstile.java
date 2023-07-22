import employees.Employee;
import employees.Manager;
import employees.Specialist;
import positions.Position;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class Turnstile {
    private final HashSet<Manager> managers;
    private final HashSet<Specialist> specialists;
    private final HashSet<Position> positions;

    public Turnstile(){
        specialists = new HashSet<>();
        managers = new HashSet<>();
        positions = new HashSet<>();
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
            if (spec.getId().equals(id)){
                String date = LocalDate.now().toString();
                spec.putStartTime(date);
                return true;
            }
        }
        for(Manager m : managers){
            if (m.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public void exit(String id){
        for(Specialist spec : specialists){
            if (spec.getId().equals(id)){
                String date = LocalDate.now().toString();
                spec.putEndTime(date);
                return;
            }
        }
    }

}
