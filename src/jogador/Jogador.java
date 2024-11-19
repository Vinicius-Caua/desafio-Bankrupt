package jogador;

import tabuleiro.Propriedade;

public interface Jogador {
    boolean deveComprar(Propriedade propriedade, int saldo);

    void jogar(int resultadoDado);

    String getComportamento();

    int getSaldo();

    void ajustarSaldo(int valor);

    void receberBonus();

    void resetar();

    boolean isAtivo();

    int getPosicaoAtual();

    void setPosicaoAtual(int novaPosicao);

}
