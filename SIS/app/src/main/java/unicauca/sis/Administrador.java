package unicauca.sis;

public class Administrador {

    private String usuario;
    private String contrasena;
    private String nombreCompleto;

    public Administrador() { }

    public Administrador(String usuario, String contrasena, String nombreCompleto) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
