package cl.karubag.pesaje.service;

import cl.karubag.pesaje.dto.PesajeDTO;
import cl.karubag.pesaje.model.Pesaje;
import cl.karubag.pesaje.repository.PesajeRepository;
import cl.karubag.pesaje.client.MaterialClient;
import cl.karubag.pesaje.exception.ResourceNotFoundException;
import cl.karubag.pesaje.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PesajeService {

    private final PesajeRepository pesajeRepository;
    private final MaterialClient materialClient;

    public PesajeService(PesajeRepository pesajeRepository,
                     MaterialClient materialClient) {
        this.pesajeRepository = pesajeRepository;
        this.materialClient = materialClient;
    }

    public List<PesajeDTO> listarTodos() {
        return pesajeRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PesajeDTO> listarPorRetiro(Long retiroId) {
        return pesajeRepository.findByRetiroId(retiroId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PesajeDTO> listarPorMaterial(Long materialId) {
        return pesajeRepository.findByMaterialId(materialId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PesajeDTO obtenerPorId(Long id) {
        Pesaje pesaje = pesajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pesaje no encontrado con id: " + id));
        return toDTO(pesaje);
    }

    public Double obtenerTotalKilosPorRetiro(Long retiroId) {
        Double total = pesajeRepository.sumKilosByRetiroId(retiroId);
        return total != null ? total : 0.0;
    }

    public Double obtenerTotalKilosPorMaterial(Long materialId) {
        Double total = pesajeRepository.sumKilosByMaterialId(materialId);
        return total != null ? total : 0.0;
    }

    public PesajeDTO crear(PesajeDTO dto) {
        if (!materialClient.existeMaterial(dto.getMaterialId())) {
        throw new ResourceNotFoundException("Material no encontrado con id: " + dto.getMaterialId());
        }
        Pesaje pesaje = toEntity(dto);
        if (pesaje.getPrecioPorKilo() == null) {
        Double precio = materialClient.obtenerPrecioPorKilo(dto.getMaterialId());
        pesaje.setPrecioPorKilo(precio);
        }
        if (pesaje.getPrecioPorKilo() != null && pesaje.getKilos() != null) {
        pesaje.setTotalCalculado(pesaje.getKilos() * pesaje.getPrecioPorKilo());
        }
        return toDTO(pesajeRepository.save(pesaje));    
    }

    public PesajeDTO actualizar(Long id, PesajeDTO dto) {
        Pesaje pesaje = pesajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pesaje no encontrado con id: " + id));
        pesaje.setRetiroId(dto.getRetiroId());
        pesaje.setMaterialId(dto.getMaterialId());
        pesaje.setKilos(dto.getKilos());
        pesaje.setPrecioPorKilo(dto.getPrecioPorKilo());
        if (dto.getPrecioPorKilo() != null && dto.getKilos() != null) {
            pesaje.setTotalCalculado(dto.getKilos() * dto.getPrecioPorKilo());
        }
        pesaje.setObservacion(dto.getObservacion());
        return toDTO(pesajeRepository.save(pesaje));
    }

    public void eliminar(Long id) {
        pesajeRepository.deleteById(id);
    }

    private PesajeDTO toDTO(Pesaje p) {
        PesajeDTO dto = new PesajeDTO();
        dto.setId(p.getId());
        dto.setRetiroId(p.getRetiroId());
        dto.setMaterialId(p.getMaterialId());
        dto.setKilos(p.getKilos());
        dto.setPrecioPorKilo(p.getPrecioPorKilo());
        dto.setTotalCalculado(p.getTotalCalculado());
        dto.setObservacion(p.getObservacion());
        return dto;
    }

    private Pesaje toEntity(PesajeDTO dto) {
        Pesaje p = new Pesaje();
        p.setRetiroId(dto.getRetiroId());
        p.setMaterialId(dto.getMaterialId());
        p.setKilos(dto.getKilos());
        p.setPrecioPorKilo(dto.getPrecioPorKilo());
        p.setTotalCalculado(dto.getTotalCalculado());
        p.setObservacion(dto.getObservacion());
        return p;
    }
}
