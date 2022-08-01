package dh.clinicaDental.ClinicaDentalFinal.repository;


import dh.clinicaDental.ClinicaDentalFinal.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
