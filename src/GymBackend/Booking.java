package GymBackend;
 
public class Booking {
    private String bookingID;
    private String memberID;
    private String classID;
    private String bookingDate;
    private String status;
 
    public Booking(String id, String mid, String cid, String bd, String st) {
        bookingID = id;
        memberID = mid;
        classID = cid;
        bookingDate = bd;
        status = st;
    }
 
    public String getBookingID() 
    { 
        return bookingID; 
    }
    public String getMemberID() 
    { 
        return memberID; 
    }
    public String getClassID() 
    { 
        return classID; 
    }
    public String getBookingDate() 
    { 
        return bookingDate; 
    }
    public String getStatus() 
    { 
        return status; 
    }
    public void setStatus(String st) 
    { 
        status = st; 
    }
 
    public String toString() {
        return bookingID + "," + memberID + "," + classID + "," + bookingDate + "," + status;
    }
}
