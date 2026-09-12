package model;

public class Voucher {

    private String codigo;
    private String usuarioId;
    private String transporteId;
    private double valorCredito;
    private double pontosUsados;
    private String status;
    private String dataGeracao;
    private boolean antifraude;

    public Voucher(){}

    public Voucher(String codigo, String usuarioId, String transporteId, double valorCredito, double pontosUsados, String dataGeracao, String status) {
        this.codigo = codigo;
        this.usuarioId = usuarioId;
        this.transporteId = transporteId;
        this.valorCredito = valorCredito;
        this.pontosUsados = pontosUsados;
        this.dataGeracao = dataGeracao;
        this.status = status;
        this.antifraude = true;
    }

    // CONSTRUTOR CORRIGIDO: Agora os dados são salvos corretamente no objeto[cite: 10]
    public Voucher(String codigoVoucher, String usuarioId, String transporteId, double valorCredito, double pontosNecessarios, String dataAtual) {
        this.codigo = codigoVoucher;
        this.usuarioId = usuarioId;
        this.transporteId = transporteId;
        this.valorCredito = valorCredito;
        this.pontosUsados = pontosNecessarios;
        this.dataGeracao = dataAtual;
        this.status = "ATIVO";
        this.antifraude = true;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getTransporteId() { return transporteId; }
    public void setTransporteId(String transporteId) { this.transporteId = transporteId; }

    public double getValorCredito() { return valorCredito; }
    public void setValorCredito(double valorCredito) { this.valorCredito = valorCredito; }

    public double getPontosUsados() { return pontosUsados; }
    public void setPontosUsados(double pontosUsados) { this.pontosUsados = pontosUsados; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(String dataGeracao) { this.dataGeracao = dataGeracao; }

    public boolean isAntifraude() { return antifraude; }
    public boolean getAntiFraude() { return antifraude; }
    public void setAntifraude(boolean antifraude) { this.antifraude = antifraude; }

    @Override
    public String toString() {
        return "==============================\n"
                + "      VOUCHER URBANLINK        \n"
                + "==============================\n"
                + "Codigo  : " + this.codigo + "\n"
                + "Credito : R$ " + String.format("%.2f", this.valorCredito) + "\n"
                + "Pontos  : " + this.pontosUsados + "\n"
                + "Status  : " + this.status + "\n"
                + "Data    : " + this.dataGeracao + "\n"
                + "Fraude  : " + (this.antifraude ? "OK" : "SUSPEITO") + "\n"
                + "==============================";
    }
}