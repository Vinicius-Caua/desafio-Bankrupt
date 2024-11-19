package jogador;

public abstract class JogadorBase implements Jogador {
    protected int saldo;
    protected int posicaoAtual;
    protected boolean ativo;

    public JogadorBase() {
        this.saldo = 300; // Todos começam com 300 coins
        this.posicaoAtual = 0;
        this.ativo = true;
    }

    @Override
    public int getSaldo() {
        return saldo;
    }

    @Override
    public void ajustarSaldo(int valor) {
        saldo += valor;
        if (saldo < 0) {
            ativo = false; // Jogador vai à falência
        }
    }

    @Override
    public void jogar(int resultadoDado) {
        posicaoAtual = (posicaoAtual + resultadoDado) % 20;
    }

    @Override
    public void receberBonus() {
        saldo += 100; // Bônus ao completar uma volta no tabuleiro
    }

    @Override
    public void resetar() {
        this.saldo = 300;
        this.posicaoAtual = 0;
        this.ativo = true;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }
}
