package dh.clinicaDental.ClinicaDentalFinal;

import dh.clinicaDental.ClinicaDentalFinal.model.Domicilio;
import dh.clinicaDental.ClinicaDentalFinal.model.Odontologo;
import dh.clinicaDental.ClinicaDentalFinal.model.Paciente;
import dh.clinicaDental.ClinicaDentalFinal.model.Turno;
import dh.clinicaDental.ClinicaDentalFinal.service.OdontologoService;
import dh.clinicaDental.ClinicaDentalFinal.service.PacienteService;
import dh.clinicaDental.ClinicaDentalFinal.service.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Leloir", "645", "Yerba Buena", "Tucumán");
        Paciente p = pacienteService.registrarPaciente (new Paciente("Sofía", "Monasterio", "37905905", new Date(), domicilio));
        this.odontologoService.registrarOdontologo(new Odontologo("Lucas", "Monasterio", 321));
        turnoService.registrarTurno(new Turno(pacienteService.buscar(1).get(),odontologoService.buscar(1).get(),new Date()));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
