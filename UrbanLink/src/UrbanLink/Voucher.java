package UrbanLink;
public class Voucher {

    public enum StatusVoucher {
        ATIVO, UTILIZADO, EXPIRADO, CANCELADO
    }

    private String codigo;
    private String usuarioId;
    private String transporteId;
    private double valorCredito;
    private double pontosUsados;
    private StatusVoucher status;
    private String dataGeracao;
    private boolean antifraude;

    public Voucher(String codigo, String usuarioId, String transporteId, double valorCredito, double pontosUsados, String dataGeracao) {
        this.codigo = codigo;
        this.usuarioId = usuarioId;
        this.transporteId = transporteId;
        this.valorCredito = valorCredito;
        this.pontosUsados = pontosUsados;
        this.dataGeracao = dataGeracao;
        this.status = StatusVoucher.ATIVO;
        this.antifraude = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTransporteId() {
        return transporteId;
    }

    public void setTransporteId(String transporteId) {
        this.transporteId = transporteId;
    }

    public double getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public double getPontosUsados() {
        return pontosUsados;
    }

    public void setPontosUsados(double pontosUsados) {
        this.pontosUsados = pontosUsados;
    }

    public StatusVoucher getStatus() {
        return status;
    }

    public void setStatus(StatusVoucher status) {
        this.status = status;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public boolean isAntifraude() {
        return antifraude;
    }

    public void setAntifraude(boolean antifraude) {
        this.antifraude = antifraude;
    }


    public boolean utilizarVoucher() {
        if (status == StatusVoucher.ATIVO && antifraude) {
            this.status = StatusVoucher.UTILIZADO;
            System.out.println("[✓] Voucher " + codigo + " utilizado com sucesso! Crédito: R$ "
                    + String.format("%.2f", valorCredito));
            return true;
        } else if (!antifraude) {
            System.out.println("[X] Voucher " + codigo + " BLOQUEADO por suspeita de fraude!");
            return false;
        } else {
            System.out.println("[!] Voucher " + codigo + " não está ativo. Status: " + status);
            return false;
        }
    }

    @Override
    public String toString() {
        return "==============================\n"
                + "      VOUCHER SOULUP        \n"
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