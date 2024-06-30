package Lists.User;

public class UserList {
    public UserNode head;

    public UserList() {
        this.head = null;
    }

    public void agregar(Usuario usuario) {
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

    public User buscar(String email) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.getEmail().equals(email)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean verificarCredenciales(String email, String contrasenia) {
        User usuario = buscar(email);
        return usuario != null && usuario.getContrasenia().equals(contrasenia);
    }
}
