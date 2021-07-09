package org.prueba.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String nombreCompleto;
	
	@Column(nullable = false, length = 100)
	private String apellidosCompletos;
	
	@Column(nullable = false, length = 1)
	private String genero;
	
	@Column(nullable = false, length = 255, unique = true)
	private String email;
	
	@Column(nullable = false, length = 20, unique = true)
	private String usuario;
	
	@Column(nullable = false, length = 100)
	private String contrasena;
	
	@Column(updatable = false)
	private LocalDateTime fechaRegistro;
	
	@Column(insertable = false)
	private LocalDateTime fechaUltimoIngreso;
	
	@Column(nullable = false)
	private Integer intentos;
	
	@Column
	private LocalDateTime fechaBloqueo;

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getApellidosCompletos() {
		return apellidosCompletos;
	}

	public void setApellidosCompletos(String apellidosCompletos) {
		this.apellidosCompletos = apellidosCompletos;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDateTime getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(LocalDateTime fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public LocalDateTime getFechaBloqueo() {
		return fechaBloqueo;
	}

	public void setFechaBloqueo(LocalDateTime fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
