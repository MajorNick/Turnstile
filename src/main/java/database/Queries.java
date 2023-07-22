package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Queries {
    private final Connection con;
    public Queries (Connection con){
        this.con  = con;
    }

    public boolean saveDailyAttendance(String id, LocalTime enter){
        PreparedStatement statement = null;
        boolean executed = false;
        try{
           statement =  con.prepareStatement("INSERT INTO " + DatabaseInfo.ATTENDANCE_TABLE +" (specialist_id,attendance_date,enter_time,exit_time) VALUES " +
                    id +" "+ LocalDate.now().toString() +" "+enter.format(DateTimeFormatter.ofPattern("HH:mm")));
             executed = statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return executed;
    }

    public void writeLateSpecialistsList(LocalDate from,LocalDate to){
        PreparedStatement statement = null;
        ResultSet result;
        try{
            statement =  con.prepareStatement("SELECT * FROM " + DatabaseInfo.ATTENDANCE_TABLE+ " WHERE attendance_date>= "+from +
                    " AND attendance_date<="+to +" ORDER BY attendance_date;" );
            result = statement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();

        }
        ArrayList data = new ArrayList<>();

    }
}
