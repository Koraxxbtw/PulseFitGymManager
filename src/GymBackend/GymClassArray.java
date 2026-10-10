package GymBackend;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
 
public class GymClassArray {
    private static final String FILE_NAME = "Classes.txt";
    private GymClass[] classList;
    private int size;
 
    public GymClassArray() {
        classList = new GymClass[10];
        size = 0;
        readFromFile();
    }
 
    private void grow() 
    { 
        classList = Arrays.copyOf(classList, classList.length * 2); 
    }
 
    public void addClass(GymClass c) 
    {
        if (c == null) 
        {
            return;
        }
        if (size == classList.length) 
        {
            grow();
        }
        classList[size] = c;
        size++;
        writeToFile();
    }
 
    public void editClass(int index, GymClass c) 
    {
        if (index < 0 || index >= size || c == null) 
        {
            return;
        }
        classList[index] = c;
        writeToFile();
    }
 
    public void deleteClass(String classID) 
    {
        int index = indexOfID(classID);
        if (index == -1) 
        {
            return;
        }
        for (int i = index; i < size - 1; i++) 
        {
            classList[i] = classList[i + 1];
        }
        classList[size - 1] = null;
        size--;
        writeToFile();
    }
 
    private int indexOfID(String classID) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (classList[i].getClassID().equalsIgnoreCase(classID)) 
            {
                return i;
            }
        }
        return -1;
    }
 
    public GymClass searchByID(String classID) 
    {
        int index = indexOfID(classID);
        return (index == -1) ? null : classList[index];
    }
 
    public int getSize() 
    { 
        return size; 
    }
 
    public GymClass getClass(int index) 
    { 
        return classList[index]; 
    }
 
    public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (int i = 0; i < size; i++) 
            {
                writer.write(classList[i].toString());
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
                if (parts.length != 7) 
                {
                    continue;
                }
                try 
                {
                    int duration = Integer.parseInt(parts[5].trim());
                    int capacity = Integer.parseInt(parts[6].trim());
                    GymClass c = new GymClass(parts[0], parts[1], parts[2], parts[3], parts[4], duration, capacity);
                    if (size == classList.length) 
                    {
                        grow();
                    }
                    classList[size] = c;
                    size++;
                } 
                catch (NumberFormatException nfe) 
                {
                    System.out.println("Skipped corrupt row in " + FILE_NAME + ": " + line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
