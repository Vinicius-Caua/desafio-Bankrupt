package fileConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tabuleiro.Propriedade;

public class GameConfig {
    private final File file;

    public GameConfig() {
        // Utilize um caminho relativo para evitar problemas de compatibilidade
        this.file = new File("src/fileConfig/gameConfig.txt");
    }

    public List<Propriedade> carregarPropriedades() throws IOException {
        List<Propriedade> propriedades = new ArrayList<>();

        try (Scanner scan = new Scanner(file)) {
            int posicao = 0; // Para identificar a posição no tabuleiro
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                String[] valores = linha.trim().split("\\s+"); // Divide a linha por espaços em branco

                if (valores.length == 2) {
                    int valorVenda = Integer.parseInt(valores[0]);
                    int valorAluguel = Integer.parseInt(valores[1]);

                    // Cria a propriedade e adiciona à lista
                    propriedades.add(new Propriedade(posicao, valorVenda, valorAluguel));
                    posicao++;
                }
            }
        }

        return propriedades;
    }
}
