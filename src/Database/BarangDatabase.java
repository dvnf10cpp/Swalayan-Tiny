package Database;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import Handlers.Functions;

public class BarangDatabase 
{
    private static ArrayList<Barang> groceries = initData("./src/Database/Data/groceries.txt");
    //WIP UBAH DARI USER JADI BARANG
    private static ArrayList<Barang> initData(String pathFile)
    {
        String[] groceryData; 
        String tempNama; int tempStok, tempHarga;

        ArrayList<Barang> tempGroceries = new ArrayList<>();
        File data = new File(pathFile);
        Scanner cin; String tempUser;
        try 
        {
            cin = new Scanner(data);
            while(cin.hasNextLine())
            {
                tempUser = cin.nextLine();
                groceryData = tempUser.split(";");
                tempNama = groceryData[0];
                tempHarga = Integer.parseInt(groceryData[1]);
                tempStok = Integer.parseInt(groceryData[2]);
                Barang barang = new Barang(tempNama,tempHarga,tempStok);
                tempGroceries.add(barang);
            }
        } catch(Exception e)
        {
            System.out.printf("You just found an error in our system!\n");
            System.out.printf("%s\n",e.getMessage());
            System.exit(0);
        }
        return tempGroceries;
    }

    public static void displayGroceries()
    {
        Functions.displayHeader("LIST BARANG");
        System.out.printf("| %-20s | %-15s | %-15s |\n","Nama","Harga","Stok"); 
        System.out.printf("-".repeat(60));
        for(Barang barang : groceries)
        {
            System.out.printf("\n");
            System.out.printf("| %-20s | %-15d | %-15d |",barang.getNama(),barang.getHarga(),barang.getStok());
            
        }
        System.out.printf("\n");
        System.out.printf("-".repeat(60));
        System.out.printf("\n");
    }

    public static void saveGroceryData() throws Exception
    {
        File file = new File("./src/Database/Data/groceries.txt");
        PrintWriter cout = new PrintWriter(file); 
        for(Barang barang : groceries)
        {
            cout.println(barang.getNama() + ";" + barang.getHarga() + ";" + barang.getStok()); 
        }
        cout.close();
    }

    public static Barang getGrocery(String nama)
    {
        for(Barang barang : groceries)
        {
            if(barang.getNama().equals(nama))
            {
                return barang;
            }
        }
        return null;
    }
}
