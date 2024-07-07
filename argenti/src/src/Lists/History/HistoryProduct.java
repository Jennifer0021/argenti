package Lists.History;

import javafx.scene.image.Image;
import java.time.LocalDate;

public class HistoryProduct {
    private String nombre;
    private double precio;
    private int stock;
    private Image imagen;
    private LocalDate fechaCompra;

    public HistoryProduct(String nombre, double precio, int stock, Image imagen, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.fechaCompra = fechaCompra;
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

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " - Stock: " + stock + " - Imagen: " + imagen + " - Fecha de compra: " + fechaCompra;
    }
}

