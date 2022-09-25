import Estrutura.Dicionario;
import Estrutura.Lista;

import java.util.ArrayList;
import java.util.Map;

public class Relevancia {

    private int letrasMin;
    Dicionario est;

    public Relevancia(int letrasMin, Dicionario est) {
        this.letrasMin = letrasMin;
        this.est = est;
    }

    public double relevancia(String[] termos, int nlinha){// realiza o calculo da quantidade de palavras na linha
        if(nlinha> est.getTamanhoDicionario()){
            return 0;
        }

        //quantidade de palavras na linha x
        Map<String, Lista> palavrasPerLinha = est.getTotalpl(nlinha);
        int quantPalavras = palavrasPerLinha.size();

        //System.out.println(quantPalavras);
        double calculo = ((double)1/quantPalavras)*(somatorio(termos, palavrasPerLinha, nlinha));
        return calculo;
    }

    private double somatorio(String[] termos, Map<String, Lista> palavras, int linhas){
        double retorno = 0;
        for(String palavra : termos){
            if(palavras.containsKey(palavra)){
                retorno += wij(palavras.get(palavra).get(linhas).getTotal(), est.get(palavra).size() , est.getTamanhoDicionario());
            }
        }
        return retorno;
    }

    private double wij(int ocorrencias, int numProdutos, int tamanhoDA){
        return ocorrencias*(Math.log(tamanhoDA)/numProdutos);
        //ocorrencias eh o numero que contem no termo
        //numProdutos eh o numero de linhas que possuem o termo
        //tamanhoDA eh o tamanho total do arquivo
    }

    public ArrayList<Integer> interseccao(Dicionario est, String[] termo) {
        for (String chave : termo) {//percorre o dicionario verifica se a chave esta contida
            if (!est.containskey(chave)) {// se nao retorna uma list
                return new ArrayList<>();
            }
        }
        ArrayList<Integer> intersec = est.get(termo[0]).getlinhas();
        ArrayList<Integer> aux = new ArrayList<>();


        for (String palavra : termo) {//percorre a string[] de termos/palavras
            for (Integer linha : intersec) {//percorre o num de linhas
                if (!est.get(palavra).getlinhas().contains(linha)) {
                    aux.add(linha);
                }
            }
            for (Integer i : aux) {
                intersec.remove(i);
            }
        }
        return intersec;
    }
}
