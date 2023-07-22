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
    private final Queries queries;
    public Turnstile(){
        specialists = new HashSet<>();
        managers = new HashSet<>();
        positions = new HashSet<>();
        dailyHistory = new HashMap<>();
        visitors = new HashMap<>();
        queries = new Queries();
    }
    // Positions in Company, gets parameters: Position Name, Daily start time,Daily end time
    public void addPosition(String posName, LocalTime start, LocalTime end){
        positions.add(new Position(posName,start,end));

    }
    // Positions in Company, gets parameters: Position Name,
    // Daily start time and Daily end time are 11am and 7pm by default
    public void addPosition(String posName){
        positions.add(new Position(posName));
    }

    //adding manager and then assigning to Position (every position has its own manager)
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
    // add Specialist, with position, Ex: BE_DEVELOPER,FE_DEVELOPER
    public void addSpecialist(String ID,String posName) {
        for(Position pos : positions){
            if (pos.position_name.equals(posName)) {
                specialists.add(new Specialist(ID,pos));
                break;
            }
        }
    }

    //it is checking in data and if you are working here or visited in this day it returns true,
    //true means it is opened, and you can enter
    //also it is saving enter time of specialist for future use;
    public boolean enter(String id){
        //contains methond is depended on object's overrided method, so
        // in this  implementation position isn't needed to compare 2 specialist instance;
        Specialist s = new Specialist(id,null);
        //same as specialist
        Manager m = new Manager(id,null);
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

    // everyone can exit from building but,
    // if person is specialist it is adding current date,enter time and exit time to database
    public void exit(String id){
        //contains methond is depended on object's overrided method, so
        // in this  implementation position isn't needed to compare 2 specialist instance;
        Specialist spec = new Specialist(id,null);
        if (specialists.contains(spec)){
            WorkHours work = dailyHistory.get(id);
            boolean writer = queries.saveDailyAttendance(id,LocalTime.now());
            if (!writer){
                System.err.println("Something is wrong in executing query");
            }
        }
    }
    // adding visitor and date to data
    public void addVisitor(String id,LocalDate date) {
        if (LocalDate.now().isAfter(date)) {
            System.err.println("wrong date, visit date must be in future or today!");
            return;
        }
        visitors.put(id, date);
    }
    // if manager want to get attendance list from date to date,
    //after using this it will write in CSV file called Attendance.csv
    public void writeAttendance(LocalDate from,LocalDate to){
        if(!queries.writeDailyAttendanceCSV(from,to)){
            System.err.println("wrong date, visit date must be in future or today!");
        }
        System.out.println("Data is written in Attendance.csv file");
    }


}
