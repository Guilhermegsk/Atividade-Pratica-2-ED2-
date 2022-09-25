package Tads;

import Estrutura.Lista;

import java.util.ArrayList;


class AVLNode
{
    AVLNode left, right;
    Lista data;
    int height;
    String key;

    public AVLNode(String key, Lista data)
    {
        left = null;
        right = null;
        this.key = key;
        this.data = data;
        height = 0;
    }

}

public class ArvoreAvl {
    private AVLNode raiz;


    public ArvoreAvl()
    {
        raiz = null;
    }

    //insert main
    public void insert(String data, Lista list)
    {
        raiz = insert(data, list ,raiz);
    }

    private int height(AVLNode t )
    {
        return t == null ? -1 : t.height;
    }
    //altura maxima
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }

    //função insert recursiva
    private AVLNode insert(String x, Lista list ,AVLNode t)
    {
        if (t == null) {
            t = new AVLNode(x, list);
        }
        else if (x.compareTo(t.key) < 0) {
            t.left = insert(x, list, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.key) < 0) {
                    t = rotateWithLeftChild(t);
                }else {
                    t = doubleWithLeftChild(t);
                }
            }
        }
        else if( x.compareTo(t.key) > 0 )
        {
            t.right = insert( x,list , t.right );
            if( height( t.right ) - height( t.left ) == 2 ) {
                if (x.compareTo(t.right.key) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        }
        else
            ;
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    private AVLNode rotateWithLeftChild(AVLNode k2)
    {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }


    private AVLNode rotateWithRightChild(AVLNode k1)
    {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    private AVLNode doubleWithLeftChild(AVLNode k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    private AVLNode doubleWithRightChild(AVLNode k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    public boolean busca(String chave) {
        return busca(raiz, chave);
    }


    private boolean busca(AVLNode no, String chave) {
        boolean encontrado = false;
        while (no != null) {
            if (no.key.compareTo(chave) > 0) {
                no = no.left;
            } else if (no.key.compareTo(chave) < 0) {
                no = no.right;
            } else {
                encontrado = true;
                break;
            }
            return busca(no, chave);
        }
        return encontrado;
    }

    public void inorder()
    {
        inorder(raiz);
    }

    private void inorder(AVLNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.data +" ");
            inorder(r.right);
        }
    }
}

