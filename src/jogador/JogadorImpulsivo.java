package jogador;

import tabuleiro.Propriedade;

public class JogadorImpulsivo extends JogadorBase {
    @Override
    public boolean deveComprar(Propriedade propriedade, int saldo) {
        return saldo >= propriedade.getValorVenda(); // Sempre compra, desde que tenha saldo
    }

    @Override
    public String getComportamento() {
        return "Impulsivo";
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
