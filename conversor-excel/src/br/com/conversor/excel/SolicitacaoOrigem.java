package br.com.conversor.excel;

import java.util.Date;

public class SolicitacaoOrigem {
	private String typeSymbol;
	private String numeroChamado;
	private String status;
	private String slaConsumido;
	private String numeroProblema;
	private String ticketExterno;
	private String slaViolado;
	private Date dataAbertura;
	private Date dataSolucao;
	private String prioridade;
	private Date dataFechamento;
	private String sumario;
	private String descricao;
	private String grupo;
	private String categoriaChamado;
	private String slaCategoria;
	private String responsavel;
	private String relatadoPor;
	private String usuarioFinalAfetado;
	private String localidade;
	private String gestor;
	private String subGestor;
	private String origem;

	public String getTypeSymbol() {
		return typeSymbol;
	}

	public void setTypeSymbol(String typeSymbol) {
		this.typeSymbol = typeSymbol;
	}

	public String getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(String numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSlaConsumido() {
		return slaConsumido;
	}

	public void setSlaConsumido(String slaConsumido) {
		this.slaConsumido = slaConsumido;
	}

	public String getNumeroProblema() {
		return numeroProblema;
	}

	public void setNumeroProblema(String numeroProblema) {
		this.numeroProblema = numeroProblema;
	}

	public String getTicketExterno() {
		return ticketExterno;
	}

	public void setTicketExterno(String ticketExterno) {
		this.ticketExterno = ticketExterno;
	}

	public String getSlaViolado() {
		return slaViolado;
	}

	public void setSlaViolado(String slaViolado) {
		this.slaViolado = slaViolado;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(Date dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCategoriaChamado() {
		return categoriaChamado;
	}

	public void setCategoriaChamado(String categoriaChamado) {
		this.categoriaChamado = categoriaChamado;
	}

	public String getSlaCategoria() {
		return slaCategoria;
	}

	public void setSlaCategoria(String slaCategoria) {
		this.slaCategoria = slaCategoria;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getRelatadoPor() {
		return relatadoPor;
	}

	public void setRelatadoPor(String relatadoPor) {
		this.relatadoPor = relatadoPor;
	}

	public String getUsuarioFinalAfetado() {
		return usuarioFinalAfetado;
	}

	public void setUsuarioFinalAfetado(String usuarioFinalAfetado) {
		this.usuarioFinalAfetado = usuarioFinalAfetado;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getSubGestor() {
		return subGestor;
	}

	public void setSubGestor(String subGestor) {
		this.subGestor = subGestor;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	

	public void mostrar() {
		System.out.println("typeSymbol: " + typeSymbol);
		System.out.println("numeroChamado: " + numeroChamado);
		System.out.println("status: " + status);
		System.out.println("slaConsumido: " + slaConsumido);
		System.out.println("numeroProblema: " + numeroProblema);
		System.out.println("ticketExterno: " + ticketExterno);
		System.out.println("slaViolado: " + slaViolado);
		System.out.println("dataAbertura: " + dataAbertura);
		System.out.println("dataSolucao: " + dataSolucao);
		System.out.println("prioridade: " + prioridade);
		System.out.println("dataFechamento: " + dataFechamento);
		System.out.println("sumario: " + sumario);
		System.out.println("descricao: " + descricao);
		System.out.println("grupo: " + grupo);
		System.out.println("categoriaChamado: " + categoriaChamado);
		System.out.println("slaCategoria: " + slaCategoria);
		System.out.println("responsavel: " + responsavel);
		System.out.println("relatadoPor: " + relatadoPor);
		System.out.println("usuarioFinalAfetado: " + usuarioFinalAfetado);
		System.out.println("localidade: " + localidade);
		System.out.println("gestor: " + gestor);
		System.out.println("subGestor: " + subGestor);
		System.out.println("origem: " + origem);
		System.out.println("---------------------------------------------");
	}

}
