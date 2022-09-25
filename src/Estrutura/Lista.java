package Estrutura;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lista {
    private LinkedList<Valor> retorno;

    public Lista(){
        retorno = new LinkedList<>();
    }

    public Valor get(Integer linha) {
        for (Valor v : retorno) {
            if (v.getLinha() == linha) {
                return v;
            }
        }
        return null;
    }

    public void add(Integer linha) {
        Valor atual = null;

        for (Valor v : retorno) {
            if (v.getLinha() == linha) {
                atual = v;
            }
        }
        if (atual == null) {
            atual = new Valor(linha, 1);
            retorno.add(atual);
        } else {
            atual.incrementaQuant();
        }
    }

    public boolean contains(int lin){
        for(Valor i: retorno){
            if(i.getLinha()== lin){
                return true;
            }
        }
        return false;
    }
    public int size() {
        return retorno.size();
    }

    public ArrayList<Integer> getlinhas() {
        ArrayList<Integer> retorna = new ArrayList<Integer>();
        for (Valor v : retorno) {
            retorna.add(v.getLinha());
        }
        return retorna;
    }

    @Override
    public String toString() {
        return " \nLista " +
                " retorno : " + retorno;
    }
}
