package controller;

import java.util.ArrayList;

import model.bo.CarroBO;
import model.bo.MontadoraBO;
import model.vo.Carro;
import model.vo.Montadora;

public class CarroController {

	public ArrayList<Carro> consultarCarros(String nome, Montadora montadoraSelecionada){
		CarroBO bo = new CarroBO();
		return bo.consultarCarros(nome, montadoraSelecionada);
	}
	
	public ArrayList<Montadora> consultarMontadoras() {
		MontadoraBO bo = new MontadoraBO();
		return bo.consultarMontadoras();
	}
}
