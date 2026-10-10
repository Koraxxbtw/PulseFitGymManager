package GymBackend;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class MemberArray {
    private static final String FILE_NAME = "Members.txt";
    private Member[] memberList;
    private int size;
 
    public MemberArray() {
        memberList = new Member[10];
        size = 0;
        readFromFile();
    }
 
    private void grow() 
    { 
        memberList = Arrays.copyOf(memberList, memberList.length * 2); 
    }
 
    public void addMember(Member m) 
    {
        if (m == null) 
        {
            return;
        }
        if (size == memberList.length) 
        {
            grow();
        }
        memberList[size] = m;
        size++;
        writeToFile();
    }
 
    public void editMember(int index, Member m) 
    {
        if (index < 0 || index >= size || m == null) 
        {
            return;
        }
        memberList[index] = m;
        writeToFile();
    }
 
    public void deleteMember(String memberID) 
    {
        int index = indexOfID(memberID);
        if (index == -1) 
        {
            return;
        }
        for (int i = index; i < size - 1; i++) 
        {
            memberList[i] = memberList[i + 1];
        }
        memberList[size - 1] = null;
        size--;
        writeToFile();
    }
 
    private int indexOfID(String memberID) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (memberList[i].getMemberID().equalsIgnoreCase(memberID)) 
            {
                return i;
            }
        }
        return -1;
    }
 
    public Member searchByID(String memberID) 
    {
        int index = indexOfID(memberID);
        return (index == -1) ? null : memberList[index];
    }
 
    public Member searchByUsername(String username) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (memberList[i].getUsername().equalsIgnoreCase(username)) 
            {
                return memberList[i];
            }
        }
        return null;
    }
 
    public int getSize() 
    { 
        return size; 
    }
 
    public Member getMember(int index) 
    { 
        return memberList[index]; 
    }
 
    public Member[] getPendingRenewals() 
    {
        ArrayList<Member> pending = new ArrayList<>();
        for (int i = 0; i < size; i++) 
        {
            if (memberList[i].getMembershipStatus().equalsIgnoreCase("Pending Renewal")) 
            {
                pending.add(memberList[i]);
            }
        }
        return pending.toArray(new Member[0]);
    }
 
    public void writeToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (int i = 0; i < size; i++) 
            {
                writer.write(memberList[i].toString());
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
                if (parts.length != 10) 
                {
                    continue;
                }
                try 
                {
                    int pts = Integer.parseInt(parts[9].trim());
                    Member m = new Member(parts[0], parts[1], parts[2], parts[3], parts[4],
                            parts[5], parts[6], parts[7], parts[8], pts);
                    addMemberSilently(m);
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
 
    private void addMemberSilently(Member m) 
    {
        if (size == memberList.length) 
        {
            grow();
        }
        memberList[size] = m;
        size++;
    }
}
