package org.prueba.service;

import org.prueba.entity.Respuesta;
import org.prueba.entity.Usuario;

public interface UsuarioService {

	Respuesta inicioSesion(Usuario usuario);
	
	String registro(Usuario usuario);
}
