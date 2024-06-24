package DAO;

public class HistoryObject {
    private int uid;
    private int pid;
    private String name;
    private int stock;
    private double price;
    private byte[] image;
    private String buyDate;

    public HistoryObject(int uid, int pid, String name, int stock, double price, byte[] image, String buyDate) {
        this.uid = uid;
        this.pid = pid;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.image = image;
        this.buyDate = buyDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }
}
