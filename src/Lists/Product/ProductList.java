package Lists.Product;

public class ProductList {
    public ProductNode head;

    public ProductList() {
        this.head = null;
    }

    public void agregar(Product data) {
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

    public void actualizar(String nombre, double precio, int stock) {
        Product producto = buscar(nombre);
        if (producto != null) {
            producto.setPrecio(precio);
            producto.setStock(stock);
        }
    }
}
