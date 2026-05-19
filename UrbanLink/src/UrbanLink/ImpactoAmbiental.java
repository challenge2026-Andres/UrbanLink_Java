package UrbanLink;
public class ImpactoAmbiental {

    private String usuarioId;
    private double totalCo2Evitado;
    private int totalViagens;
    private double equivalenciaArvores;
    private double pontosInvestidos;

    public ImpactoAmbiental(String usuarioId) {
        this.usuarioId = usuarioId;
        this.totalCo2Evitado = 0.0;
        this.totalViagens = 0;
        this.equivalenciaArvores = 0.0;
        this.pontosInvestidos = 0.0;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getTotalCo2Evitado() {
        return totalCo2Evitado;
    }
    public void setTotalCo2Evitado(double totalCo2Evitado) {
        this.totalCo2Evitado = totalCo2Evitado;
    }

    public int getTotalViagens() {
        return totalViagens;
    }

    public void setTotalViagens(int totalViagens) {
        this.totalViagens = totalViagens;
    }

    public double getEquivalenciaArvores() {
        return equivalenciaArvores;
    }

    public void setEquivalenciaArvores(double equivalenciaArvores) {
        this.equivalenciaArvores = equivalenciaArvores;
    }

    public double getPontosInvestidos() {
        return pontosInvestidos;
    }
    public void setPontosInvestidos(double pontosInvestidos) {
        this.pontosInvestidos = pontosInvestidos;
    }


     // arvore  absorve 22 kg de CO2/ano.

    public void registrarViagem(double co2EvitadoViagem, double pontosUsados) {
        this.totalViagens++;
        this.totalCo2Evitado += co2EvitadoViagem;
        this.pontosInvestidos += pontosUsados;
        this.equivalenciaArvores = totalCo2Evitado / 22.0;
        System.out.println("[+] Viagem registrada! CO2 evitado: " + co2EvitadoViagem + " kg");
    }

    @Override
    public String toString() {
        String nivel;
        if (totalCo2Evitado >= 100) {
            nivel = "DIAMANTE";
        } else if (totalCo2Evitado >= 50) {
            nivel = "OURO";
        } else if (totalCo2Evitado >= 10) {
            nivel = "PRATA";
        } else {
            nivel = "BRONZE";
        }

        return "====================================\n"
                + "    RELATORIO DE IMPACTO AMBIENTAL  \n"
                + "====================================\n"
                + "Usuario          : " + this.usuarioId + "\n"
                + "Total de viagens : " + this.totalViagens + "\n"
                + "CO2 total evitado: " + String.format("%.3f", this.totalCo2Evitado) + " kg\n"
                + "Equivale a       : " + String.format("%.2f", this.equivalenciaArvores) + " arvores/ano\n"
                + "Pontos investidos: " + this.pontosInvestidos + "\n"
                + "Nivel            : " + nivel + "\n"
                + "====================================";
    }
}
