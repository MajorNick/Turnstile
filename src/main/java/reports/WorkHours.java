package reports;


import java.time.LocalTime;

public class WorkHours  {
    private final LocalTime startTime;
    private LocalTime endTime;
    public WorkHours(LocalTime startTime) {
        this.startTime = startTime;

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime time){
        this.endTime = endTime;
    }

}
