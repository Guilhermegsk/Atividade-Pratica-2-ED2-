package Estrutura;

public class Valor {
    private int linha;
    private int total;

    public Valor(int linha, int total) {
        this.linha = linha;
        this.total = total;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void incrementaQuant(){
        this.total++;
    }


    @Override
    public String toString() {
        return "Valor: " +
                "linha: " + linha +
                " e total: " + total;
    }
}
