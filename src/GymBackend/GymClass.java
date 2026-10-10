package GymBackend;
 
public class GymClass {
    private String classID;
    private String className;
    private String trainerID;
    private String dayOfWeek;
    private String startTime;
    private int duration;
    private int capacity;
 
    public GymClass(String id, String cn, String tid, String dow,
                     String st, int dur, int cap) {
        classID = id;
        className = cn;
        trainerID = tid;
        dayOfWeek = dow;
        startTime = st;
        duration = dur;
        capacity = cap;
    }
 
    public String getClassID() 
    { 
        return classID; 
    }
    public String getClassName() 
    { 
        return className; 
    }
    public String getTrainerID() 
    { 
        return trainerID; 
    }
    public String getDayOfWeek() 
    { 
        return dayOfWeek; 
    }
    public String getStartTime() 
    { 
        return startTime; 
    }
    public int getDuration() 
    { 
        return duration; 
    }
    public int getCapacity() 
    { 
        return capacity; 
    }
    public void setCapacity(int cap) 
    { 
        capacity = cap; 
    }
 
    public String toString() {
        return classID + "," + className + "," + trainerID + "," + dayOfWeek + "," + startTime
                + "," + duration + "," + capacity;
    }
}
