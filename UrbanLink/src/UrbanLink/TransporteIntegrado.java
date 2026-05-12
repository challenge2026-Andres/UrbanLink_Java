package UrbanLink;
public class TransporteIntegrado extends TransportePublico {

    private String sistemaBilhetagem;
    private int totalViagensProcessadas;
    private boolean aceitaVoucher;

    public TransporteIntegrado(String id, String nome, String modalidade, double tarifaBase, double co2EvitadoPorUso, String sistemaBilhetagem) {
        super(id, nome, modalidade, tarifaBase, co2EvitadoPorUso);
        this.sistemaBilhetagem = sistemaBilhetagem;
        this.totalViagensProcessadas = 0;
        this.aceitaVoucher = true;
    }

    public String getSistemaBilhetagem() { return sistemaBilhetagem; }
    public void setSistemaBilhetagem(String sistemaBilhetagem) {
        this.sistemaBilhetagem = sistemaBilhetagem;
    }

    public int getTotalViagensProcessadas() { return totalViagensProcessadas; }
    public void setTotalViagensProcessadas(int totalViagensProcessadas) {
        this.totalViagensProcessadas = totalViagensProcessadas;
    }

    public boolean isAceitaVoucher() { return aceitaVoucher; }
    public void setAceitaVoucher(boolean aceitaVoucher) { this.aceitaVoucher = aceitaVoucher; }

    public void registrarViagem() {
        this.totalViagensProcessadas++;
    }

    
    // o mesmo que disse na classe TransportePublico, ficaria ate mais simples, por puxar as info da classe super
    // esse override exibe so as informacoes especificas e num pega as genericas de TransportePublico
    // (Deixei um comentario de como ficaria la em baixo)
    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Sistema    : " + sistemaBilhetagem);
        System.out.println("Viagens    : " + totalViagensProcessadas);
        System.out.println("Voucher    : " + (aceitaVoucher ? "Aceito" : "Não aceito"));
    }

    @Override
    public String toString() {
        return "TransporteIntegrado{nome='" + getNome() + "', sistema='" + sistemaBilhetagem + "'}";
    }
    
//    @Override
//    public String toString() {
//        return super.toString() + "\n"
//             + "Sistema    : " + this.sistemaBilhetagem + "\n"
//             + "Viagens    : " + this.totalViagensProcessadas + "\n"
//             + "Voucher    : " + (this.aceitaVoucher ? "Aceito" : "Não aceito");
//    }
}
