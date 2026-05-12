package UrbanLink;
public class TransportePublico {

    private String id;
    private String nome;
    private String modalidade;
    private double tarifaBase;
    private double co2EvitadoPorUso;
    private boolean integrado;

    public TransportePublico(String id, String nome, String modalidade, double tarifaBase, double co2EvitadoPorUso) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
        this.tarifaBase = tarifaBase;
        this.co2EvitadoPorUso = co2EvitadoPorUso;
        this.integrado = true;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public double getTarifaBase() { return tarifaBase; }
    public void setTarifaBase(double tarifaBase) { this.tarifaBase = tarifaBase; }

    public double getCo2EvitadoPorUso() { return co2EvitadoPorUso; }
    public void setCo2EvitadoPorUso(double co2EvitadoPorUso) {
        this.co2EvitadoPorUso = co2EvitadoPorUso;
    }

    public boolean isIntegrado() { return integrado; }
    public void setIntegrado(boolean integrado) { this.integrado = integrado; }

    public double calcularPontosNecessarios(double percentualCobertura) {
        if (percentualCobertura < 0 || percentualCobertura > 100) {
            System.out.println("[!] Percentual inválido. Use entre 0 e 100.");
            return -1;
        }
        double valorCoberto = tarifaBase * (percentualCobertura / 100.0);
        return valorCoberto * 10;
    }
    
    
    // e se mostrasse essas info no proprio metodo toString ao inves de ter 2 metodos fazendo algo parecido?
    // (Deixei um comentario de como ficaria la em baixo)
    public void exibirInfo() {
        System.out.println("=== TRANSPORTE PÚBLICO ===");
        System.out.println("ID         : " + id);
        System.out.println("Nome       : " + nome);
        System.out.println("Modalidade : " + modalidade);
        System.out.println("Tarifa     : R$ " + String.format("%.2f", tarifaBase));
        System.out.println("CO₂ evitado: " + co2EvitadoPorUso + " kg/uso");
        System.out.println("Integrado  : " + (integrado ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "TransportePublico{nome='" + nome + "', tarifa=R$" + tarifaBase + "}";
    }
    
    
//    @Override
//    public String toString() {
//        return "=== TRANSPORTE PÚBLICO ===\n"
//             + "ID         : " + this.id + "\n"
//             + "Nome       : " + this.nome + "\n"
//             + "Modalidade : " + this.modalidade + "\n"
//             + "Tarifa     : R$ " + String.format("%.2f", this.tarifaBase) + "\n"
//             + "CO₂ evitado: " + this.co2EvitadoPorUso + " kg/uso\n"
//             + "Integrado  : " + (this.integrado ? "Sim" : "Não");
//    }
    
    
    
    
}