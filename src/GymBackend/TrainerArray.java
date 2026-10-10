package GymBackend;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
 
public class TrainerArray {
    private static final String FILE_NAME = "Trainers.txt";
    private Trainer[] trainerList;
    private int size;
 
    public TrainerArray() {
        trainerList = new Trainer[10];
        size = 0;
        readFromFile();
    }
 
    private void grow() 
    { 
        trainerList = Arrays.copyOf(trainerList, trainerList.length * 2); 
    }
 
    public void addTrainer(Trainer t) 
    {
        if (t == null) 
        {
            return;
        }
        if (size == trainerList.length) 
        {
            grow();
        }
        trainerList[size] = t;
        size++;
        writeToFile();
    }
 
    public void editTrainer(int index, Trainer t) 
    {
        if (index < 0 || index >= size || t == null) 
        {
            return;
        }
        trainerList[index] = t;
        writeToFile();
    }
 
    public void deleteTrainer(String trainerID) 
    {
        int index = indexOfID(trainerID);
        if (index == -1) 
        {
            return;
        }
        for (int i = index; i < size - 1; i++) 
        {
            trainerList[i] = trainerList[i + 1];
        }
        trainerList[size - 1] = null;
        size--;
        writeToFile();
    }
 
    private int indexOfID(String trainerID) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (trainerList[i].getTrainerID().equalsIgnoreCase(trainerID)) 
            {
                return i;
            }
        }
        return -1;
    }
 
    public Trainer searchByID(String trainerID) 
    {
        int index = indexOfID(trainerID);
        return (index == -1) ? null : trainerList[index];
    }
 
    public int getSize() 
    { 
        return size; 
    }
 
    public Trainer getTrainer(int index) 
    { 
        return trainerList[index]; 
    }
 
    public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (int i = 0; i < size; i++) 
            {
                writer.write(trainerList[i].toString());
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
                if (parts.length != 6) 
                {
                    continue;
                }
                Trainer t = new Trainer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                if (size == trainerList.length) 
                {
                    grow();
                }
                trainerList[size] = t;
                size++;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
