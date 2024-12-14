package com.turismo.gestion_destinos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "usuario_destinos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "destino_id")
    )
    private List<DestinoTuristico> destinosAsignados = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DestinoTuristico> getDestinosAsignados() {
        return destinosAsignados;
    }

    public void setDestinosAsignados(List<DestinoTuristico> destinosAsignados) {
        this.destinosAsignados = destinosAsignados;
    }
}
