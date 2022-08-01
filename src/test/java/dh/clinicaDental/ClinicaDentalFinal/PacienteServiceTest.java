package dh.clinicaDental.ClinicaDentalFinal;
import dh.clinicaDental.ClinicaDentalFinal.exceptions.ResourceNotFoundException;
import dh.clinicaDental.ClinicaDentalFinal.model.Domicilio;
import dh.clinicaDental.ClinicaDentalFinal.model.Paciente;
import dh.clinicaDental.ClinicaDentalFinal.service.PacienteService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("calle A", "20", "Yerba Buena", "Tucumán");
        Paciente p = pacienteService.registrarPaciente(new Paciente("Abi", "Orellana", "38258147", new Date(), domicilio));
        Domicilio domicilio1 = new Domicilio("calle b", "21", "YB", "Tuc");
        Paciente p1 = pacienteService.registrarPaciente(new Paciente("Jimena", "Ruiz G", "37502502", new Date(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("calle 3", "50", "YB", "Tuc");
        Paciente p = pacienteService.registrarPaciente(new Paciente("Sofia", "Heguy", "678456123", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(3);
        Assert.assertTrue(pacienteService.buscar(3).isEmpty());

    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }


}
