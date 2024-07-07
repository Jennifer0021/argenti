package Lists.Product;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class ProductList {
    public ProductNode head;

    public ProductList() {
        this.head = null;
    }

    public void AddProduct(Product data) {
        ProductNode nuevoNodo = new ProductNode(data);
        if (head == null) {
            head = nuevoNodo;
        } else {
            ProductNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nuevoNodo;
        }
    }

    public void eliminar(String nombre) {
        if (head == null) return;

        if (head.data.getNombre().equals(nombre)) {
            head = head.next;
            return;
        }

        ProductNode temp = head;
        while (temp.next != null && !temp.next.data.getNombre().equals(nombre)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public Product buscar(String nombre) {
        ProductNode temp = head;
        while (temp != null) {
            if (temp.data.getNombre().equals(nombre)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void actualizar(String nombre, double precio, int stock, Image imagen) {
        Product producto = buscar(nombre);
        if (producto != null) {
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setImagen(imagen);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        ProductNode temp = head;
        while (temp != null) {
            products.add(temp.data);
            temp = temp.next;
        }
        return products;
    }

    public void listarProductos() {
        ProductNode temp = head;
        while (temp != null) {
            System.out.println(temp.data.toString());
            temp = temp.next;
        }
    }
}