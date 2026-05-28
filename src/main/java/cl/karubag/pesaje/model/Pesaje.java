package cl.karubag.pesaje.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pesaje")
public class Pesaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "retiro_id", nullable = false)
    private Long retiroId;

    @Column(name = "material_id", nullable = false)
    private Long materialId;

    @Column(name = "kilos", nullable = false)
    private Double kilos;

    @Column(name = "precio_por_kilo")
    private Double precioPorKilo;

    @Column(name = "total_calculado")
    private Double totalCalculado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }

    public Pesaje() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRetiroId() { return retiroId; }
    public void setRetiroId(Long retiroId) { this.retiroId = retiroId; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Double getKilos() { return kilos; }
    public void setKilos(Double kilos) { this.kilos = kilos; }
    public Double getPrecioPorKilo() { return precioPorKilo; }
    public void setPrecioPorKilo(Double precioPorKilo) { this.precioPorKilo = precioPorKilo; }
    public Double getTotalCalculado() { return totalCalculado; }
    public void setTotalCalculado(Double totalCalculado) { this.totalCalculado = totalCalculado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
}
