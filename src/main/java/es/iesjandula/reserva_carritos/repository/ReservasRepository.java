package es.iesjandula.reserva_carritos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iesjandula.reserva_carritos.dto.ReservaDto;
import es.iesjandula.reserva_carritos.models.reservas_fijas.RecursosPrevios;
import es.iesjandula.reserva_carritos.models.reservas_fijas.ReservaFijas;
import es.iesjandula.reserva_carritos.models.reservas_fijas.ReservasFijasId;

public interface ReservasRepository extends JpaRepository<ReservaFijas, ReservasFijasId>
{

//	Consulta que recupera la información sobre las reservas que están asociadas a 
//	un email, una aulaYCarritos, un diasDeLaSemana y un tramosHorarios
	@Query("SELECT r FROM ReservaFijas r WHERE r.reservaId.profesor.email = :email AND "
			+ "r.reservaId.aulaYCarritos.aulaYCarritos = :aulaYCarritos AND "
			+ "r.reservaId.diasDeLaSemana.id = :diasDeLaSemana AND "
			+ "r.reservaId.tramosHorarios.id = :tramosHorarios")
	Optional<ReservaFijas> encontrarReserva(@Param("email") String email, @Param("aulaYCarritos") String aulaYCarritos,
			@Param("diasDeLaSemana") Long diasDeLaSemana, @Param("tramosHorarios") Long tramosHorarios);

//	Consulta que recupera la información sobre las reservas que están asociadas a un recurso específico..
	@Query("SELECT new es.iesjandula.reserva_carritos.dto.ReservaDto("
			+ "r.reservaId.diasDeLaSemana.diasDeLaSemana, r.reservaId.tramosHorarios.tramosHorarios, r.nAlumnos, p.email, CONCAT(p.nombre, ' ', p.apellidos)) "
			+ "FROM ReservaFijas r Join r.reservaId.profesor p " + "WHERE r.reservaId.aulaYCarritos = :recurso")
	List<ReservaDto> encontrarReservaPorRecurso(@Param("recurso") RecursosPrevios recurso);

	@Query("SELECT new es.iesjandula.reserva_carritos.dto.ReservaDto("
            + "d.diasDeLaSemana, t.tramosHorarios) "
            + "FROM DiasSemana d, TramosHorarios t "
            + "ORDER BY CASE t.tramosHorarios "
            + "WHEN '8:00/9:00' THEN 1 "
            + "WHEN '9:00/10:00' THEN 2 "
            + "WHEN '10:00/11:00' THEN 3 "
            + "WHEN '11:30/12:30' THEN 4 "
            + "WHEN '12:30/13:30' THEN 5 "
            + "WHEN '13:30/14:30' THEN 6 "
            + "END, "
            + "CASE d.diasDeLaSemana "
            + "WHEN 'Lunes' THEN 1 "
            + "WHEN 'Martes' THEN 2 "
            + "WHEN 'Miércoles' THEN 3 "
            + "WHEN 'Jueves' THEN 4 "
            + "WHEN 'Viernes' THEN 5 "
            + "END")
List<ReservaDto> obtenerCombinacionesDiasYTramos();



	@Query("SELECT new es.iesjandula.reserva_carritos.dto.ReservaDto(" + "r.reservaId.diasDeLaSemana.diasDeLaSemana, "
			+ "r.reservaId.tramosHorarios.tramosHorarios, "
			+ "r.nAlumnos, p.email, CONCAT(p.nombre, ' ', p.apellidos)) "
			+ "FROM ReservaFijas r JOIN r.reservaId.profesor p")
	List<ReservaDto> obtenerReservasFijas();

}
