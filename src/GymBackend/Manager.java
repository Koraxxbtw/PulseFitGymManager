package GymBackend;
 
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Manager {
    public MemberArray memberArray;
    public TrainerArray trainerArray;
    public GymClassArray classArray;
    public BookingArray bookingArray;
    public AttendanceArray attendanceArray;
    public String loggedInUser;
    public String userType;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "Admin#2026";
    private static final int POINTS_PER_CHECKIN = 10;
 
    public Manager() {
        memberArray = new MemberArray();
        trainerArray = new TrainerArray();
        classArray = new GymClassArray();
        bookingArray = new BookingArray();
        attendanceArray = new AttendanceArray();
        loggedInUser = "";
        userType = "";
    }
 
    public String login(String username, String password) 
    {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) 
        {
            return "ERROR: Username and password cannot be empty.";
        }
        if (username.equalsIgnoreCase(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) 
        {
            userType = "Admin";
            loggedInUser = ADMIN_USERNAME;
            return "Admin";
        }
        Member m = memberArray.searchByUsername(username);
        if (m == null) 
        {
            return "ERROR: No account found for that username.";
        }
        if (!m.getPassword().equals(password)) 
        {
            return "ERROR: Incorrect password.";
        }
        userType = "Member";
        loggedInUser = m.getMemberID();
        return "Member";
    }
 
    public String registerMember(String firstName, String surname, String cellNumber,
                                  String email, String dateOfBirth, String username, String password) 
    {
        if (firstName == null || firstName.trim().isEmpty()
                || surname == null || surname.trim().isEmpty()
                || cellNumber == null || cellNumber.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || dateOfBirth == null || dateOfBirth.trim().isEmpty()
                || username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) 
        {
            return "ERROR: All fields are required.";
        }
        if (!email.contains("@") || !email.contains(".")) 
        {
            return "ERROR: Please enter a valid email address.";
        }
        if (!cellNumber.matches("\\d{10}")) 
        {
            return "ERROR: Cell number must be exactly 10 digits.";
        }
        if (memberArray.searchByUsername(username) != null) 
        {
            return "ERROR: That username is already taken.";
        }
        String newID = "M" + String.format("%03d", memberArray.getSize() + 1);
        Member newMember = new Member(newID, firstName, surname, cellNumber, email,
                dateOfBirth, username, password, "Active", 0);
        memberArray.addMember(newMember);
        return "SUCCESS";
    }
 
    public String bookClass(String memberID, String classID) 
    {
        GymClass gc = classArray.searchByID(classID);
        if (gc == null) 
        {
            return "ERROR: That class could not be found.";
        }
        int confirmedCount = 0;
        for (int i = 0; i < bookingArray.getSize(); i++) 
        {
            Booking b = bookingArray.getBooking(i);
            if (b.getClassID().equalsIgnoreCase(classID) && b.getStatus().equalsIgnoreCase("Confirmed")) 
            {
                confirmedCount++;
            }
        }
        if (confirmedCount >= gc.getCapacity()) 
        {
            return "ERROR: This class is already fully booked.";
        }
        String newID = "B" + String.format("%03d", bookingArray.getSize() + 1);
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Booking newBooking = new Booking(newID, memberID, classID, today, "Confirmed");
        bookingArray.addBooking(newBooking);
        return "SUCCESS";
    }
 
    public String cancelBooking(String bookingID) 
    {
        Booking b = bookingArray.searchByID(bookingID);
        if (b == null) 
        {
            return "ERROR: Booking not found.";
        }
        bookingArray.cancelBooking(bookingID);
        return "SUCCESS";
    }
 
    public String checkIn(String memberID, String classID) 
    {
        String newID = "A" + String.format("%03d", attendanceArray.getSize() + 1);
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String now = new SimpleDateFormat("HH:mm").format(new Date());
        Attendance newAttendance = new Attendance(newID, memberID, classID, today, now);
        attendanceArray.addAttendance(newAttendance);
        Member m = memberArray.searchByID(memberID);
        if (m != null) 
        {
            m.addPoints(POINTS_PER_CHECKIN);
            memberArray.writeToFile();
        }
        return "SUCCESS: You earned " + POINTS_PER_CHECKIN + " points!";
    }
 
    public String approveRenewal(String memberID) 
    {
        Member m = memberArray.searchByID(memberID);
        if (m == null) 
        {
            return "ERROR: Member not found.";
        }
        m.setMembershipStatus("Active");
        memberArray.writeToFile();
        return "SUCCESS";
    }
 
    public String getAttendanceStats(String classID) 
    {
        int count = attendanceArray.getAttendanceCount(classID);
        GymClass gc = classArray.searchByID(classID);
        String name = (gc == null) ? classID : gc.getClassName();
        return "Total check-ins for " + name + ": " + count;
    }
}
