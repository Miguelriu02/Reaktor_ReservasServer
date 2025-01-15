package es.iesjandula.reserva_carritos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto
{
	private String diaSemana;

	private String tramoHorario;

	private int nAlumnos;

	private String email;

	private String nombreYapellidos;

	public ReservaDto(String diaSemana, String tramoHorario) {
		super();
		this.diaSemana = diaSemana;
		this.tramoHorario = tramoHorario;

	}	
}
