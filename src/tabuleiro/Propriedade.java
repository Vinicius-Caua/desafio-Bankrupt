package tabuleiro;

import jogador.Jogador;

public class Propriedade {
    private final int posicao;
    private final int valorVenda;
    private final int valorAluguel;
    private Jogador dono; // Alterado para aceitar objetos Jogador

    public Propriedade(int posicao, int valorVenda, int valorAluguel) {
        this.posicao = posicao;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
        this.dono = null; // Propriedade começa sem dono
    }

    public int getPosicao() {
        return posicao;
    }

    public int getValorVenda() {
        return valorVenda;
    }

    public int getValorAluguel() {
        return valorAluguel;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        return "Propriedade{" +
                "posicao=" + posicao +
                ", valorVenda=" + valorVenda +
                ", valorAluguel=" + valorAluguel +
                ", dono=" + (dono != null ? dono.getComportamento() : "Nenhum") +
                '}';
    }
}
