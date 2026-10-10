package GymBackend;
 
public class Attendance {
    private String attendanceID;
    private String memberID;
    private String classID;
    private String checkInDate;
    private String checkInTime;
 
    public Attendance(String id, String mid, String cid,
                       String cd, String ct) {
        attendanceID = id;
        memberID = mid;
        classID = cid;
        checkInDate = cd;
        checkInTime = ct;
    }
 
    public String getAttendanceID() 
    { 
        return attendanceID; 
    }
    public String getMemberID() 
    { 
        return memberID; 
    }
    public String getClassID() 
    { 
        return classID; 
    }
    public String getCheckInDate() 
    { 
        return checkInDate; 
    }
    public String getCheckInTime() 
    { 
        return checkInTime; 
    }
 
    public String toString() {
        return attendanceID + "," + memberID + "," + classID + "," + checkInDate + "," + checkInTime;
    }
}
