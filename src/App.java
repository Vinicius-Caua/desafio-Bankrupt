import dado.Dado;
import fileConfig.GameConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import jogador.*;
import tabuleiro.Propriedade;
import tabuleiro.Tabuleiro;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            // Carregar configuração do jogo
            GameConfig gameConfig = new GameConfig();
            List<Propriedade> propriedades = gameConfig.carregarPropriedades();
            Tabuleiro tabuleiro = new Tabuleiro(propriedades);

            // Estatísticas
            int partidasTimeout = 0;
            int totalTurnos = 0;
            int[] vitoriasPorComportamento = new int[4]; // 0: Impulsivo, 1: Exigente, 2: Cauteloso, 3: Aleatório

            // Simular 300 partidas
            for (int i = 0; i < 300; i++) {
                List<Jogador> jogadores = inicializarJogadores();
                int turnos = simularPartida(jogadores, tabuleiro);
                totalTurnos += turnos;
                if (turnos >= 1000) {
                    partidasTimeout++;
                }

                // Contabilizar vitórias
                Jogador vencedor = determinarVencedor(jogadores);
                if (vencedor != null) {
                    switch (vencedor.getComportamento()) {
                        case "Impulsivo" -> vitoriasPorComportamento[0]++;
                        case "Exigente" -> vitoriasPorComportamento[1]++;
                        case "Cauteloso" -> vitoriasPorComportamento[2]++;
                        case "Aleatório" -> vitoriasPorComportamento[3]++;
                    }
                }

                // Resetar tabuleiro e propriedades para nova partida
                tabuleiro.resetar();
            }

            // Exibir resultados
            System.out.println("Resultados após 300 partidas:");
            System.out.println("1. Quantidade de partidas que terminaram por time out: " + partidasTimeout);
            System.out.println("2. Turnos médios por partida: " + (totalTurnos / 300));
            System.out.println("3. Percentual de vitórias por comportamento:");
            System.out.printf("   - Impulsivo: %.2f%%%n", (vitoriasPorComportamento[0] / 300.0) * 100);
            System.out.printf("   - Exigente: %.2f%%%n", (vitoriasPorComportamento[1] / 300.0) * 100);
            System.out.printf("   - Cauteloso: %.2f%%%n", (vitoriasPorComportamento[2] / 300.0) * 100);
            System.out.printf("   - Aleatório: %.2f%%%n", (vitoriasPorComportamento[3] / 300.0) * 100);

            int comportamentoMaisVitorioso = determinarComportamentoMaisVitorioso(vitoriasPorComportamento);
            String[] comportamentos = {"Impulsivo", "Exigente", "Cauteloso", "Aleatório"};
            System.out.println("4. Comportamento que mais vence: " + comportamentos[comportamentoMaisVitorioso]);

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao carregar arquivo de configuração: " + e.getMessage());
        }
    }

    // Inicializa jogadores
    private static List<Jogador> inicializarJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new JogadorImpulsivo());
        jogadores.add(new JogadorExigente());
        jogadores.add(new JogadorCauteloso());
        jogadores.add(new JogadorAleatorio());
        return jogadores;
    }

    // Simula uma única partida
    private static int simularPartida(List<Jogador> jogadores, Tabuleiro tabuleiro) {
        Dado dado = new Dado();
        int turnos = 0;

        while (jogadoresAtivos(jogadores) > 1 && turnos < 1000) {
            for (Jogador jogador : jogadores) {
                if (!jogador.isAtivo()) continue;

                // Jogar dado e mover
                int resultadoDado = dado.lancar();
                jogador.jogar(resultadoDado);

                // Obter propriedade onde o jogador caiu
                Propriedade propriedade = tabuleiro.obterPropriedade(jogador.getPosicaoAtual());

                // Verificar ações na propriedade
                if (propriedade.getDono() == null) {
                    // Sem dono: decidir comprar
                    if (jogador.deveComprar(propriedade, jogador.getSaldo())) {
                        jogador.ajustarSaldo(-propriedade.getValorVenda());
                        propriedade.setDono(jogador);
                    }
                } else if (propriedade.getDono() != jogador) {
                    // Com dono: pagar aluguel
                    jogador.ajustarSaldo(-propriedade.getValorAluguel());
                    propriedade.getDono().ajustarSaldo(propriedade.getValorAluguel());
                }

                // Verificar bônus de volta no tabuleiro
                if (jogador.getPosicaoAtual() < resultadoDado) {
                    jogador.receberBonus();
                }

                // Eliminar jogador se saldo negativo
                if (jogador.getSaldo() < 0) {
                    jogador.resetar();
                    tabuleiro.liberarPropriedades(jogador);
                }
            }
            turnos++;
        }

        return turnos;
    }

    // Determina o vencedor da partida
    private static Jogador determinarVencedor(List<Jogador> jogadores) {
        return jogadores.stream()
                .filter(Jogador::isAtivo)
                .max(Comparator.comparingInt(Jogador::getSaldo))
                .orElse(null);
    }

    // Determina o comportamento com mais vitórias
    private static int determinarComportamentoMaisVitorioso(int[] vitorias) {
        int maxIndex = 0;
        for (int i = 1; i < vitorias.length; i++) {
            if (vitorias[i] > vitorias[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Conta jogadores ativos
    private static int jogadoresAtivos(List<Jogador> jogadores) {
        return (int) jogadores.stream().filter(Jogador::isAtivo).count();
    }
}
