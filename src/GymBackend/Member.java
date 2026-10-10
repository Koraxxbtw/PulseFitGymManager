package GymBackend;
 
public class Member {
    private String memberID;
    private String firstName;
    private String surname;
    private String cellNumber;
    private String email;
    private String dateOfBirth;
    private String username;
    private String password;
    private String membershipStatus; // "Active" or "Pending Renewal"
    private int points;
 
    public Member(String id, String fn, String sn, String cell,
                  String em, String dob, String un, String pw,
                  String ms, int pts) {
        memberID = id;
        firstName = fn;
        surname = sn;
        cellNumber = cell;
        email = em;
        dateOfBirth = dob;
        username = un;
        password = pw;
        membershipStatus = ms;
        points = pts;
    }
 
    public String getMemberID() 
    { 
        return memberID; 
    }
    public String getFirstName() 
    { 
        return firstName; 
    }
    public String getSurname() 
    {
        return surname; 
    }
    public String getCellNumber() 
    { 
        return cellNumber; 
    }
    public String getEmail() 
    { 
        return email; 
    }
    public String getDateOfBirth() 
    { 
        return dateOfBirth; 
    }
    public String getUsername() 
    { 
        return username; 
    }
    public String getPassword()
    { 
        return password; 
    }
    public String getMembershipStatus() 
    {
        return membershipStatus;
    }
    public int getPoints() 
    { 
        return points; 
    }
    public void setMembershipStatus(String ms) 
    { 
        membershipStatus = ms;
    }
    public void setPoints(int pts) 
    { 
        points = pts; 
    }
    public void addPoints(int amount) 
    { 
        points += amount;
    }
    
    public String toString() {
        return memberID + "," + firstName + "," + surname + "," + cellNumber + "," + email + ","
                + dateOfBirth + "," + username + "," + password + "," + membershipStatus + "," + points;
    }
}
