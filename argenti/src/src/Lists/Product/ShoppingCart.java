package Lists.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private ProductNode head;

    public ShoppingCart() {
        this.head = null;
    }

    public void AddProduct(Product data) {
        ProductNode newNode = new ProductNode(data);
        if (head == null) {
            head = newNode;
        } else {
            ProductNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void removeProduct(String nombre) {
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

    public Product findProduct(String nombre) {
        ProductNode temp = head;
        while (temp != null) {
            if (temp.data.getNombre().equals(nombre)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void updateProduct(String nombre, double precio, int stock) {
        Product producto = findProduct(nombre);
        if (producto != null) {
            producto.setPrecio(precio);
            producto.setStock(stock);
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
}
