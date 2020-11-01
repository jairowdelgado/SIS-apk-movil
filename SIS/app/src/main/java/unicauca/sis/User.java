package unicauca.sis;

import java.io.Serializable;

public class User implements Serializable {

    private String usuario;
    private String contrasenia;
    private String nombreCompleto;

    public User() { }

    public User(String usuario, String contrasenia, String nombreCompleto) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
