package org.prueba.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.prueba.entity.Respuesta;
import org.prueba.entity.Usuario;
import org.prueba.repository.Usuariorepository;
import org.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private Usuariorepository repository;

	@Override
	public Respuesta inicioSesion(Usuario usuario) {
		Usuario Usuarior= repository.findByUsuarioOrEmail(usuario.getUsuario(), usuario.getUsuario());
		if(Usuarior == null) {
			Respuesta respuesta = new Respuesta();
			respuesta.setMensaje("Error de Usuario y/o Contraseña");
			respuesta.setIntento(3);
			return respuesta;
		} else {
			if(Usuarior.getFechaBloqueo() != null) {
				LocalDateTime bloqueo = Usuarior.getFechaBloqueo();
				LocalDateTime ahora = LocalDateTime.now();
				
				long minutes = Duration.between(bloqueo, ahora).toMinutes();
				if(minutes <= 30 ) {
					Respuesta respuesta = new Respuesta();
					respuesta.setMensaje("Usuario bloqueado por "+(30-minutes)+" minutos");
					return respuesta;
				} else {
					Usuarior.setIntentos(3);
					Usuarior.setFechaBloqueo(null);
					return intentoIniciarSesion(Usuarior, usuario.getContrasena());
				}
			} else return intentoIniciarSesion(Usuarior, usuario.getContrasena());
		}
	}

	private Respuesta intentoIniciarSesion(Usuario usuario, String password) {
		Respuesta respuesta = new Respuesta();
		if(usuario.getContrasena().equals(password)) {
			//Reinicia el conteo, coloca fecha de ingreso y desbloquea si pasaron los 30min
			usuario.setIntentos(3);
			usuario.setFechaBloqueo(null);
			usuario.setFechaUltimoIngreso(LocalDateTime.now());
			repository.save(usuario);
			respuesta = saludar(usuario, respuesta);
		} else {
			if(usuario.getIntentos() > 1) {
				usuario.setIntentos(usuario.getIntentos() -1);
				repository.save(usuario);
				respuesta.setMensaje("Error de Usuario y/o Contraseña");
				respuesta.setIntento(usuario.getIntentos());
			}
			else {
				usuario.setFechaBloqueo(LocalDateTime.now());
				repository.save(usuario);
				respuesta.setMensaje("Usuario bloqueado por 30 minutos");
				respuesta.setIntento(0);
			}
		}
		return respuesta;
	}
	
	private Respuesta saludar(Usuario usuario, Respuesta respuesta) {
		respuesta.setNombre(usuario.getNombreCompleto()+" "+usuario.getApellidosCompletos());
		LocalDateTime fecha = LocalDateTime.now();
		
		LocalDateTime actual = LocalDateTime.of(1, 1, 1, fecha.getHour(), fecha.getMinute(), fecha.getSecond());
		LocalDateTime dia = LocalDateTime.of(1, 1, 1, 4, 0, 0);
		LocalDateTime tarde = LocalDateTime.of(1, 1, 1, 12, 0, 0);
		LocalDateTime noche = LocalDateTime.of(1, 1, 1, 20, 0, 0);
		
		if(actual.isAfter(dia) && actual.isBefore(tarde))
			respuesta.setSaludo("unos muy buenos días");
		else if(actual.isAfter(tarde) && actual.isBefore(noche))
			respuesta.setSaludo("unas muy buenas tardes");
		else respuesta.setSaludo("unas muy buenas noches");
		return respuesta;
	}

	@Override
	public String registro(Usuario usuario) {
		Usuario registrado= repository.findByUsuarioOrEmail(usuario.getUsuario(), usuario.getEmail());
		if(registrado != null) {
			if(registrado.getUsuario().equals(usuario.getUsuario())) return "El usuario ya se encuentra registrado";
			else return "El email ya se encuentra registrado";
		} else {
			usuario.setFechaRegistro(LocalDateTime.now());
			usuario.setFechaBloqueo(null);
			usuario.setIntentos(3);
			repository.save(usuario);
			return null;
		}
	}

}
