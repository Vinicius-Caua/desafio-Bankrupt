package jogador;

import tabuleiro.Propriedade;

public class JogadorExigente extends JogadorBase {
    @Override
    public boolean deveComprar(Propriedade propriedade, int saldo) {
        return saldo >= propriedade.getValorVenda() && propriedade.getValorAluguel() > 50;
    }

    @Override
    public String getComportamento() {
        return "Exigente";
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
