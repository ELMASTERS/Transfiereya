package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author mcabral
 */
public class Transaccion {

    private int idTransaccion;
    private int idClienteRemitente;
    private int idClienteDestinatario;
    private double montoTransaccion;
    private String monedaRemitente;
    private String monedaDestinatario;
    private double tipoCambio;
    private double totalPagar;
    private LocalDateTime fechaHoraTransaccion;
    private int estado;

    public Transaccion() {
        this.idTransaccion = 0;
        this.idClienteRemitente = 0;
        this.idClienteDestinatario = 0;
        this.montoTransaccion = 0.0;
        this.monedaRemitente = "";
        this.monedaDestinatario = "";
        this.tipoCambio = 0.0;
        this.totalPagar = 0.0;
        this.fechaHoraTransaccion = null;
        this.estado = 0;

    }

    public Transaccion(int idTransaccion, int idClienteRemitente, int idClienteDestinatario, double montoTransaccion, String monedaRemitente, String monedaDestinatario, double tipoCambio, double totalPagar, LocalDateTime fechaHoraTransaccion, int estado) {
        this.idTransaccion = idTransaccion;
        this.idClienteRemitente = idClienteRemitente;
        this.idClienteDestinatario = idClienteDestinatario;
        this.montoTransaccion = montoTransaccion;
        this.monedaRemitente = monedaRemitente;
        this.monedaDestinatario = monedaDestinatario;
        this.tipoCambio = tipoCambio;
        this.totalPagar = totalPagar;
        this.fechaHoraTransaccion = fechaHoraTransaccion;
        this.estado = estado;

    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdClienteRemitente() {
        return idClienteRemitente;
    }

    public void setIdClienteRemitente(int idClienteRemitente) {
        this.idClienteRemitente = idClienteRemitente;
    }

    public int getIdClienteDestinatario() {
        return idClienteDestinatario;
    }

    public void setIdClienteDestinatario(int idClienteDestinatario) {
        this.idClienteDestinatario = idClienteDestinatario;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getMonedaRemitente() {
        return monedaRemitente;
    }

    public void setMonedaRemitente(String monedaRemitente) {
        this.monedaRemitente = monedaRemitente;
    }

    public String getMonedaDestinatario() {
        return monedaDestinatario;
    }

    public void setMonedaDestinatario(String monedaDestinatario) {
        this.monedaDestinatario = monedaDestinatario;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public LocalDateTime getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(LocalDateTime fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTransaccion=" + idTransaccion + ", idClienteRemitente=" + idClienteRemitente + ", idClienteDestinatario=" + idClienteDestinatario + ", montoTransaccion=" + montoTransaccion + ", monedaRemitente=" + monedaRemitente + ", monedaDestinatario=" + monedaDestinatario + ", tipoCambio=" + tipoCambio + ", totalPagar=" + totalPagar + ", fechaHoraTransaccion=" + fechaHoraTransaccion + ", estado=" + estado + '}';
    }

}
