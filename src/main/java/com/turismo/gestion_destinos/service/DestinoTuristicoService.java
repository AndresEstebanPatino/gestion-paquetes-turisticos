package com.turismo.gestion_destinos.service;

import com.turismo.gestion_destinos.model.DestinoTuristico;
import com.turismo.gestion_destinos.model.Usuario;
import com.turismo.gestion_destinos.repository.DestinoTuristicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinoTuristicoService {
    private final DestinoTuristicoRepository destinoTuristicoRepository;

    public DestinoTuristicoService(DestinoTuristicoRepository destinoTuristicoRepository) {
        this.destinoTuristicoRepository = destinoTuristicoRepository;
    }

    public List<DestinoTuristico> obtenerTodosDestinosTuristicos(){
        return destinoTuristicoRepository.findAll();
    }
    public DestinoTuristico crearDestinoTuristico(DestinoTuristico destinoTuristico){
        return destinoTuristicoRepository.save(destinoTuristico);
    }
    public DestinoTuristico obtenerDestinoturisticoPorId(Long id){
        return  destinoTuristicoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Destino no escontrado"));
    }
    public DestinoTuristico editarDestinoTuristico(Long destinoTuristicoId, DestinoTuristico destinoTuristicoActualizado) {
        DestinoTuristico destinoTuristico = destinoTuristicoRepository.findById(destinoTuristicoId)
                .orElseThrow(() -> new RuntimeException("Destino turístico no encontrado"));
        destinoTuristico.setNombre(destinoTuristicoActualizado.getNombre());
        destinoTuristico.setPrecio(destinoTuristicoActualizado.getPrecio());
        return destinoTuristicoRepository.save(destinoTuristico);

    }

    public void eliminarDestinoTuristico(Long destinoTuristicoId){
        DestinoTuristico destinoTuristico = destinoTuristicoRepository.findById(destinoTuristicoId)
                .orElseThrow(()-> new RuntimeException("no se encontró el destino"));

        destinoTuristicoRepository.delete(destinoTuristico);
    }
}
