package UrbanLink;
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String cpf;
    private double saldoPontos;

    public Usuario(String id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.saldoPontos = 0.0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

    public void setSaldoPontos(double saldoPontos) {
        this.saldoPontos = saldoPontos;
    }

    public void adicionarPontos(double pontos) {
        if (pontos > 0) {
            this.saldoPontos += pontos;
            System.out.println("+ " + nome + " recebeu " + pontos + " pontos. Saldo atual: " + saldoPontos);
        } else {
            System.out.println("! Valor de pontos inválido para adicionar.");
        }
    }

    public boolean reduzirPontos(double pontos) {
        if (pontos <= 0) {
            System.out.println("[!] Valor de pontos inválido para deduzir.");
            return false;
        }
        if (this.saldoPontos >= pontos) {
            this.saldoPontos -= pontos;
            return true;
        } else {
            System.out.println("[!] Saldo insuficiente. Saldo: " + saldoPontos + " | Necessário: " + pontos);
            return false;
        }
    }

    @Override
    public String toString() {
        return "=== PERFIL DO USUÁRIO ===\n"
                + "ID     : " + this.id + "\n"
                + "Nome   : " + this.nome + "\n"
                + "Email  : " + this.email + "\n"
                + "CPF    : " + this.cpf + "\n"
                + "Pontos : " + this.saldoPontos;
    }
}