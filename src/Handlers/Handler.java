package Handlers;

import Database.Barang;
import Database.BarangDatabase;
import Database.User;
import Database.UserDatabase;
import Routes.Routes;
import DataStructure.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Handler
{
    final private static Scanner in = new Scanner(System.in);

    public static void newAccountProses()
    {
        Functions.displayHeader("NEW ACCOUNT PROCESS");
        String nama = Input.inputNameHandler();
        double saldo = Input.inputSaldoHandler();
        String nomorPelanggan = Input.nomorPelangganGenerator(saldo);
        String pin = Input.inputPinHandler();
        Functions.throwLoading("Membuat user");
        User user = new User(nama, nomorPelanggan, pin, saldo); 
        UserDatabase.addUser(user);
        System.out.printf("User telah dibuat!\n");
        System.out.printf("Nomor pelanggan kamu adalah %s\n",nomorPelanggan);
        System.out.printf("Kamu bisa mencoba untuk relogin\n");
        System.out.printf("Press enter "); in.nextLine();
        Routes.appLogin();
    }

    public static void authAccountProses()
    {
        Functions.displayHeader("AUTH ACCOUNT PROCESS");
        String noPelanggan = Input.inputNomorPelanggan();
        User user = UserDatabase.getUser(noPelanggan);

        while(user == null)
        {
            System.out.printf("User tidak ada dalam database\n");
            noPelanggan = Input.inputNomorPelanggan();
            user = UserDatabase.getUser(noPelanggan);
        }
        String pin = Input.inputPinHandler();
        boolean pinSama = user.getPin().equals(pin); 
        while(!pinSama && !user.getStatusBlock())
        {
            user.addTryCounter();
            System.out.printf("PIN yang dimasukkan salah!\n");
            pin = Input.inputPinHandler();
            pinSama = user.getPin().equals(pin); 
        }
        Functions.throwLoading("Melakukan proses auth");
        System.out.printf("Proses selesai!\n");
        System.out.printf("Press enter "); in.nextLine();
        Routes.appRoutes(user);
    }

    public static void topUpTransaction(User user) 
    {
        double saldo = Input.inputSaldoHandler() + user.getSaldo();
        Functions.throwLoading("Melakukan transaksi");
        user.setSaldo(saldo + user.getSaldo());
        try 
        {
            UserDatabase.saveData();
        } catch (Exception e)
        {
            Functions.errorMessage(e);
        }
        System.out.printf("Transaksi selesai!\n");
        System.out.printf("Saldo kamu sekarang : %.2f\n",user.getSaldo());
        System.out.printf("Press enter "); in.nextLine();
        Routes.appRoutes(user);
    }

    public static void buyTransaction(User user) 
    {
        ArrayList<Pair<Barang,Integer>> struk = new ArrayList<>();
        char choice = ' '; double total = 0;
        do
        {
            BarangDatabase.displayGroceries();
            System.out.printf("Saldo kamu : Rp %.2f\n",user.getSaldo());
            System.out.printf("Masukkan barang yang ingin dibeli : ");
            String nama = in.nextLine();
            Barang barang = BarangDatabase.getGrocery(nama);
            while(barang == null)
            {
                System.out.printf("Barang yang anda ketikkan tidak ada\n");
                System.out.printf("Masukkan barang yang ingin dibeli : ");
                nama = in.nextLine();
                barang = BarangDatabase.getGrocery(nama);
            }
            System.out.printf("Masukkan jumlah barang yang ingin dibeli : ");
            int jumlah = in.nextInt(); in.nextLine();
            while(jumlah > barang.getStok() || jumlah < 0) 
            {
                if(jumlah < 0 ) System.out.printf("Jumlah tidak boleh kurang dari 0\n");
                if(jumlah > barang.getStok()) System.out.printf("Jumlah melebihi stok barang\n");
                System.out.printf("Masukkan jumlah barang yang ingin dibeli : ");
                jumlah = in.nextInt(); in.nextLine();
            }
            struk.add(new Pair<>(barang,jumlah)); 
            System.out.printf("\nStruk Belanja\n");
            System.out.printf("-".repeat(70));
            System.out.printf("\n");
            System.out.printf("| %-20s | %-20s | %-20s |\n","Barang","Jumlah","Nilai");
            System.out.printf("-".repeat(70) + "\n"); 
            for(Pair<Barang,Integer> brng : struk)
            {
                System.out.printf("| %-20s | %-20d | %-20.2f |\n"
                ,brng.getKey().getNama(),brng.getValue(),(double)brng.getValue() * brng.getKey().getHarga());
            }
            System.out.printf("-".repeat(70) + "\n"); 
            total = Functions.getTotal(struk);
            System.out.printf("Total harga yang harus dibayar : Rp %.2f\n",total);
            while(total > user.getSaldo() - 10000) 
            {
                Functions.reduceGrocery(struk);
                System.out.printf("Press enter ");
                in.nextLine();
                Routes.appRoutes(user);
                return;
            }
            barang.setStok(barang.getStok() - jumlah);
            choice = Input.yesOrNoInput();
        } while(choice == 'y');  
        try 
        {
            BarangDatabase.saveGroceryData();
        }
        catch (Exception e)
        {
            Functions.errorMessage(e);
        }
        
        String jenisRek = user.getNomorPelanggan().substring(0,2);
        double[] cashback = {0,0};
        if(jenisRek.equals("38"))
            cashback[0] = 0.05;
        if(jenisRek.equals("56"))
        {
            cashback[0] = 0.07;
            cashback[1] = 0.02;
        }
        if(jenisRek.equals("74"))
        {
            cashback[0] = 0.10;
            cashback[1] = 0.05;
        } 
        Functions.throwLoading("Melakukan transaksi pembelian");
        total = total + (total * cashback[0]);
        user.setSaldo(user.getSaldo() - total + (cashback[1] * total)); 
        try 
        {
            UserDatabase.saveData();
        } catch (Exception e)
        {
            Functions.errorMessage(e);
        }
        System.out.printf("Transaksi selesai!\n");
        System.out.printf("Saldo kamu sekarang : %.2f\n",user.getSaldo());
        if(cashback[0] > 0)
            System.out.printf("Kamu mendapatkan cashbask sebesar : %.2f%% untuk struk\n",cashback[0] * 100);
        if(cashback[1] > 0)
            System.out.printf("Cashback sebesar : %.2f%% kembali ke saldo kamu!\n",cashback[1] * 100);
        System.out.printf("Press enter "); in.nextLine();
        Routes.appRoutes(user);
    }

    public static void exitProgram()
    {
        System.out.printf("Terima kasih sudah menggunakan program kami ^_^\n");
        System.exit(0);
    }
}
