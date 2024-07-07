package Lists.User;

import java.util.ArrayList;
import java.util.List;
public class UserList {
    private UserNode head;

    public UserList() {
        this.head = null;
    }

    // Método para agregar un usuario
    public void addUser(User data) {
        UserNode newNode = new UserNode(data);
        if (head == null) {
            head = newNode;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Método para eliminar un usuario por userId
    public void removeUser(String userId) {
        if (head == null) return;

        if (head.data.getUserId().equals(userId)) {
            head = head.next;
            return;
        }

        UserNode temp = head;
        while (temp.next != null && !temp.next.data.getUserId().equals(userId)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
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

    public String GetUid(String email) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.getEmail().equals(email)) {
                return temp.data.getUserId();
            }
            temp = temp.next;
        }
        return null;
    }

    // Método para encontrar un usuario por userId
    public User GetByUid(String userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.getUserId().equals(userId)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    // Método para actualizar la información de un usuario por userId
    public void updateUser(String userId, String email, String contrasenia, String nombre, String apellido) {
        User user = GetByUid(userId);
        if (user != null) {
            user.setEmail(email);
            user.setContrasenia(contrasenia);
            user.setNombre(nombre);
            user.setApellido(apellido);
        }
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        UserNode temp = head;
        while (temp != null) {
            users.add(temp.data);
            temp = temp.next;
        }
        return users;
    }

    public void ListUser() {
        UserNode temp = head;
        System.out.println("Lista de usuarios:");
        while (temp != null) {
            System.out.println(temp.data.getEmail() + temp.data.getUserId());
            temp = temp.next;
        }
    }

    public boolean LoginUser(String email, String contrasenia) {
        User usuario = FindUser(email);
        return usuario != null && usuario.getContrasenia().equals(contrasenia);
    }
}


//public class UserList {
//    public UserNode head;
//
//    public UserList() {
//        this.head = null;
//    }
//
//    public void ListUser() {
//        UserNode temp = head;
//        System.out.println("Lista de usuarios:");
//        while (temp != null) {
//            System.out.println(temp.data.getEmail());
//            temp = temp.next;
//        }
//    }
//
//    public void AddUser(User usuario) {
//        UserNode nuevoNodo = new UserNode(usuario);
//        if (head == null) {
//            head = nuevoNodo;
//        } else {
//            UserNode temp = head;
//            while (temp.next != null) {
//                temp = temp.next;
//            }
//            temp.next = nuevoNodo;
//        }
//    }
//
//    public User FindUser(String email) {
//        UserNode temp = head;
//        while (temp != null) {
//            if (temp.data.getEmail().equals(email)) {
//                return temp.data;
//            }
//            temp = temp.next;
//        }
//        return null;
//    }
//
//    public boolean LoginUser(String email, String contrasenia) {
//        User usuario = FindUser(email);
//        return usuario != null && usuario.getContrasenia().equals(contrasenia);
//    }
//}
