package com.turismo.gestion_destinos.controller;

import com.turismo.gestion_destinos.model.DestinoTuristico;
import com.turismo.gestion_destinos.service.DestinoTuristicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinosController {

    private final DestinoTuristicoService destinoTuristicoService;

    public DestinosController(DestinoTuristicoService destinoTuristicoService) {
        this.destinoTuristicoService = destinoTuristicoService;
    }

    @PostMapping
    public DestinoTuristico crearDestino(@RequestBody DestinoTuristico destinoTuristico){
        return destinoTuristicoService.crearDestinoTuristico(destinoTuristico);
    }

    @GetMapping
    public List<DestinoTuristico> obeteberTodosDestinos(){
        return destinoTuristicoService.obtenerTodosDestinosTuristicos();
    }

    @GetMapping("/{id}")
    public DestinoTuristico obetenerDestinoPorId(@PathVariable Long id){
        return destinoTuristicoService.obtenerDestinoturisticoPorId(id);
    }

    @PutMapping("/{id}")
    public DestinoTuristico editarDestino(@PathVariable Long id, @RequestBody DestinoTuristico destinoActualizado){
        return destinoTuristicoService.editarDestinoTuristico(id, destinoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarDestino(@PathVariable Long id){
        destinoTuristicoService.eliminarDestinoTuristico(id);
    }
}
