public class UsuarioPremium extends Usuario {

    private double multiplicadorBonus;
    private int limiteResgatesMensais;
    private int resgatesRealizadosMes;

    public UsuarioPremium(String id, String nome, String email, String cpf,
                          double multiplicadorBonus, int limiteResgatesMensais) {
        super(id, nome, email, cpf); // chama construtor da superclasse
        this.multiplicadorBonus = multiplicadorBonus;
        this.limiteResgatesMensais = limiteResgatesMensais;
        this.resgatesRealizadosMes = 0;
    }

    public double getMultiplicadorBonus() { return multiplicadorBonus; }
    public void setMultiplicadorBonus(double multiplicadorBonus) {
        this.multiplicadorBonus = multiplicadorBonus;
    }

    public int getLimiteResgatesMensais() { return limiteResgatesMensais; }
    public void setLimiteResgatesMensais(int limiteResgatesMensais) {
        this.limiteResgatesMensais = limiteResgatesMensais;
    }

    public int getResgatesRealizadosMes() { return resgatesRealizadosMes; }
    public void setResgatesRealizadosMes(int resgatesRealizadosMes) {
        this.resgatesRealizadosMes = resgatesRealizadosMes;
    }

    public boolean podeResgatar() {
        return resgatesRealizadosMes < limiteResgatesMensais;
    }

    public void registrarResgate() {
        resgatesRealizadosMes++;
    }

    @Override
    public void exibirPerfil() {
        super.exibirPerfil(); // chama o método da superclasse
        System.out.println("Tipo   : PREMIUM");
        System.out.println("Bônus  : x" + multiplicadorBonus);
        System.out.println("Resgates no mês: " + resgatesRealizadosMes + "/" + limiteResgatesMensais);
    }

    @Override
    public String toString() {
        return "UsuarioPremium{nome='" + getNome() + "', bonus=x" + multiplicadorBonus + "}";
    }
}