package GymBackend;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class BookingArray {
    private static final String FILE_NAME = "Bookings.txt";
    private Booking[] bookingList;
    private int size;
 
    public BookingArray() {
        bookingList = new Booking[10];
        size = 0;
        readFromFile();
    }
 
    private void grow() 
    { 
        bookingList = Arrays.copyOf(bookingList, bookingList.length * 2); 
    }
 
    public void addBooking(Booking b) 
    {
        if (b == null) 
        {
            return;
        }
        if (size == bookingList.length) 
        {
            grow();
        }
        bookingList[size] = b;
        size++;
        writeToFile();
    }
 
    public void cancelBooking(String bookingID) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (bookingList[i].getBookingID().equalsIgnoreCase(bookingID)) 
            {
                bookingList[i].setStatus("Cancelled");
                writeToFile();
                return;
            }
        }
    }
 
    public Booking searchByID(String bookingID) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (bookingList[i].getBookingID().equalsIgnoreCase(bookingID)) 
            {
                return bookingList[i];
            }
        }
        return null;
    }
 
    public Booking[] getBookingsForMember(String memberID) 
    {
        ArrayList<Booking> results = new ArrayList<>();
        for (int i = 0; i < size; i++) 
        {
            if (bookingList[i].getMemberID().equalsIgnoreCase(memberID)) 
            {
                results.add(bookingList[i]);
            }
        }
        return results.toArray(new Booking[0]);
    }
 
    public int getSize() 
    { 
        return size; 
    }
 
    public Booking getBooking(int index) 
    { 
        return bookingList[index]; 
    }
 
    public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (int i = 0; i < size; i++) 
            {
                writer.write(bookingList[i].toString());
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
                Booking b = new Booking(parts[0], parts[1], parts[2], parts[3], parts[4]);
                if (size == bookingList.length) 
                {
                    grow();
                }
                bookingList[size] = b;
                size++;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
