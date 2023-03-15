package Routes;

import Handlers.Functions;
import Handlers.Handler;
import Handlers.Input;
import Database.User;

public class Routes 
{

    public static void appLogin()
    {
        Functions.printNewManyLine();
        Functions.displayHeader("LOGIN PAGE SWALAYAN TINY");
        System.out.printf("Pilihlah salah satu pilihan ini\n");
        System.out.printf("1. Sudah punya akun\n");
        System.out.printf("2. Belum punya akun\n");
        char choice = Input.inputChoiceHandler(); 
        switch(choice)
        {
            case '1':
                Handler.authAccountProses();
                break;
            case '2':
                Handler.newAccountProses();
                break;
            case '3':
                Handler.exitProgram();
                break;
        }
    }

    public static void appRoutes(User user)
    {
        Functions.printNewManyLine();
        Functions.displayHeader("SELAMAT DATANG DI SWALAYAN TINY");
        System.out.printf("Halo %s ^_^!\n",user.getNama());
        System.out.printf("Pilih transaksi yang ingin anda lakukan\n");
        System.out.printf("1. Pembelian\n");
        System.out.printf("2. Top Up\n");
        System.out.printf("3. Log out\n"); 
        System.out.printf("4. Exit program\n");
        char choice = Input.inputChoiceHandler(); 
        switch(choice)
        {
            case '1':
                Handler.buyTransaction(user);
                break;
            case '2':
                Handler.topUpTransaction(user);
                break;
            case '3':
                appLogin();
                break;
            case '4':
                Handler.exitProgram();
        }
    }
}
