package Tads;


import Estrutura.Lista;

class RNnode{
    Lista data;
    RNnode left, right, dad;
    boolean cor; // 'false' eh cor preta, 'true' eh vermelho

    public RNnode(Lista data, RNnode left, RNnode right){
        this.data = data;
        this.left = left;
        this.right= right;
        this.cor= true;
    }

    public RNnode (Lista data) {
        this (data, null, null);
    }

    public Lista getData() {
        return data;
    }

    public void setData(Lista data) {
        this.data = data;
    }

    public RNnode getLeft() {
        return left;
    }

    public void setLeft(RNnode left) {
        this.left = left;
    }

    public RNnode getRight() {
        return right;
    }

    public void setRight(RNnode right) {
        this.right = right;
    }

    public RNnode getDad() {
        return dad;
    }

    public void setDad(RNnode dad) {
        this.dad = dad;
    }

    public boolean isCor() {
        return cor;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }



}

public class ArvoreRN {

}
