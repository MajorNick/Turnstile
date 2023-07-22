package database;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
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
    public Queries (){
        con = (new Database()).getConnection();
    }

    // executing query which is inserting row, which contains information of,
    // specialist id, date, enter time and exit time
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
    // executing query to get attendance list from date to date and writing it in Attendance.csv file
    public boolean writeDailyAttendanceCSV(LocalDate from,LocalDate to){
        PreparedStatement statement = null;
        ResultSet result;
        try{
            statement =  con.prepareStatement("SELECT * FROM " + DatabaseInfo.ATTENDANCE_TABLE+ " WHERE attendance_date>= "+from +
                    " AND attendance_date<="+to +" GROUP BY specialist ORDER BY specialist,attendance_date;" );
            result = statement.executeQuery();
            try{
                FileWriter fileWriter = new FileWriter("attendance.csv");
                fileWriter.append("specialist,attendance_date,enter_time,exit_time\n");
                while (result.next()) {
                    String specialist = result.getString("specialist");
                    String attendanceDate = result.getString("attendance_date");
                    String start = result.getString("start_time");
                    String exit = result.getString("exit_time");
                    fileWriter.append(specialist).append(",").append(attendanceDate).append(",").append(start).append(",").append(exit).append('\n');
                }
                result.close();
                statement.close();
            }catch(IOException e){
                e.printStackTrace();
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
