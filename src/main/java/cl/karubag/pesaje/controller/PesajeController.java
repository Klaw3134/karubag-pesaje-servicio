package cl.karubag.pesaje.controller;

import cl.karubag.pesaje.dto.PesajeDTO;
import cl.karubag.pesaje.service.PesajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Pesajes", description = "Gestion de pesajes de materiales reciclados Karübag")
@RestController
@RequestMapping("/api/pesajes")
public class PesajeController {

    private final PesajeService pesajeService;

    public PesajeController(PesajeService pesajeService) {
        this.pesajeService = pesajeService;
    }

    @Operation(summary = "Listar todos los pesajes", description = "Retorna la lista completa de pesajes")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PesajeDTO>> listarTodos() {
        return ResponseEntity.ok(pesajeService.listarTodos());
    }

    @Operation(summary = "Listar por retiro", description = "Retorna pesajes de un retiro especifico")
    @ApiResponse(responseCode = "200", description = "Lista de pesajes del retiro")
    @GetMapping("/retiro/{retiroId}")
    public ResponseEntity<List<PesajeDTO>> listarPorRetiro(@PathVariable Long retiroId) {
        return ResponseEntity.ok(pesajeService.listarPorRetiro(retiroId));
    }

    @Operation(summary = "Listar por material", description = "Retorna pesajes de un material especifico")
    @ApiResponse(responseCode = "200", description = "Lista de pesajes por material")
    @GetMapping("/material/{materialId}")
    public ResponseEntity<List<PesajeDTO>> listarPorMaterial(@PathVariable Long materialId) {
        return ResponseEntity.ok(pesajeService.listarPorMaterial(materialId));
    }

    @Operation(summary = "Total kilos por retiro", description = "Retorna la suma total de kilos de un retiro")
    @ApiResponse(responseCode = "200", description = "Total de kilos calculado")
    @GetMapping("/retiro/{retiroId}/total-kilos")
    public ResponseEntity<Double> obtenerTotalKilosPorRetiro(@PathVariable Long retiroId) {
        return ResponseEntity.ok(pesajeService.obtenerTotalKilosPorRetiro(retiroId));
    }

    @Operation(summary = "Total kilos por material", description = "Retorna la suma total de kilos de un material")
    @ApiResponse(responseCode = "200", description = "Total de kilos por material")
    @GetMapping("/material/{materialId}/total-kilos")
    public ResponseEntity<Double> obtenerTotalKilosPorMaterial(@PathVariable Long materialId) {
        return ResponseEntity.ok(pesajeService.obtenerTotalKilosPorMaterial(materialId));
    }

    @Operation(summary = "Obtener pesaje por ID", description = "Busca un pesaje por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pesaje encontrado"),
        @ApiResponse(responseCode = "404", description = "Pesaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PesajeDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pesajeService.obtenerPorId(id));
    }

    @Operation(summary = "Crear pesaje", description = "Registra un pesaje obteniendo precio automaticamente via WebClient")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pesaje creado con precio y total calculados automaticamente",
            content = @Content(schema = @Schema(implementation = PesajeDTO.class),
            examples = @ExampleObject(value = "{\"retiroId\": 1, \"materialId\": 1, \"kilos\": 3.5, \"observacion\": \"Carton reciclado\"}"))),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    @PostMapping
    public ResponseEntity<PesajeDTO> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Solo ingresa retiroId, materialId y kilos. El precio se obtiene automaticamente.",
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\"retiroId\": 1, \"materialId\": 1, \"kilos\": 3.5, \"observacion\": \"Carton reciclado\"}")))
        @Valid @RequestBody PesajeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pesajeService.crear(dto));
    }

    @Operation(summary = "Actualizar pesaje", description = "Actualiza los datos de un pesaje")
    @ApiResponse(responseCode = "200", description = "Pesaje actualizado exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<PesajeDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PesajeDTO dto) {
        return ResponseEntity.ok(pesajeService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar pesaje", description = "Elimina un pesaje por su ID")
    @ApiResponse(responseCode = "204", description = "Pesaje eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pesajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
