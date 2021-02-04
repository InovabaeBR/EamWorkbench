package br.com.gesc.model;

import java.util.Date;

/**
 * @author Evandro Costa Neves
 */
public class ApontamentoHoraOrdem {
    /*
    Centro
     */
    private String centroLocalizacao;
    /*
    Centro de trabalho
     */
    private String centroTrabalho;

    //TODO Verificar se vai precisar mesmo
    /*
    Numerador da confirmação
     */

    /*
    Confirmação parcial/final
     */
    private Boolean aponteFinal;

    //TODO Verificar se vai precisar mesmo
    /*
    confirmação da operação
     */

    /*
    Fim real: executar (data)
     */
    private Date dataFim;

    /*
    Início real: execução (data)
     */
    private Date dataInicio;

    /*
    Data de criação do registro
     */
    private Date dataRegistro;

    /*
    Causa do desvio
     */
    private String codigoDesvio;

    /*
    Código de uma posição
     */
    private Boolean estorno;

    /*
    Fim real: execução (hora)
     */
    private Date horaFimReal;

    /*
    Início real: executar/preparar (hora)
     */
    private Date horaInicioReal;

    /*
    Hora de entrada
     */
    private Date horaEntrada;

    /*
    Trabalho real
     */
    private Float trabalhoReal;

    /*
    Nº pessoal
     */
    private int numeroPessoa;

    /*
    Nº operação
     */
    private String operacao;

    /*
    Nº ordem
     */
    private String ordem;

    /*
    Suboperação
     */
    private String subOperacao;

    /*
    Unidade de trabalho
     */
    private String unidadeTrabalho;

    /*
    Nome do responsável que adicionou o objeto
     */
    private String usuarioRegistro;

    public String getCentroLocalizacao() {
        return centroLocalizacao;
    }

    public void setCentroLocalizacao(String centroLocalizacao) {
        this.centroLocalizacao = centroLocalizacao;
    }

    public String getCentroTrabalho() {
        return centroTrabalho;
    }

    public void setCentroTrabalho(String centroTrabalho) {
        this.centroTrabalho = centroTrabalho;
    }

    public Boolean getAponteFinal() {
        return aponteFinal;
    }

    public void setAponteFinal(Boolean aponteFinal) {
        this.aponteFinal = aponteFinal;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getCodigoDesvio() {
        return codigoDesvio;
    }

    public void setCodigoDesvio(String codigoDesvio) {
        this.codigoDesvio = codigoDesvio;
    }

    public Boolean getEstorno() {
        return estorno;
    }

    public void setEstorno(Boolean estorno) {
        this.estorno = estorno;
    }

    public Date getHoraFimReal() {
        return horaFimReal;
    }

    public void setHoraFimReal(Date horaFimReal) {
        this.horaFimReal = horaFimReal;
    }

    public Date getHoraInicioReal() {
        return horaInicioReal;
    }

    public void setHoraInicioReal(Date horaInicioReal) {
        this.horaInicioReal = horaInicioReal;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Float getTrabalhoReal() {
        return trabalhoReal;
    }

    public void setTrabalhoReal(Float trabalhoReal) {
        this.trabalhoReal = trabalhoReal;
    }

    public int getNumeroPessoa() {
        return numeroPessoa;
    }

    public void setNumeroPessoa(int numeroPessoa) {
        this.numeroPessoa = numeroPessoa;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getSubOperacao() {
        return subOperacao;
    }

    public void setSubOperacao(String subOperacao) {
        this.subOperacao = subOperacao;
    }

    public String getUnidadeTrabalho() {
        return unidadeTrabalho;
    }

    public void setUnidadeTrabalho(String unidadeTrabalho) {
        this.unidadeTrabalho = unidadeTrabalho;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
}
