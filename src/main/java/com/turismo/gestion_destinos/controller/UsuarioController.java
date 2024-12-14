package com.turismo.gestion_destinos.controller;

import com.turismo.gestion_destinos.model.Usuario;
import com.turismo.gestion_destinos.service.FacturaService;
import com.turismo.gestion_destinos.service.UsuarioService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final FacturaService facturaService;

    public UsuarioController(UsuarioService usuarioService, FacturaService facturaService) {
        this.usuarioService = usuarioService;
        this.facturaService = facturaService;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerTodosUsuarios(){
        return usuarioService.obtenerTodosUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id){
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado){
        return usuarioService.editarUsuario(id, usuarioActualizado);
    }

    @PutMapping("/{usuarioId}/destinos/{destinoId}")
    public Usuario asignarDestino(@PathVariable Long usuarioId, @PathVariable Long destinoId){
        return usuarioService.asignarDestino(usuarioId, destinoId);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/{usuarioId}/factura")
    public ResponseEntity<InputStreamResource> generarFactura(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);

        facturaService.generarFactura(usuario);

        try{
            String archivo = "factura_" + usuario.getId() + ".pdf";
            File file = new File(archivo);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + archivo)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
