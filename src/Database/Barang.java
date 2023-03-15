package Database;

public class Barang 
{
    private String nama;
    private int harga;
    private int stok;
    
    public Barang(String nama, int harga, int stok)
    {
        setNama(nama);
        setHarga(harga);
        setStok(stok);
    }

    public String getNama()
    {
        return this.nama;
    }

    public int getHarga()
    {
        return this.harga;
    }

    public int getStok()
    {
        return this.stok;
    }

    private void setNama(String nama)
    {
        this.nama = nama;
    }

    private void setHarga(int harga)
    {
        this.harga = harga;
    }

    public void setStok(int stok)
    {
        this.stok = stok;
    }
}
