package cl.karubag.pesaje.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PesajeDTO {

    private Long id;

    @NotNull(message = "El retiroId es obligatorio")
    private Long retiroId;

    @NotNull(message = "El materialId es obligatorio")
    private Long materialId;

    @NotNull(message = "Los kilos son obligatorios")
    @Positive(message = "Los kilos deben ser mayor a 0")
    private Double kilos;

    private Double precioPorKilo;

    private Double totalCalculado;

    private String observacion;

    public PesajeDTO() {}

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
}
