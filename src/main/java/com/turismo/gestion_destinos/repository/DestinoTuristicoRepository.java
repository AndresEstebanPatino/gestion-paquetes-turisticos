package com.turismo.gestion_destinos.repository;

import com.turismo.gestion_destinos.model.DestinoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoTuristicoRepository extends JpaRepository<DestinoTuristico, Long> {
}
