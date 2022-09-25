package Estrutura;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Dicionario {

    private int letrasMin;
    private int tamanhoDicionario;
    private Map<String, Lista> dicionario;

    public Dicionario(int letrasMin) {
        dicionario = new LinkedHashMap<String, Lista>();
        this.letrasMin = letrasMin;
    }

    public void lerArquivo(String arquivo) throws FileNotFoundException {
        Scanner leitor = new Scanner(new File(arquivo));

        leitor.useDelimiter("\n");  // Realiza a procura linha por linha

        boolean trava_descricao = false;      // Trava ao achar o campo descrição
        int campo_desc = 0;             // Campo da descrição
        int linha = 1;                  // Linha do csv escaneada atualmente

        while (leitor.hasNext()) {
            String campo_atual = leitor.next();
            if (!trava_descricao) { // Procura o campo que tem a descrição
                for (String campo : campo_atual.split(",")) {
                    if (campo.contains("description")) {//se possui o campo da descricao
                        trava_descricao = true;
                        // Pula pra a próxima linha
                        campo_atual = leitor.next();
                        linha++;
                        break;
                    }
                    campo_desc++;
                }
            }

            // String que vai ser cortada e adicionada na árvore
            String palavras_para_inserir = campo_atual.split(",")[campo_desc];

            if (palavras_para_inserir.contains("\"")) {
                for (int i = campo_desc+1; i < campo_atual.split(",").length; i++) {
                    palavras_para_inserir += campo_atual.split(",")[i];
                    if (campo_atual.split(",")[i].contains("\"")) {
                        break;
                    }
                }
            }
            palavras_para_inserir = palavras_para_inserir.toLowerCase();
            palavras_para_inserir = palavras_para_inserir.replaceAll("[.,+=*&:;%$#@|!?_\"\'’\\r•]", "");
            palavras_para_inserir = palavras_para_inserir.replaceAll("[/\u00A0]", " ");

            for (String palavra : palavras_para_inserir.split(" ")) {
                if (palavra.length() >= letrasMin) {
                    add(palavra.substring(0, letrasMin), linha);
                }
            }

            linha++;
        }
        setTamanhoDicionario(linha - 1);
        leitor.close();
    }

    public void add(String palavra, Integer linha) {
        Lista temp;

        // Se o dicionário já tem a lista
        if (dicionario.containsKey(palavra)) {
            temp = dicionario.get(palavra);
        } else {
            temp = new Lista();
        }
        temp.add(linha);
        dicionario.put(palavra, temp);
    }

    public Set<String> keySet(){
        return dicionario.keySet();
    }

    public Lista get(String chave){
        return dicionario.get(chave);
    }

    public int getTamanhoDicionario() {
        return tamanhoDicionario;
    }

    public void setTamanhoDicionario(int tamanhoDicionario) {
        this.tamanhoDicionario = tamanhoDicionario;
    }

    public Map<String, Lista> getTotalpl(int linha){
        Map<String, Lista> retorno = new LinkedHashMap<>();
        for(String aux: dicionario.keySet()){
            Lista var = dicionario.get(aux);
            if(dicionario.get(aux).contains(linha)){
                retorno.put(aux, dicionario.get(aux));
            }
        }
        return retorno;
    }

    public boolean containskey(String chave) {
        return dicionario.containsKey(chave);
    }

    @Override
    public String toString() {
        return "\nDicionario: " +
                "letrasMin: " + letrasMin +
                " e dicionario com valor \n" + dicionario ;
    }
}
