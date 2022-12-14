package dh.clinicaDental.ClinicaDentalFinal.controller;

import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Turno;
import dh.clinicaDental.ClinicaDentalFinal.service.OdontologoService;
import dh.clinicaDental.ClinicaDentalFinal.service.PacienteService;
import dh.clinicaDental.ClinicaDentalFinal.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    //agregar excepción
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        if (pacienteService.buscar(turno.getPaciente().getId()).isPresent() && odontologoService.buscar(turno.getOdontologo().getId()).isPresent())
            response = ResponseEntity.ok(turnoService.registrarTurno(turno));

        else
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
       turnoService.eliminar(id);
       return ResponseEntity.ok("Se eliminó el turno correctamente");
    }


    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }


}
