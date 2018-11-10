package com.contaazul.contants;

public interface Constants {

	public interface URI {
		String BASE = "/rest/bankslips";
	}

	public interface SWAGGER {
		String DOC_CREATE = "Esse método deve receber um novo boleto e inseri-lo em um banco de dados, "
				+ "com o status PENDING para ser consumido pela própria API. Todos os campos são obrigatórios.";
		String DOC_RETRIEVE_ALL = "Esse método da API deve retornar uma lista de boletos em formato JSON. "
				+ "Caso não tenha boletos cadastrados, retorna um array vazio";

	}

}
