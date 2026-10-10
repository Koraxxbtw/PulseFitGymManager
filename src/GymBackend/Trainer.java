package GymBackend;
 
public class Trainer {
    private String trainerID;
    private String firstName;
    private String surname;
    private String cellNumber;
    private String email;
    private String specialty;
 
    public Trainer(String id, String fn, String sn, String cell,
                    String em, String sp) {
        trainerID = id;
        firstName = fn;
        surname = sn;
        cellNumber = cell;
        email = em;
        specialty = sp;
    }
 
    public String getTrainerID() 
    { 
        return trainerID; 
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
    public String getSpecialty() 
    { 
        return specialty; 
    }
    public void setSpecialty(String sp) 
    { 
        specialty = sp; 
    }
 
    public String toString() {
        return trainerID + "," + firstName + "," + surname + "," + cellNumber + "," + email + "," + specialty;
    }
}
