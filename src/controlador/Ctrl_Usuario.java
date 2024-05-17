package controlador;

import java.sql.ResultSet;
import java.sql.Connection;
import conexion.Conexion;
import java.sql.PreparedStatement;
import modelo.Usuario;
import java.sql.SQLException;

/**
 *
 * @author mcabral
 */
public class Ctrl_Usuario {

    /**
     * **************************************************
     * metodo para inciar sesion
     * **************************************************
     */
    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "SELECT password FROM tb_usuario WHERE usuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, objeto.getUsuario());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (HashUtil.verifyPassword(objeto.getPassword(), storedPassword)) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
        return respuesta;
    }

    /**
     **************************************
     * Metodo para guardar Usuario *************************************
     */
    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String saltedHash = HashUtil.generateSaltedHash(objeto.getPassword());

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono, rolUsuario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, saltedHash);
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getRolUsuario());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para veficar si existe el usuario
     * **************************************************
     */
    public boolean exiteUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "SELECT usuario FROM tb_usuario WHERE usuario = ?";
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para guardar un usuario
     * **************************************************
     */
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();

            // Obtener la contraseña actual almacenada en la base de datos
            String sql = "SELECT password FROM tb_usuario WHERE idUsuario = ?";
            statement = cn.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            rs = statement.executeQuery();
            String currentPassword = "";
            if (rs.next()) {
                currentPassword = rs.getString("password");
            }

            // Verificar si la nueva contraseña es diferente de la actual
            String newPassword = objeto.getPassword();
            if (!newPassword.equals(currentPassword)) {
                // Generar el hash de la nueva contraseña
                String newPasswordHash = HashUtil.generateSaltedHash(newPassword);

                try ( // Actualizar la contraseña en la base de datos
                        PreparedStatement updateStatement = cn.prepareStatement(
                                "UPDATE tb_usuario SET nombre=?, apellido=?, usuario=?, password=?, telefono=?, rolUsuario=?, estado=? WHERE idUsuario=?")) {
                    updateStatement.setString(1, objeto.getNombre());
                    updateStatement.setString(2, objeto.getApellido());
                    updateStatement.setString(3, objeto.getUsuario());
                    updateStatement.setString(4, newPasswordHash);
                    updateStatement.setString(5, objeto.getTelefono());
                    updateStatement.setString(6, objeto.getRolUsuario());
                    updateStatement.setInt(7, objeto.getEstado());
                    updateStatement.setInt(8, idUsuario);

                    if (updateStatement.executeUpdate() > 0) {
                        respuesta = true;
                    }
                }
            } else {
                // No es necesario actualizar la contraseña si es la misma
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para eliminar un usuario
     * **************************************************
     */
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_usuario WHERE idUsuario = ?");
            consulta.setInt(1, idUsuario);
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
        return respuesta;
    }

}
