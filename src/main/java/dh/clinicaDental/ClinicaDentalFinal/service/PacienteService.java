package dh.clinicaDental.ClinicaDentalFinal.service;

import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Paciente;
import dh.clinicaDental.ClinicaDentalFinal.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoService.class));


    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) {
        logger.info("comienza metodo registrar paciente");
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscar(Integer id) {
        logger.info("comienza metodo buscar paciente");
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() {
        logger.info("comienza metodo buscar todos los pacientes");
        return pacienteRepository.findAll();
    }


    public void eliminar(Integer id) throws ResourceNotFoundException {
        logger.info("comienza metodo eliminar paciente");
        Optional<Paciente>pacienteBuscado=buscar(id);
        if (pacienteBuscado.isPresent())
            pacienteRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("Paciente con id: "+id+" no encontrado");
    }


    public Paciente actualizar(Paciente paciente) {
        logger.info("comienza metodo actualizar paciente");
        return pacienteRepository.save(paciente);
    }
}
