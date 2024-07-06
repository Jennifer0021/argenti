package Lists.Admin;

public class AdminList {
    public AdminNode head;

    public AdminList() {
        this.head = null;
    }

    public void ListAdmin() {
        AdminNode temp = head;
        System.out.println("Lista de administradores:");
        while (temp != null) {
            System.out.println(temp.data.getEmail());
            temp = temp.next;
        }
    }

    public void AddAdmin(Admin admin) {
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

    public Admin FindAdmin(String email) {
        AdminNode temp = head;
        while (temp != null) {
            if (temp.data.getEmail().equals(email)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean LoginAdmin(String email, String contrasenia) {
        Admin admin = FindAdmin(email);
        return admin != null && admin.getContrasenia().equals(contrasenia);
    }
}

