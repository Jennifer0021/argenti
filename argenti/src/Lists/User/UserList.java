package Lists.User;

import Lists.Admin.Admin;
import Lists.Admin.AdminNode;

public class UserList {
    public UserNode head;

    public UserList() {
        this.head = null;
    }

    public void ListUser() {
        UserNode temp = head;
        System.out.println("Lista de usuarios:");
        while (temp != null) {
            System.out.println(temp.data.getEmail());
            temp = temp.next;
        }
    }

    public void AddUser(User usuario) {
        UserNode nuevoNodo = new UserNode(usuario);
        if (head == null) {
            head = nuevoNodo;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nuevoNodo;
        }
    }

    public User FindUser(String email) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.getEmail().equals(email)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean LoginUser(String email, String contrasenia) {
        User usuario = FindUser(email);
        return usuario != null && usuario.getContrasenia().equals(contrasenia);
    }
}
