package model.bo;

import java.util.ArrayList;

import model.dao.CarroDAO;
import model.vo.Carro;
import model.vo.Montadora;

public class CarroBO {

	CarroDAO dao = new CarroDAO();
	
	public ArrayList<Carro> consultarCarros(String nome, Montadora montadoraSelecionada) {
		// TODO implementar as regras do item 1.c (c.1, c.2 e c.3) da avaliação

		ArrayList<Carro> carros = new ArrayList<Carro>();

		if (nome.equals("") && montadoraSelecionada == null) {
			carros = dao.listarTodos();
		} else if (!nome.equals("") && !(montadoraSelecionada == null)) {
			carros = dao.listarPorPlacaEMontadora(nome, montadoraSelecionada);
		} else if (!nome.equals("") && montadoraSelecionada == null) {
			carros = dao.listarPorPlaca(nome);
		} else if (nome.equals("") && !(montadoraSelecionada == null)) {
			carros = dao.listarPorMontadora(montadoraSelecionada);
		}
		return carros;
	}
}
