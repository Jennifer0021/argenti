package Lists.Product;

import javafx.scene.image.Image;

public class Product {
    private String nombre;
    private double precio;
    private int stock;
    private Image imagen;

    public Product(String nombre, double precio, int stock, Image imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " - Stock: " + stock + "imagen" + imagen;
    }
}

