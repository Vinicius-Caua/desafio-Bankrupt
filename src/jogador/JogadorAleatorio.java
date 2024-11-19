package jogador;

import java.util.Random;
import tabuleiro.Propriedade;

public class JogadorAleatorio extends JogadorBase {
    private final Random random = new Random();

    @Override
    public boolean deveComprar(Propriedade propriedade, int saldo) {
        return saldo >= propriedade.getValorVenda() && random.nextBoolean(); // 50% de chance
    }

    @Override
    public String getComportamento() {
        return "Aleatório";
    }

    @Override
    public int getPosicaoAtual() {
        return this.posicaoAtual;  // Retorna a posição atual do jogador
    }

    @Override
    public void setPosicaoAtual(int posicao) {
        this.posicaoAtual = posicao;  // Define a posição atual do jogador
    }
}
