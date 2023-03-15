package Database;

public class User
{
    private String nama;
    private String nomorPelanggan;
    private String pin;
    private double saldo;
    private boolean isBlocked;
    private int tryCounter;

    public User(String nama, String nomorPelanggan,String pin, double saldo)
    {
        setNama(nama);
        setNomorPelanggan(nomorPelanggan);
        setPin(pin);
        setSaldo(saldo);
        setTryCounter(0);
        setBlocked();
    }

    protected User(String nama, String nomorPelanggan, String pin, double saldo, int tryCounter)
    {
        setNama(nama);
        setNomorPelanggan(nomorPelanggan);
        setPin(pin);
        setSaldo(saldo);  
        setTryCounter(tryCounter);
        setBlocked();
    }

    public void addTryCounter()
    {
        tryCounter++;
        setBlocked();
    }

    private void setBlocked()
    {
        if(tryCounter >= 3)
        {
            isBlocked = true;
            System.out.printf("Maaf akses untuk user ini telah keblokir\n");
            System.out.printf("Silahkan hubungi layanan terdekat kami\n");
            System.out.printf("Untuk menonaktifkan status blokir\n");
            System.exit(0);
        } 
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public void setNomorPelanggan(String nomorPelanggan)
    {
        this.nomorPelanggan = nomorPelanggan;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    private void setTryCounter(int tryCounter)
    {
        this.tryCounter = tryCounter;
    }

    public String getNama()
    {
        return this.nama;
    }

    public String getNomorPelanggan()
    {
        return this.nomorPelanggan;
    }

    public String getPin()
    {
        return this.pin;
    }

    public double getSaldo()
    {
        return this.saldo;
    }

    public int getTryCounter()
    {
        return this.tryCounter;
    }

    public boolean getStatusBlock()
    {
        return this.isBlocked; 
    }
}