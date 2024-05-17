package modelo;

/**
 *
 * @author mcabral
 */
public class Usuario {

    //atributos
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String telefono;
    private String rolUsuario;
    private int estado;

    //contructor
    public Usuario() {
        this.idUsuario = 0;
        this.nombre = "";
        this.apellido = "";
        this.usuario = "";
        this.password = "";
        this.telefono = "";
        this.rolUsuario = "";
        this.estado = 0;

    }

    public Usuario(int idUsuario, String nombre, String apellido, String usuario, String password, String telefono, String rolUsuario, int estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.telefono = telefono;
        this.rolUsuario = rolUsuario;
        this.estado = estado;
    }
    
    

  

    //set and get
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
      public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", password=" + password + ", telefono=" + telefono + ", rolUsuario=" + rolUsuario + ", estado=" + estado + '}';
    }
    
    

}
