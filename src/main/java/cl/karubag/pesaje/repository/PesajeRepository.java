package cl.karubag.pesaje.repository;

import cl.karubag.pesaje.model.Pesaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PesajeRepository extends JpaRepository<Pesaje, Long> {

    List<Pesaje> findByRetiroId(Long retiroId);

    List<Pesaje> findByMaterialId(Long materialId);

    @Query("SELECT SUM(p.kilos) FROM Pesaje p WHERE p.retiroId = :retiroId")
    Double sumKilosByRetiroId(Long retiroId);

    @Query("SELECT SUM(p.kilos) FROM Pesaje p WHERE p.materialId = :materialId")
    Double sumKilosByMaterialId(Long materialId);
}
