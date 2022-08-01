package dh.clinicaDental.ClinicaDentalFinal;

import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Domicilio;
import dh.clinicaDental.ClinicaDentalFinal.model.Odontologo;
import dh.clinicaDental.ClinicaDentalFinal.model.Paciente;
import dh.clinicaDental.ClinicaDentalFinal.model.Turno;
import dh.clinicaDental.ClinicaDentalFinal.service.OdontologoService;
import dh.clinicaDental.ClinicaDentalFinal.service.PacienteService;
import dh.clinicaDental.ClinicaDentalFinal.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("calle 4", "789", "YB", "TUC");
        Paciente p = pacienteService.registrarPaciente(new Paciente("Tati", "Pastor", "0000000", new Date(), domicilio));
        this.odontologoService.registrarOdontologo(new Odontologo("Nicki", "Nicole", 321));
    }
    @Test
    public void altaTurnoTest(){
        this.cargarDataSet();
        turnoService.registrarTurno(new Turno(pacienteService.buscar(1).get(),odontologoService.buscar(1).get(),new Date()));
        Assert.assertNotNull(turnoService.buscar(1));
    }
    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1));
    }
    @Test
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminar(1);
        Assert.assertFalse(turnoService.buscar(1).isPresent());
    }
}
