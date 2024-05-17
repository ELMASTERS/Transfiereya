package controlador;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.ResultSet;
import conexion.Conexion;
import java.sql.PreparedStatement;
import modelo.Transaccion;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author mcabral
 */
public class Ctrl_Transaccion {

    private static final double USD_TO_EUR = 0.93664;
    private static final double USD_TO_DOP = 58;
    private static final double EUR_TO_USD = 1.068;
    private static final double EUR_TO_DOP = 61;
    private static final double DOP_TO_USD = 0.017;
    private static final double DOP_TO_EUR = 0.016;

    public static double obtenerTasaUSD_TO_DOP() {
        return USD_TO_DOP;
    }

    public static double obtenerTasaEUR_TO_DOP() {
        return EUR_TO_DOP;
    }

    public static double convertir(double cantidad, String monedaDe, String monedaA) {
        double resultado = cantidad;

        switch (monedaDe) {
            case "USD":
                switch (monedaA) {
                    case "EUR":
                        resultado = cantidad * USD_TO_EUR;
                        break;
                    case "DOP":
                        resultado = cantidad * USD_TO_DOP;
                        break;
                }
                break;

            case "EUR":
                switch (monedaA) {
                    case "USD":
                        resultado = cantidad * EUR_TO_USD;
                        break;
                    case "DOP":
                        resultado = cantidad * EUR_TO_DOP;
                        break;
                }
                break;

            case "DOP":
                switch (monedaA) {
                    case "USD":
                        resultado = cantidad * DOP_TO_USD;
                        break;
                    case "EUR":
                        resultado = cantidad * DOP_TO_EUR;
                        break;
                }
                break;
        }

        return resultado;
    }

    public boolean guardarTransaccion(Transaccion objeto, String paisRemitente, String paisDestinatario) throws TransaccionException {
        Connection cn = Conexion.conectar();
        boolean respuesta = false;

        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_transaccion (idClienteRemitente, idClienteDestinatario, montoTransaccion, monedaRemitente, monedaDestinatario, tipoCambio, totalPagar, fechaHoraTransaccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, objeto.getIdClienteRemitente());
            consulta.setInt(2, objeto.getIdClienteDestinatario());
            consulta.setDouble(3, objeto.getMontoTransaccion());
            consulta.setString(4, objeto.getMonedaRemitente());
            consulta.setString(5, objeto.getMonedaDestinatario());
            consulta.setDouble(6, objeto.getTipoCambio());
            consulta.setDouble(7, objeto.getTotalPagar());
            consulta.setString(8, objeto.getFechaHoraTransaccion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            consulta.setInt(9, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                // Actualizar el país del cliente remitente y destinatario
                actualizarPaisCliente(objeto.getIdClienteRemitente(), paisRemitente);
                actualizarPaisCliente(objeto.getIdClienteDestinatario(), paisDestinatario);
            }
            cn.close();
        } catch (SQLException e) {
            if (e instanceof MySQLIntegrityConstraintViolationException) {
                throw new TransaccionException("Error al guardar la transacción en la base de datos");
            } else {
                System.out.println("Error al guardar Usuario " + e);
            }
        }
        return respuesta;
    }

    private void actualizarPaisCliente(int idCliente, String pais) {
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            consulta = cn.prepareStatement("UPDATE tb_cliente SET pais = ? WHERE idCliente = ?");
            consulta.setString(1, pais);
            consulta.setInt(2, idCliente);
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el país del cliente: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }

    public class TransaccionException extends Exception {

        public TransaccionException(String message) {
            super(message);
        }
    }

    public ResultSet buscarTransaccion(String nombre, String apellido, String cedula) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = Conexion.conectar();

