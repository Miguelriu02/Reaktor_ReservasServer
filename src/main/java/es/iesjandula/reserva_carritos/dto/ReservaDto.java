package es.iesjandula.reserva_carritos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReservaDto
{
	private String diaSemana;

	private String tramoHorario;

	private int nAlumnos;

	private String email;

	private String nombreYapellidos;
	
	private String valornulo1;
	private String valornulo2;
	private String valornulo3;


	public ReservaDto(String diaSemana, String tramoHorario, int nAlumnos, String email, String nombreYapellidos) {
		super();
		this.diaSemana = diaSemana;
		this.tramoHorario = tramoHorario;
		this.nAlumnos = nAlumnos;
		this.email = email;
		this.nombreYapellidos = nombreYapellidos;
	}


	public ReservaDto(String diaSemana, String tramoHorario) {
		super();
		this.diaSemana = diaSemana;
		this.tramoHorario = tramoHorario;

	}

 
	
	
}
