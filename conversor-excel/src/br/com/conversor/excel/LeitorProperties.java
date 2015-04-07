package br.com.conversor.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LeitorProperties {

	private Properties prop;

	private final String arquivo = "config.properties";

	private final String DIVISOR = ",";
	private final String KEY_SIGLA = "sigla";
	private final String KEY_CATEGORIA = "categoria";
	private final String KEY_LOCALIDADE = "localidade";
	private final String KEY_PAIS = "pais";
	private final String KEY_SISTEMA = "sistema";
	private final String KEY_CABECALHO = "cabecalho";

	private String[] sigla;
	private String[] categoria;
	private String[] localidade;
	private String[] pais;
	private String[] sistema;
	private String[] cabecalho;
	
	public LeitorProperties() throws IOException {
		FileInputStream file = new FileInputStream(arquivo);
		prop = new Properties();
		prop.load(file);
		sigla = prop.getProperty(KEY_SIGLA).split(DIVISOR);
		categoria = prop.getProperty(KEY_CATEGORIA).split(DIVISOR);
		localidade = prop.getProperty(KEY_LOCALIDADE).split(DIVISOR);
		pais = prop.getProperty(KEY_PAIS).split(DIVISOR);
		sistema = prop.getProperty(KEY_SISTEMA).split(DIVISOR);
		cabecalho = prop.getProperty(KEY_CABECALHO).split(DIVISOR);
	}

	public String[] getSigla() {
		return sigla;
	}

	public String[] getCategoria() {
		return categoria;
	}

	public String[] getLocalidade() {
		return localidade;
	}

	public String[] getPais() {
		return pais;
	}

	public String[] getSistema() {
		return sistema;
	}
	
	public String[] getCabecalho() {
		return cabecalho;
	}

}
