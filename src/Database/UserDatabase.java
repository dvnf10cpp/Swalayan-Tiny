package Database;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class UserDatabase 
{
    private static ArrayList<User> db = initData("./src/Database/Data/users.txt");

    private static ArrayList<User> initData(String pathFile)
    {
        String[] userData; 
        int tempTryCounter; double tempSaldo;
        String tempNama, tempNoPelanggan, tempPin;

        ArrayList<User> tempDb = new ArrayList<>();
        File data = new File(pathFile);
        Scanner cin; String tempUser;
        try 
        {
            cin = new Scanner(data);
            while(cin.hasNextLine())
            {
                tempUser = cin.nextLine();
                userData = tempUser.split(";");
                tempNama = userData[0];
                tempNoPelanggan = userData[1];
                tempPin = userData[2];
                tempSaldo = Double.parseDouble(userData[3]);
                tempTryCounter = Integer.parseInt(userData[4]);
                User user = new User(tempNama, tempNoPelanggan, tempPin ,tempSaldo, tempTryCounter);
                tempDb.add(user);
            }
        } catch(Exception e)
        {
            System.out.printf("You just found an error in our system!\n");
            System.out.println("init method");
            System.out.printf("%s\n",e.getMessage());
            System.exit(0);
        }
        return tempDb;
    }

    private static void addData(User user, String pathFile) throws Exception
    {
        FileWriter data = new FileWriter(pathFile,true); 
        String temp = user.getNama() + ";" +
        user.getNomorPelanggan() + ";" + 
        user.getPin() + ";" + 
        user.getSaldo() + ";" + 
        user.getTryCounter();
        try
        {
           BufferedWriter cout = new BufferedWriter(data);
           cout.write(temp);
           cout.newLine();
           cout.close();
        } catch (Exception e)
        {
            System.out.printf("You just found an error in our system!\n");
            System.out.println("add method"); 
            System.out.printf("%s\n",e.getMessage());
            System.exit(0);
        }
    }

    public static void saveData() throws Exception
    {
        File file = new File("./src/Database/Data/users.txt");
        PrintWriter cout = new PrintWriter(file); 
        for(User user : db)
        {
            cout.println(user.getNama() + ";" +
            user.getNomorPelanggan() + ";" + 
            user.getPin() + ";" + 
            user.getSaldo() + ";" + 
            user.getTryCounter()); 
        }
        cout.close();
    }

    public static ArrayList<User> getDb()
    {
        return db; 
    }

    public static User getUser(String noPelanggan)
    {
        for(User user : db)
        {
            if(user.getNomorPelanggan().equals(noPelanggan))
            {
                
                return user;
            }
        }
        
        return null;
    }

    public static void addUser(User user)
    {
        db.add(user); 
        try
        {
            addData(user, "./src/Database/Data/users.txt");
        } catch (Exception e)
        {
            System.out.printf("You just found an error in our system!\n");
            System.out.println("Adduser method"); 
            System.out.printf("%s\n",e.getMessage());
            System.exit(0);
        }
    }
}
