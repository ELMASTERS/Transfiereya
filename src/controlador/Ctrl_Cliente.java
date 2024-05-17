package controlador;

import java.sql.Statement;
import conexion.Conexion;
import modelo.Cliente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author mcabral
 */
//public class Ctrl_Cliente {
//
//    public ResultSet buscarCliente(String nombre, String apellido, String cedula) {
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection cn = Conexion.conectar();
//
//        try {
//
//            String sql = "SELECT idCliente, nombre, apellido, cedula, pais, telefono, direccion, estado "
//                    + "FROM tb_cliente "
//                    + "WHERE cedula LIKE ? OR nombre LIKE ? OR apellido LIKE ?";
//
//            ps = cn.prepareStatement(sql);
//            ps.setString(1, "%" + nombre + "%");
//            ps.setString(2, "%" + apellido + "%");
//            ps.setString(3, "%" + cedula + "%");
//
//            rs = ps.executeQuery();
////            if (!rs.next()) {
////                JOptionPane.showMessageDialog(null, "No se encontraron resultados");
////            }
//
//            return rs;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//
//        }
//
//    }
//
//    public boolean guardar(Cliente objeto) {
//
//        boolean respuesta = false;
//
//        Connection cn = Conexion.conectar();
//
//        try {
//            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values (?,?,?,?,?,?,?,?)");
//            consulta.setInt(1, 0); //id
//            consulta.setString(2, objeto.getNombre());
//            consulta.setString(3, objeto.getApellido());
//            consulta.setString(4, objeto.getCedula());
//            consulta.setString(5, objeto.getPais());
//            consulta.setString(6, objeto.getTelefono());
//            consulta.setString(7, objeto.getDireccion());
//            consulta.setInt(8, objeto.getEstado());
//
//            if (consulta.executeUpdate() > 0) {
//                respuesta = true;
//            }
//            cn.close();
//        } catch (SQLException e) {
//            System.out.println("Error al guardar cliente " + e);
//        }
//        return respuesta;
//
//    }
//
//    public boolean exiteCliente(String cedula) {
//
//        boolean respuesta = false;
//
//        String sql = "select cedula from tb_cliente where cedula = '" + cedula + "';";
//        Statement st;
//
//        try {
//            Connection cn = Conexion.conectar();
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                respuesta = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al consultar Cliente" + e);
//        }
//        return respuesta;
//    }
//
//    public boolean actualizar(Cliente objeto, int idCliente) {
//        boolean respuesta = false;
//        Connection cn = Conexion.conectar();
//        try {
//
//            PreparedStatement consulta = cn.prepareStatement("update tb_cliente set nombre=?, apellido = ?, cedula = ?,pais = ?, telefono= ?, direccion = ?, estado = ? where idCliente ='" + idCliente + "'");
//            consulta.setString(1, objeto.getNombre());
//            consulta.setString(2, objeto.getApellido());
//            consulta.setString(3, objeto.getCedula());
//            consulta.setString(4, objeto.getPais());
//            consulta.setString(5, objeto.getTelefono());
//            consulta.setString(6, objeto.getDireccion());
//            consulta.setInt(7, objeto.getEstado());
//
//            if (consulta.executeUpdate() > 0) {
//                respuesta = true;
//            }
//            cn.close();
//        } catch (SQLException e) {
//            System.out.println("Error al actualizar cliente: " + e);
//        }
//        return respuesta;
//    }
//
//    public boolean eliminar(int idCliente) {
//        boolean respuesta = false;
//        Connection cn = Conexion.conectar();
//        try {
//            PreparedStatement consulta = cn.prepareStatement(
//                    "delete from tb_cliente where idCliente ='" + idCliente + "'");
//            consulta.executeUpdate();
//
//            if (consulta.executeUpdate() > 0) {
//                respuesta = true;
//            }
//            cn.close();
//        } catch (SQLException e) {
//            System.out.println("Error al eliminar cliente: " + e);
//        }
//        return respuesta;
//    }
//
//}
public class Ctrl_Cliente {

    public ResultSet buscarCliente(String nombre, String apellido, String cedula) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = Conexion.conectar();

        try {

            String sql = "SELECT idCliente, nombre, apellido, cedula, pais, telefono, direccion, estado "
                    + "FROM tb_cliente "
                    + "WHERE cedula LIKE ? OR nombre LIKE ? OR apellido LIKE ?";

            ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + cedula + "%");
            ps.setString(2, "%" + nombre + "%");
            ps.setString(3, "%" + apellido + "%");

            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
            return null;
        }

    }

    public boolean actualizar(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            consulta = cn.prepareStatement("UPDATE tb_cliente SET nombre=?, apellido = ?, cedula = ?,pais = ?, telefono= ?, direccion = ?, estado = ? WHERE idCliente = ?");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getPais());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, objeto.getEstado());
            consulta.setInt(8, idCliente);

            respuesta = consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean guardar(Cliente objeto) {

        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            consulta = cn.prepareStatement("INSERT INTO tb_cliente (nombre, apellido, cedula, pais, telefono, direccion, estado) VALUES (?,?,?,?,?,?,?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getPais());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, objeto.getEstado());

            respuesta = consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;

    }

    public boolean existeCliente(String cedula) {

        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT cedula FROM tb_cliente WHERE cedula = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            respuesta = rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            consulta = cn.prepareStatement("DELETE FROM tb_cliente WHERE idCliente = ?");
            consulta.setInt(1, idCliente);

            respuesta = consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

}
