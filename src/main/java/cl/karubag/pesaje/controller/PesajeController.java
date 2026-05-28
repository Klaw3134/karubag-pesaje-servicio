package cl.karubag.pesaje.controller;

import cl.karubag.pesaje.dto.PesajeDTO;
import cl.karubag.pesaje.service.PesajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pesajes")
public class PesajeController {

    private final PesajeService pesajeService;

    public PesajeController(PesajeService pesajeService) {
        this.pesajeService = pesajeService;
    }

    @GetMapping
    public ResponseEntity<List<PesajeDTO>> listarTodos() {
        return ResponseEntity.ok(pesajeService.listarTodos());
    }

    @GetMapping("/retiro/{retiroId}")
    public ResponseEntity<List<PesajeDTO>> listarPorRetiro(@PathVariable Long retiroId) {
        return ResponseEntity.ok(pesajeService.listarPorRetiro(retiroId));
    }

    @GetMapping("/material/{materialId}")
    public ResponseEntity<List<PesajeDTO>> listarPorMaterial(@PathVariable Long materialId) {
        return ResponseEntity.ok(pesajeService.listarPorMaterial(materialId));
    }

    @GetMapping("/retiro/{retiroId}/total-kilos")
    public ResponseEntity<Double> obtenerTotalKilosPorRetiro(@PathVariable Long retiroId) {
        return ResponseEntity.ok(pesajeService.obtenerTotalKilosPorRetiro(retiroId));
    }

    @GetMapping("/material/{materialId}/total-kilos")
    public ResponseEntity<Double> obtenerTotalKilosPorMaterial(@PathVariable Long materialId) {
        return ResponseEntity.ok(pesajeService.obtenerTotalKilosPorMaterial(materialId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesajeDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pesajeService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PesajeDTO> crear(@Valid @RequestBody PesajeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pesajeService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PesajeDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PesajeDTO dto) {
        return ResponseEntity.ok(pesajeService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pesajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}