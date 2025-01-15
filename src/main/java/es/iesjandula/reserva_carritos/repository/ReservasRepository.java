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
	
	@Query("(SELECT  new es.iesjandula.reserva_carritos.dto.ReservaDto("
			+"d.diasDeLaSemana, t.tramosHorarios ,null, null, null) "
			+"FROM DiasSemana d, TramosHorarios t) "
			+"UNION " 
			+"SELECT  new es.iesjandula.reserva_carritos.dto.ReservaDto("
			+"r.reservaId.diasDeLaSemana.diasDeLaSemana, r.reservaId.tramosHorarios.tramosHorarios, r.nAlumnos, p.email, CONCAT(p.nombre, ' ', p.apellidos)) "
			+ "FROM ReservaFijas r Join r.reservaId.profesor p ")
	List<ReservaDto>entrarTodasLasReservas();
	
	@Query("SELECT  new es.iesjandula.reserva_carritos.dto.ReservaDto("
			+ "r.reservaId.diasDeLaSemana.diasDeLaSemana, r.reservaId.tramosHorarios.tramosHorarios, r.nAlumnos, p.email, CONCAT(p.nombre, ' ', p.apellidos)) "
			+ "FROM ReservaFijas r Join r.reservaId.profesor p ")
	List<ReservaDto> obtenerReservasFijas();


}
