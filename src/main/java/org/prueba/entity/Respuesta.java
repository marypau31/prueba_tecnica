package org.prueba.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Respuesta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mensaje;
	
	private Integer intento;
	
	private Integer status;
	
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	private String saludo;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getFecha() {
		return LocalDateTime.now();
	}

	public Integer getIntento() {
		return intento;
	}

	public void setIntento(Integer intento) {
		this.intento = intento;
	}
	
	
}
