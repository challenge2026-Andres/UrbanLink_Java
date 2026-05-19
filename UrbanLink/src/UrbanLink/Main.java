package UrbanLink;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("╔══════════════════════════════════════════╗");
		System.out.println("║               URBAN LINK                 ║");
		System.out.println("║  Pontos que movem você e o planeta       ║");
		System.out.println("╚══════════════════════════════════════════╝\n");
		
		// Usuários 
		Usuario usuario1 = new Usuario("USR-001", "Ana Souza", "ana@email.com", "123.456.789-00");
		Usuario usuario2 = new Usuario("USR-002", "Carlos Lima", "carlos@email.com", "987.654.321-11");

		// UsuarioPremium multiplicador 1.5x, até 10 resgates/mês
		UsuarioPremium usuarioPremium = new UsuarioPremium(
				"USR-003", "Beatriz Santos", "bia@email.com", "111.222.333-44",
				1.5, 10
		);

		// Transportes Públicos 
		TransportePublico metroBH = new TransportePublico(
				"TRP-001", "Metrô BH", "Metrô", 4.50, 2.1
		);

		// TransporteIntegrado herda de TransportePublico
		TransporteIntegrado metroSP = new TransporteIntegrado(
				"TRP-002", "Metro SP", "Metro", 5.00, 2.5, "CartaoSP"
		);
		TransporteIntegrado brtRio = new TransporteIntegrado(
				"TRP-003", "BRT Rio", "Onibus BRT", 4.05, 1.8, "RioCard"
		);

		// Serviço principal
		UrbanLinkService service = new UrbanLinkService(50);

		// ImpactoAmbiental
		ImpactoAmbiental impactoManual = new ImpactoAmbiental("USR-999");

		System.out.println("Distribuindo pontos iniciais...\n");
		usuario1.adicionarPontos(500.0);
		usuario2.adicionarPontos(200.0);
		usuarioPremium.adicionarPontos(800.0);

		usuario1.setEmail("ana.souza@soulup.com");
		System.out.println("Email atualizado de Ana: " + usuario1.getEmail());

		// toString() sobrescrito
		System.out.println("\n>>> Exibindo perfis :\n");
		System.out.println(usuario1);
		System.out.println();
		System.out.println(usuarioPremium);

		// polimorfismo
		System.out.println("\n>>> Exibindo transportes (POLIMORFISMO — toString sobrescrito):\n");
		System.out.println(metroBH);
		System.out.println();
		System.out.println(metroSP);


		// pontos em vouchers
		System.out.println("\nConvertendo pontos em vouchers de transporte...\n");

		// usuario1: 100% da tarifa do Metrô SP
		Voucher v1 = service.converterPontosEmVoucher(usuario1, metroSP, 100.0, "2025-05-12");
		if (v1 != null) {
			System.out.println(v1);
			service.validarAntifraude(v1, usuario1);
			v1.utilizarVoucher();
		}

		// usuario2: 50% da tarifa do BRT Rio
		Voucher v2 = service.converterPontosEmVoucher(usuario2, brtRio, 50.0, "2025-05-12");
		if (v2 != null) {
			System.out.println(v2);
		}

		// usuarioPremium: 100% com bônus premium aplicado
		Voucher v3 = service.converterPontosEmVoucher(usuarioPremium, metroSP, 100.0, "2025-05-12");
		if (v3 != null) {
			System.out.println(v3);
			service.validarAntifraude(v3, usuarioPremium);
		}

		// Tentativa com saldo insuficiente
		System.out.println("\n Tentativa com saldo insuficiente:");
		service.converterPontosEmVoucher(usuario2, metroSP, 100.0, "2025-05-12");

		// listar vouchers
		service.listarVouchers();

		// voucher manual
		System.out.println("\n Testando StatusVoucher :");
		Voucher voucherManual = new Voucher("SUP-9999", "USR-001", "TRP-002",
				4.50, 45.0, "2025-05-12");
		System.out.println(voucherManual);
		voucherManual.setStatus(Voucher.StatusVoucher.EXPIRADO);
		System.out.println("Status alterado para: " + voucherManual.getStatus());
		voucherManual.utilizarVoucher(); // deve falhar

		// impacto ambiental manual
		System.out.println("\n Demonstrando ImpactoAmbiental diretamente:");
		impactoManual.registrarViagem(2.5, 50.0);
		impactoManual.registrarViagem(2.5, 50.0);
		impactoManual.registrarViagem(1.8, 40.5);
		System.out.println(impactoManual);

		// relatorio global
		service.exibirRelatorioImpactoGlobal();

		// Menu interativo
		System.out.println("\n Iniciando menu interativo...\n");
		int opcao;

		do {
			System.out.println("╔══════════════════════════════════╗");
			System.out.println("║         MENU URBANLINK           ║");
			System.out.println("╠══════════════════════════════════╣");
			System.out.println("║ 1. Ver saldo de pontos           ║");
			System.out.println("║ 2. Calcular pontos para tarifa   ║");
			System.out.println("║ 3. Buscar voucher por código     ║");
			System.out.println("║ 4. Listar todos os vouchers      ║");
			System.out.println("║ 5. Ver relatório de impacto      ║");
			System.out.println("║ 0. Sair                          ║");
			System.out.println("╚══════════════════════════════════╝");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("! Entrada inválida. Digite um número.");
				opcao = -1;
				continue;
			}

			if (opcao == 1) {
				System.out.println("\n--- SALDO DE PONTOS ---");
				Usuario[] todosUsuarios = { usuario1, usuario2, usuarioPremium };
				for (int i = 0; i < todosUsuarios.length; i++) {
					System.out.println(todosUsuarios[i].getNome() + ": "
							+ todosUsuarios[i].getSaldoPontos() + " pontos");
				}

			} else if (opcao == 2) {
				System.out.println("\n--- SIMULADOR DE CONVERSÃO ---");
				System.out.println("Transportes disponíveis:");
				System.out.println("  [1] Metrô BH  - R$ 4,50");
				System.out.println("  [2] Metrô SP  - R$ 5,00");
				System.out.println("  [3] BRT Rio   - R$ 4,05");
				System.out.print("Escolha o transporte (1-3): ");

				int escolha;
				try {
					escolha = Integer.parseInt(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println("! Opção inválida.");
					continue;
				}

				TransportePublico transporteEscolhido;
				if (escolha == 1) {
					transporteEscolhido = metroBH;
				} else if (escolha == 2) {
					transporteEscolhido = metroSP;
				} else if (escolha == 3) {
					transporteEscolhido = brtRio;
				} else {
					System.out.println("! Opção inválida.");
					continue;
				}

				System.out.print("Percentual de cobertura (0-100): ");
				double percentual;
				try {
					percentual = Double.parseDouble(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println("! Valor inválido.");
					continue;
				}

				double pontos = transporteEscolhido.calcularPontosNecessarios(percentual);
				if (pontos >= 0) {
					System.out.println("Pontos necessários: " + String.format("%.1f", pontos) + " para cobrir " + percentual + "% da tarifa de " + transporteEscolhido.getNome());
				}

			} else if (opcao == 3) {
				System.out.print("\nDigite o código do voucher (ex: SUP-1000): ");
				String codigoBusca = scanner.nextLine().trim();
				Voucher encontrado = service.buscarVoucher(codigoBusca);
				if (encontrado != null) {
					System.out.println(encontrado);
				} else {
					System.out.println("! Voucher nao encontrado: " + codigoBusca);
				}

			} else if (opcao == 4) {
				service.listarVouchers();

			} else if (opcao == 5) {
				service.exibirRelatorioImpactoGlobal();

			} else if (opcao == 0) {
				System.out.println("\n✓ Encerrando UrbanLink...");

			} else {
				System.out.println("! Opção inválida. Tente novamente.");
			}

		} while (opcao != 0);

		scanner.close();
	}
}
