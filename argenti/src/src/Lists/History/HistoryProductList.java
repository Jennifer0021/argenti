package Lists.History;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class HistoryProductList {
    private HistoryProductNode head;

    public HistoryProductList() {
        this.head = null;
    }

    public void addProductHistory(HistoryProduct data) {
        HistoryProductNode nuevoNodo = new HistoryProductNode(data);
        if (head == null) {
            head = nuevoNodo;
        } else {
            HistoryProductNode temp = head;
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

        HistoryProductNode temp = head;
        while (temp.next != null && !temp.next.data.getNombre().equals(nombre)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public HistoryProduct buscar(String nombre) {
        HistoryProductNode temp = head;
        while (temp != null) {
            if (temp.data.getNombre().equals(nombre)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void actualizar(String nombre, double precio, int stock, LocalDate fechaCompra) {
        HistoryProduct producto = buscar(nombre);
        if (producto != null) {
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setFechaCompra(fechaCompra);
        }
    }

    public List<HistoryProduct> getAllProductHistories() {
        List<HistoryProduct> products = new ArrayList<>();
        HistoryProductNode temp = head;
        while (temp != null) {
            products.add(temp.data);
            temp = temp.next;
        }
        return products;
    }
}

