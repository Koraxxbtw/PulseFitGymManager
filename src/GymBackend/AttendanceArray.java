package GymBackend;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class AttendanceArray {
    private static final String FILE_NAME = "Attendance.txt";
    private Attendance[] attendanceList;
    private int size;
 
    public AttendanceArray() {
        attendanceList = new Attendance[10];
        size = 0;
        readFromFile();
    }
 
    private void grow() 
    { 
        attendanceList = Arrays.copyOf(attendanceList, attendanceList.length * 2); 
    }
 
    public void addAttendance(Attendance a) 
    {
        if (a == null) 
        {
            return;
        }
        if (size == attendanceList.length) 
        {
            grow();
        }
        attendanceList[size] = a;
        size++;
        writeToFile();
    }
 
    public Attendance[] getAttendanceForClass(String classID) 
    {
        ArrayList<Attendance> results = new ArrayList<>();
        for (int i = 0; i < size; i++) 
        {
            if (attendanceList[i].getClassID().equalsIgnoreCase(classID)) 
            {
                results.add(attendanceList[i]);
            }
        }
        return results.toArray(new Attendance[0]);
    }
 
    public int getAttendanceCount(String classID) 
    { 
        return getAttendanceForClass(classID).length; 
    }
 
    public int getSize() 
    { 
        return size; 
    }
 
    public Attendance getAttendance(int index) 
    { 
        return attendanceList[index]; 
    }
 
    public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (int i = 0; i < size; i++) 
            {
                writer.write(attendanceList[i].toString());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing to " + FILE_NAME + ": " + e.getMessage());
        }
    }
 
    public void readFromFile() 
    {
        File file = new File(FILE_NAME);
        if (!file.exists()) 
        {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.trim().isEmpty()) 
                {
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length != 5) 
                {
                    continue;
                }
                Attendance a = new Attendance(parts[0], parts[1], parts[2], parts[3], parts[4]);
                if (size == attendanceList.length) 
                {
                    grow();
                }
                attendanceList[size] = a;
                size++;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