        try {

//            String sql = "SELECT t.idTransaccion, c1.nombre AS 'Cliente Remitente', c2.nombre AS 'Cliente Destinatario', "
//                    + "t.montoTransaccion, t.monedaRemitente, t.monedaDestinatario, t.tipoCambio, t.totalPagar, "
//                    + "t.fechaHoraTransaccion, t.estado "
//                    + "FROM tb_transaccion t "
//                    + "JOIN tb_cliente c1 ON t.idClienteRemitente = c1.idCliente "
//                    + "JOIN tb_cliente c2 ON t.idClienteDestinatario = c2.idCliente "
//                    + "WHERE c1.nombre LIKE ? OR c2.nombre LIKE ? OR c1.cedula LIKE ?";
            String sql = "SELECT t.idTransaccion, c1.nombre AS 'Cliente Remitente', c2.nombre AS 'Cliente Destinatario', "
                    + "t.montoTransaccion, t.monedaRemitente, t.monedaDestinatario, t.tipoCambio, t.totalPagar, "
                    + "t.fechaHoraTransaccion, "
                    + "CASE "
                    + "    WHEN t.estado = 1 THEN 'Pagado' "
                    + "    ELSE 'No Pagado' "
                    + "END AS 'Estado' "
                    + "FROM tb_transaccion t "
                    + "JOIN tb_cliente c1 ON t.idClienteRemitente = c1.idCliente "
                    + "JOIN tb_cliente c2 ON t.idClienteDestinatario = c2.idCliente "
                    + "WHERE c1.nombre LIKE ? OR c2.nombre LIKE ? OR c1.cedula LIKE ?";

            ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + apellido + "%");
            ps.setString(3, "%" + cedula + "%");

            rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Transaccion: " + e.getMessage());
            return null;

        }

    }

    public boolean actualizar(Transaccion objeto, int idTransaccion) {
        PreparedStatement consulta = null;
        boolean respuesta = false;
        Connection cn = null;

        try {

            // Obtener detalles del cliente utilizando sus identificadores
            Cliente clienteRemitente = obtenerCliente(objeto.getIdClienteRemitente());
            Cliente clienteDestinatario = obtenerCliente(objeto.getIdClienteDestinatario());

            cn = Conexion.conectar();

            consulta = cn.prepareStatement("UPDATE tb_transaccion AS t "
                    + "JOIN tb_cliente AS c1 ON t.idClienteRemitente = c1.idCliente "
                    + "JOIN tb_cliente AS c2 ON t.idClienteDestinatario = c2.idCliente "
                    + "SET t.idClienteRemitente = ?, "
                    + "  t.idClienteDestinatario = ?, "
                    + "  t.montoTransaccion = ?, "
                    + "  t.monedaRemitente = ?, "
                    + "  t.monedaDestinatario = ?, "
                    + "  t.tipoCambio = ?, "
                    + "  t.totalPagar = ?, "
                    + "  t.fechaHoraTransaccion = ?, "
                    + "  t.estado = ?, "
                    + "  c1.nombre = ?, " // Use el nombre del cliente recuperado
                    + "  c2.nombre = ? " // Use el nombre del cliente recuperado
                    + "WHERE t.idTransaccion = ?");

            consulta.setInt(1, objeto.getIdClienteRemitente());
            consulta.setInt(2, objeto.getIdClienteDestinatario());
            consulta.setDouble(3, objeto.getMontoTransaccion());
            consulta.setString(4, objeto.getMonedaRemitente());
            consulta.setString(5, objeto.getMonedaDestinatario());
            consulta.setDouble(6, objeto.getTipoCambio());
            consulta.setDouble(7, objeto.getTotalPagar());
            if (objeto.getFechaHoraTransaccion() != null) {
                consulta.setString(8, objeto.getFechaHoraTransaccion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } else {
                consulta.setString(8, null);
            }
            consulta.setInt(9, objeto.getEstado());
            consulta.setString(10, clienteRemitente.getNombre()); // Use el nombre del cliente recuperado
            consulta.setString(11, clienteDestinatario.getNombre()); // Use el nombre del cliente recuperado
            consulta.setInt(12, idTransaccion);

            respuesta = consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar transacción " + e.getMessage());
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

    public boolean eliminar(int idTransaccion) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_transaccion where idTransaccion ='" + idTransaccion + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar transaccion: " + e);
        }
        return respuesta;
    }

    public boolean verificarClienteExistente(String nombreCliente) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.conectar();
            String sql = "SELECT COUNT(idCliente) FROM tb_cliente WHERE nombre = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el cliente existe: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e);
            }
        }

        return false;
    }

    public int obtenerIdCliente(String nombreCliente) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.conectar();
            String sql = "SELECT idCliente FROM tb_cliente WHERE nombre = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idCliente");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del cliente: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e);
            }
        }

        return 0;
    }

    public Cliente obtenerCliente(int idCliente) {
        Cliente cliente = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            consulta = cn.prepareStatement("SELECT * FROM tb_cliente WHERE idCliente = ?");
            consulta.setInt(1, idCliente);
            rs = consulta.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                // Establecer otros atributos del cliente si es necesario
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return cliente;
    }

}
