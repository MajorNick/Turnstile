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
import database.Queries;

public class Turnstile {
    private final HashSet<Manager> managers;
    private final HashSet<Specialist> specialists;
    private final HashSet<Position> positions;

    private final HashMap<String, WorkHours> dailyHistory;
    private final HashMap<String, LocalDate> visitors;
    private Queries queries;
    public Turnstile(){
        specialists = new HashSet<>();
        managers = new HashSet<>();
        positions = new HashSet<>();
        dailyHistory = new HashMap<>();
        visitors = new HashMap<>();
        queries = new Queries();
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
        Manager m = new Manager(id,null);
        Specialist s = new Specialist(id,null);
        if (specialists.contains(s)){
            dailyHistory.put(id,new WorkHours(LocalTime.now()));
            return true;
        }
        if (managers.contains(m)){
            return true;
        }

        if(visitors.containsKey(id)){
            LocalDate date = visitors.get(id);
            return date.isEqual(LocalDate.now());
        }

        return false;
    }
    public void exit(String id){
        Specialist spec = new Specialist(id,null);
        if (specialists.contains(spec)){
            WorkHours work = dailyHistory.get(id);
            boolean writer = queries.saveDailyAttendance(id,LocalTime.now());
            if (!writer){
                System.err.println("Something is wrong in executing query");
            }
        }
    }
    public void addVisitor(String id,LocalDate date) {
        if (LocalDate.now().isAfter(date)) {
            System.err.println("wrong date, visit date must be in future or today!");
            return;
        }
        visitors.put(id, date);
    }

    public void writeAttendance(LocalDate from,LocalDate to){
        if(!queries.writeDailyAttendanceCSV(from,to)){
            System.err.println("wrong date, visit date must be in future or today!");
        }
        System.out.println("Data is written in Attendance.csv file");
    }


}
