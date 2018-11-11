package com.contaazul.contants;

public interface Constants {

	public interface URI {
		String BASE = "/rest/bankslips";
		String PATH_VARIABLE_ID = BASE + "/{id}";
		String URI_NAO_SEI = "/rest/bankslips/{id}/payments";
	}

	public interface SWAGGER {
		String DOC_CREATE = "Esse método deve receber um novo boleto e inseri-lo em um banco de dados, "
				+ "com o status PENDING para ser consumido pela própria API. Todos os campos são obrigatórios.";
		String DOC_RETRIEVE_ALL = "Esse método da API deve retornar uma lista de boletos em formato JSON. "
				+ "Caso não tenha boletos cadastrados, retorna um array vazio";
		String DOC_CANCEL = "Esse método da API deve alterar o status do boleto para CANCELED.";
		String DOC_PAY = "Esse método da API deve alterar o status do boleto para PAID.";
		String DOC_RETRIVE_ONE = "Esse método da API deve retornar um boleto filtrado pelo id, caso o boleto estiver atrasado "
				+ "deve ser calculado o valor da multa.";
		String DOC_RETRIVE_ONDE_NOTES = "Regra para o cálculo da multa aplicada por dia para os boletos atrasados:\n"
				+ "\t * Até 10 dias: Multa de 0,5% (Juros Simples)\n \t * Acima de 10 dias: Multa de 1% (Juros Simples)";

	}

}
