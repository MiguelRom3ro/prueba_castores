package com.example.prueba_castores.models;

public class Historial {

    int idMovimiento;
    int idProducto;
    int idUsuario;
    int nombreUser;
    int nombreProducto;
    String movimiento;
    int cantidad;
    String dateTim;

    public Historial() {
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDateTim() {
        return dateTim;
    }

    public void setDateTim(String dateTim) {
        this.dateTim = dateTim;
    }

    public String getNombreUser() {
        return dateTim;
    }

    public void setNombreUser(String nombreUser) {
        this.dateTim = dateTim;
    }

    public String getNombreProducto() {
        return dateTim;
    }

    public void setNombreProducto(String nombreProducto) {
        this.dateTim = dateTim;
    }
}