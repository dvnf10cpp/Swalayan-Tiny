package Handlers;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;

public class Input 
{
    private static Scanner in = new Scanner(System.in);
    
    public static char inputChoiceHandler()
    {
        System.out.printf("Masukkan pilihanmu : ");
        char choice;
        choice = in.nextLine().charAt(0);
        while(choice != '1' && choice != '2' && choice != '3' && choice != '4')
        {
            System.out.printf("Pilihlah sesuai opsi yang ada\n");
            System.out.printf("Masukkan pilihamu : ");
            choice = in.nextLine().charAt(0);
        }
        return choice;
    }

    public static String inputNameHandler()
    {
        String pattern = "^(?=.*[a-zA-Z])[a-zA-Z ]*$";
        String nama;
        System.out.printf("Masukkan namamu : ");
        nama = in.nextLine();
        while(!nama.matches(pattern))
        {
            System.out.printf("Nama tidak boleh mengandung huruf\n");
            System.out.printf("Masukkan namamu : ");
            nama = in.nextLine();
        }
        return nama;
    }

    public static double inputSaldoHandler()
    {
        double saldo = 0; boolean notANumber = true;
        do{
            try
            {
                System.out.printf("Minimal topup harus Rp 10.000,00\n"); 
                System.out.printf("Masukkan saldo yang ingin ditopup : Rp ");
                saldo = in.nextInt(); 
                notANumber = false;
                in.nextLine();
            } catch(NumberFormatException nfe)
            {
                System.out.printf("Masukkan angka!");
                in.next();
            }
        } while(notANumber || saldo < 10000);
        return saldo; 
    }

    public static String inputNomorPelanggan()
    {
        String pattern = "\\d+";
        String pin;
        System.out.printf("Masukkan Nomor Pelanggan : ");
        pin = in.nextLine();
        while(!pin.matches(pattern))
        {
            System.out.printf("Nomor Pelanggan hanya bisa mengandung angka!\n");
            System.out.printf("Masukkan Nomor Pelanggan : ");
            pin = in.nextLine();
        }
        return pin;
    }

    public static String inputPinHandler()
    {
        String pattern = "\\d+";
        String pin;
        System.out.printf("Masukkan PIN : ");
        pin = in.nextLine();
        while(!pin.matches(pattern) || pin.length() < 6)
        {
            if(pin.length() < 6) System.out.printf("Minimal digit PIN adalah 6\n");
            if(!pin.matches(pattern)) System.out.printf("PIN hanya bisa mengandung angka\n");
            System.out.printf("Masukkan PIN : ");
            pin = in.nextLine();
        }
        return pin;
    }

    public static String nomorPelangganGenerator(double saldo)
    {
        LocalDate now = LocalDate.now();
        Random random = new Random();

        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());
        String year = String.format("%02d", now.getYear() % 100);
        int randomNum = random.nextInt(900) + 100; 
        String randomNoPelanggan = month + day + year + randomNum;
        if(saldo > 0)
            randomNoPelanggan = "38" + randomNoPelanggan.substring(1);
        if(saldo > 1_000_000)
            randomNoPelanggan = "56" + randomNoPelanggan.substring(1);
        if(saldo > 5_000_000)
            randomNoPelanggan = "74" + randomNoPelanggan.substring(1);
        
        if(randomNoPelanggan.length() > 10) randomNoPelanggan = randomNoPelanggan.substring(0,10);
        return randomNoPelanggan; 
    } 

    public static char yesOrNoInput()
    {
        System.out.printf("Apakah ada barang yang ingin dibeli lagi ? (y/n) : "); 
        char choice = in.nextLine().toLowerCase().charAt(0);
        while(choice != 'y' && choice != 'n')
        {
            System.out.printf("Bukan inputan yang valid!\n");
            System.out.printf("Apakah ada barang yang ingin dibeli lagi ? (y/n) : "); 
            choice = in.nextLine().toLowerCase().charAt(0);
        }
        return choice;
    }

    public static int inputNumberHandler()
    {
        int saldo = 0; boolean notANumber = true;
        do{
            try
            {
                System.out.printf("Masukkan jumlah yang ingin dikurangi : ");
                saldo = in.nextInt(); 
                notANumber = false;
                in.nextLine();
            } catch(NumberFormatException nfe)
            {
                System.out.printf("Masukkan angka!");
                in.next();
            }
        } while(notANumber);
        return saldo; 
    }
}
