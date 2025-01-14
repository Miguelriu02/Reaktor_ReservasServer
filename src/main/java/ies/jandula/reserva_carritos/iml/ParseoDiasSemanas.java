package ies.jandula.reserva_carritos.iml;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.jandula.reserva_carritos.exception.ReservaException;
import ies.jandula.reserva_carritos.interfaces.IParseoDiasSemana;
import ies.jandula.reserva_carritos.models.reservas_fijas.DiasSemana;
import ies.jandula.reserva_carritos.repository.DiasSemanaRepository;

@Service
public class ParseoDiasSemanas implements IParseoDiasSemana
{
	@Autowired
	private DiasSemanaRepository diasSemanaRepository;

	@Override
	public void parseaFichero(Scanner scanner) throws ReservaException
	{
		scanner.nextLine();

		while (scanner.hasNextLine())
		{
			String lineaDelFichero = scanner.nextLine();

			// Dividir la línea por comas
			String[] lineaDelFicheroTroceada = lineaDelFichero.split(",");

			DiasSemana diasSemana = new DiasSemana();
			diasSemana.setDiasDeLaSemana(lineaDelFicheroTroceada[0]);
			this.diasSemanaRepository.saveAndFlush(diasSemana);
		}
	}

}
