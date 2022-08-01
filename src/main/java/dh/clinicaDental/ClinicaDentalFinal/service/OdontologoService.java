package dh.clinicaDental.ClinicaDentalFinal.service;

import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Odontologo;
import dh.clinicaDental.ClinicaDentalFinal.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;
    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoService.class));

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        logger.info("comienza metodo registrar odontologo");
        return odontologoRepository.save(odontologo);

    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        logger.info("comienza metodo eliminar odontologo");
        Optional<Odontologo> odontoloogoBuscado = buscar(id);
        if (odontoloogoBuscado.isPresent())
            odontologoRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("Odontologo con id: "+id+" no encontrado");
    }


    public Optional<Odontologo> buscar(Integer id) {
        logger.info("comienza metodo buscar odontologo");
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodos() {
        logger.info("comienza metodo listar todos los odontologos");
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        logger.info("comienza metodo actualizar un odontologo");
        return odontologoRepository.save(odontologo);
    }
}
