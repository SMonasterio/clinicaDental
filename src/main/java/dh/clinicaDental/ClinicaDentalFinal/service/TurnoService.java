package dh.clinicaDental.ClinicaDentalFinal.service;

import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Turno;
import dh.clinicaDental.ClinicaDentalFinal.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private static final Logger logger = Logger.getLogger(String.valueOf(TurnoService.class));

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrarTurno(Turno turno) {
        logger.info("comienza metodo registrar turno");
        return turnoRepository.save(turno);
    }

    public List<Turno> listar() {
        logger.info("comienza metodo listar turnos");
        return turnoRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        logger.info("comienza metodo eliminar turno");
     Optional<Turno> turnoBuscado = buscar(id);
     if (turnoBuscado.isPresent())
        turnoRepository.deleteById(id);
     else
         throw new ResourceNotFoundException("No existe el turno con el id: "+id);

    }

    public Turno actualizar(Turno turno) {
        logger.info("comienza metodo actualizar turno");
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscar(Integer id) {
        logger.info("comienza metodo buscar turno");
        return turnoRepository.findById(id);
    }

}
