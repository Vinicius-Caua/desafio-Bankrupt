package tabuleiro;

import java.util.List;
import jogador.Jogador;

public class Tabuleiro {
    private final List<Propriedade> propriedades;

    public Tabuleiro(List<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    public Propriedade obterPropriedade(int posicao) {
        return propriedades.get(posicao % propriedades.size());
    }

    public int avancar(int posicao, int dado) {
        return (posicao + dado) % propriedades.size();
    }

    // Método resetar já definido
    public void resetar() {
        for (Propriedade propriedade : propriedades) {
            propriedade.setDono(null); // Reseta o dono de cada propriedade
        }
    }

    public void liberarPropriedades(Jogador jogador) {
        for (Propriedade propriedade : propriedades) {
            if (propriedade.getDono() != null && propriedade.getDono().equals(jogador)) {
                propriedade.setDono(null); // Libera a propriedade (remove o dono)
            }
        }
    }
}
