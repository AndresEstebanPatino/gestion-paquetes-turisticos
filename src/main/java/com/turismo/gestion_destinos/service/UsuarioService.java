package com.turismo.gestion_destinos.service;

import com.turismo.gestion_destinos.model.DestinoTuristico;
import com.turismo.gestion_destinos.model.Usuario;
import com.turismo.gestion_destinos.repository.DestinoTuristicoRepository;
import com.turismo.gestion_destinos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private DestinoTuristicoRepository destinoTuristicoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, DestinoTuristicoRepository destinoTuristicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.destinoTuristicoRepository = destinoTuristicoRepository;
    }

    public List<Usuario> obtenerTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario){

        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("usuario no encontrado"));
    }

    public Usuario editarUsuario(Long usuarioId, Usuario usuarioActualizado){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }

    public Usuario asignarDestino(Long usuarioId, Long destinoTuristicoId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        DestinoTuristico destinoTuristico = destinoTuristicoRepository.findById(destinoTuristicoId)
                .orElseThrow(()-> new RuntimeException());


        if (!usuario.getDestinosAsignados().contains(destinoTuristico)) {
            usuario.getDestinosAsignados().add(destinoTuristico);
            destinoTuristico.getUsuarios().add(usuario);
        }
        return usuarioRepository.save(usuario);

    }
}
