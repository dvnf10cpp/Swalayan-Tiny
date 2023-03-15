package Handlers;

import java.util.ArrayList;

import DataStructure.Pair;
import Database.Barang;

public class Functions 
{
    public static double getTotal(ArrayList<Pair<Barang,Integer>> data)
    {
        double sum = 0;
        for(Pair<Barang,Integer> tmp : data)
        {
            sum += tmp.getValue();
        }
        return sum;
    }

    public static void reduceGrocery(ArrayList<Pair<Barang,Integer>> struk)
    {
        System.out.printf("Transaksi gagal\n");
        System.out.printf("Maaf harga barang yang ingin dibeli melebihi saldo\n");
        System.out.printf("Saldo pada akun harus memiliki setidaknya Rp 10.000,00\n");
        if(true)
        {
            return;
        }

        //Dibawah merupakan implementasi jika transaksi tidak gagal
        System.out.printf("Silahkan kurangi barang\n");
        
        Pair<Barang,Integer> barang = null;
        do {
            System.out.printf("Masukkan barang yang ingin dikurangi : ");
            String namaBarang = Input.inputNameHandler();
            for(Pair<Barang,Integer> tmp : struk)
            {
                if(tmp.getKey().equals(namaBarang))
                {
                    barang = tmp;
                    break;
                }
            }
        } while(barang == null);
        int jumlah = Input.inputNumberHandler();
        while(jumlah > barang.getValue())
        {
            System.out.printf("Jumlah yang dimasukkan melebih jumlah yang ada di struk");
            jumlah = Input.inputNumberHandler();
        }
        barang.setValue(barang.getValue() + jumlah);
    }


    public static void displayHeader(String message)    
    {
        System.out.println("=".repeat(50 + message.length()));
        System.out.printf(" ".repeat(25));
        System.out.printf("%s",message); 
        System.out.printf(" ".repeat(25));
        System.out.printf("\n");  
        System.out.println("=".repeat(50 + message.length()));
    }

    public static void throwLoading(String message) 
    {
        try 
        {
            System.out.print(message);
            Thread.sleep(350);
            System.out.print(".");
            Thread.sleep(550);
            System.out.print(".");
            Thread.sleep(650);
            System.out.print(".");
            Thread.sleep(850);
            System.out.print("."); 
            Thread.sleep(1100);
            System.out.print(".");
            System.out.print("\n"); 
        } catch (Exception e)
        {
            errorMessage(e);
        }
    }

    public static void printNewManyLine()
    {
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void errorMessage(Exception e)
    {
        System.err.printf("You just found an error in our system!\n");
        System.err.printf("%s\n",e.getMessage());
        System.exit(0);
    }
}
