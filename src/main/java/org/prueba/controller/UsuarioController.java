package org.prueba.controller;

import org.prueba.entity.Respuesta;
import org.prueba.entity.Usuario;
import org.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/register")
	public ResponseEntity<Respuesta> registrase(@RequestBody Usuario usuario) {
		String r = service.registro(usuario);
		Respuesta respuesta = new Respuesta();
		if(r == null) {
			respuesta.setMensaje("Creado Exitosamente");
			respuesta.setStatus(201);
			return ResponseEntity.status(201).body(respuesta);
		} else {
			respuesta.setMensaje(r);
			respuesta.setStatus(409);
			return ResponseEntity.status(409).body(respuesta);
		}
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<Respuesta> inicioSesion(@RequestBody Usuario usuario) {
		Respuesta respuesta = service.inicioSesion(usuario);
		if(respuesta.getSaludo() != null) respuesta.setStatus(200);
		else if(respuesta.getMensaje().equals("Error de Usuario y/o Contraseña")) respuesta.setStatus(400);
		else respuesta.setStatus(403);
		return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
	}
}
