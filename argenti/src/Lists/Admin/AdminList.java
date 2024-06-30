package Lists.Admin;

public class AdminList {
    public AdminNode head;

    public AdminList() {
        this.head = null;
    }

    public void agregar(Admin admin) {
        AdminNode nuevoNodo = new AdminNode(admin);
        if (head == null) {
            head = nuevoNodo;
        } else {
            AdminNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nuevoNodo;
        }
    }

    public Admin buscar(String email) {
        AdminNode temp = head;
        while (temp != null) {
            if (temp.data.getEmail().equals(email)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean verificarCredenciales(String email, String contrasenia) {
        Admin admin = buscar(email);
        return admin != null && admin.getContrasenia().equals(contrasenia);
    }
}

