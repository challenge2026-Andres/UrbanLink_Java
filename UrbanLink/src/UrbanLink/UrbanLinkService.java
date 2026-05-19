package UrbanLink;
public class UrbanLinkService {

    // Taxa base: 10 pontos = R$ 1,00
    private static final double TAXA_CONVERSAO = 10.0;

    private Voucher[] vouchersGerados;
    private ImpactoAmbiental[] impactosUsuarios;
    private int totalVouchers;
    private int totalImpactos;
    private int contadorCodigoVoucher;

    public UrbanLinkService(int capacidadeMaxima) {
        this.vouchersGerados = new Voucher[capacidadeMaxima];
        this.impactosUsuarios = new ImpactoAmbiental[capacidadeMaxima];
        this.totalVouchers = 0;
        this.totalImpactos = 0;
        this.contadorCodigoVoucher = 1000;
    }

    public int getTotalVouchers() {
        return totalVouchers;
    }
    public int getTotalImpactos() {
        return totalImpactos;
    }

    public Voucher converterPontosEmVoucher(Usuario usuario, TransportePublico transporte, double percentualCobertura, String dataAtual) {
        System.out.println("\n--- INICIANDO CONVERSÃO DE PONTOS ---");

        if (percentualCobertura <= 0 || percentualCobertura > 100) {
            System.out.println("! Percentual inválido: " + percentualCobertura);
            return null;
        }

        if (!transporte.isIntegrado()) {
            System.out.println("! Transporte '" + transporte.getNome() + "' não está integrado ao SoulUp.");
            return null;
        }

        double pontosNecessarios = transporte.calcularPontosNecessarios(percentualCobertura);

        if (usuario instanceof UsuarioPremium) {
            UsuarioPremium premium = (UsuarioPremium) usuario;

            if (!premium.podeResgatar()) {
                System.out.println("[!] Limite mensal de resgates atingido para o usuário premium.");
                return null;
            }

            pontosNecessarios = pontosNecessarios / premium.getMultiplicadorBonus();
            System.out.println("★ Bônus premium aplicado! Pontos necessários reduzidos para: "
                    + String.format("%.2f", pontosNecessarios));
        }

        boolean reducaoBemSucedida = usuario.reduzirPontos(pontosNecessarios);

        if (!reducaoBemSucedida) {
            System.out.println("X Conversão cancelada por saldo insuficiente.");
            return null;
        }


        double valorCredito = transporte.getTarifaBase() * (percentualCobertura / 100.0);
        String codigoVoucher = "UB-" + contadorCodigoVoucher++;

        Voucher novoVoucher = new Voucher(codigoVoucher, usuario.getId(),
                transporte.getId(), valorCredito, pontosNecessarios, dataAtual);

        if (totalVouchers < vouchersGerados.length) {
            vouchersGerados[totalVouchers++] = novoVoucher;
        }

        registrarImpactoAmbiental(usuario.getId(), transporte.getCo2EvitadoPorUso(), pontosNecessarios);

        if (usuario instanceof UsuarioPremium) {
            ((UsuarioPremium) usuario).registrarResgate();
        }

        System.out.println("✓ Voucher gerado com sucesso!");
        return novoVoucher;
    }

    private void registrarImpactoAmbiental(String usuarioId, double co2Evitado, double pontosUsados) {
        ImpactoAmbiental impactoExistente = null;
        for (int i = 0; i < totalImpactos; i++) {
            if (impactosUsuarios[i].getUsuarioId().equals(usuarioId)) {
                impactoExistente = impactosUsuarios[i];
                break;
            }
        }

        if (impactoExistente != null) {
            impactoExistente.registrarViagem(co2Evitado, pontosUsados);
        } else {
            ImpactoAmbiental novoImpacto = new ImpactoAmbiental(usuarioId);
            novoImpacto.registrarViagem(co2Evitado, pontosUsados);
            if (totalImpactos < impactosUsuarios.length) {
                impactosUsuarios[totalImpactos++] = novoImpacto;
            }
        }
    }

    public void listarVouchers() {
        System.out.println("\n=== VOUCHERS GERADOS (" + totalVouchers + ") ===");
        if (totalVouchers == 0) {
            System.out.println("Nenhum voucher gerado ainda.");
            return;
        }
        for (int i = 0; i < totalVouchers; i++) {
            System.out.println(vouchersGerados[i]);
        }
    }

    public Voucher buscarVoucher(String codigo) {
        for (int i = 0; i < totalVouchers; i++) {
            if (vouchersGerados[i].getCodigo().equals(codigo)) {
                return vouchersGerados[i];
            }
        }
        return null;
    }

    public void exibirRelatorioImpactoGlobal() {
        System.out.println("\n=== RELATÓRIO DE IMPACTO AMBIENTAL ===");
        System.out.println("Total de usuários com impacto: " + totalImpactos);
        System.out.println("Total de vouchers emitidos   : " + totalVouchers);

        double co2Total = 0;
        for (int i = 0; i < totalImpactos; i++) {
            co2Total += impactosUsuarios[i].getTotalCo2Evitado();
            System.out.println(impactosUsuarios[i]);
        }

        System.out.println(">>> CO2 TOTAL EVITADO PELA PLATAFORMA: "
                + String.format("%.3f", co2Total) + " kg <<<");
    }

    public boolean validarAntifraude(Voucher voucher, Usuario usuario) {
        System.out.println("\n--- VERIFICAÇÃO ANTIFRAUDE ---");

        if (voucher == null) {
            System.out.println("X Voucher inválido ou não encontrado.");
            return false;
        }

        if (!voucher.getUsuarioId().equals(usuario.getId())) {
            System.out.println("X FRAUDE DETECTADA: voucher não pertence ao usuário!");
            voucher.setAntifraude(false);
            return false;
        }

        if (voucher.getValorCredito() > 50.0) {
            System.out.println("! Valor alto detectado. Verificação extra aplicada...");
            if (usuario.getSaldoPontos() < 100) {
                System.out.println("! Saldo restante baixo após resgate de alto valor. Marcado para revisão.");
            } else {
                System.out.println("✓ Verificação extra aprovada.");
            }
        }
        System.out.println("[✓] Voucher " + voucher.getCodigo() + " aprovado no antifraude.");
        return true;
    }
}