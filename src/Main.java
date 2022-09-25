

import Estrutura.Dicionario;
import Tads.ArvoreAvl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {

            Runtime runtime = Runtime.getRuntime();// funcao pra pegar memoria
            System.out.println("Memória usada: " + (runtime.totalMemory()- runtime.freeMemory())/(1024*1024) + "MB");
            System.out.println("--Bem vindo ao sistema--");
            int letrasMin = 10;//variavel de corte do num de palavras
            Dicionario est = new Dicionario(letrasMin);
            Relevancia relev = new Relevancia(letrasMin, est);
            long tempoInicial = System.currentTimeMillis();// var pra tempo
            try{
                est.lerArquivo("victoriassecret_com.csv");// colocar o nome do arquivo aqui
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
            System.out.println("Tempo de leitura do arquivo:");
            System.out.println(System.currentTimeMillis() - tempoInicial + " ms");
            System.out.println("Memória usada: " + (runtime.totalMemory()- runtime.freeMemory())/(1024*1024) + "MB");


            while (true) {// menu
                System.out.println("\n1-Arvore Avl" + "\n2-Hash" + "\n3-Ver relevancia pela interseção" + "\n4- Ver tamanho de linhas arquivo");
                Scanner input = new Scanner(System.in);
                int opcao = input.nextInt();

                switch(opcao){
                    case 1:
                        ArvoreAvl arvore = new ArvoreAvl();
                        System.out.println("Arvore criada com sucesso");
                        System.out.println("\npreenchendo a arvore...");
                        long tempoInicialAvl = System.currentTimeMillis();
                        for(String key: est.keySet()){//percorre o campo string do hashmap
                            arvore.insert(key, est.get(key));// insere na arvore
                        }
                        System.out.println("Memória usada: " + (runtime.totalMemory()- runtime.freeMemory())/(1024*1024) + "MB");
                        System.out.println("Tempo de preenchimento da arvore Avl:");
                        System.out.println(System.currentTimeMillis() - tempoInicialAvl + " ms");
                        Scanner escolha = new Scanner(System.in);
                        Scanner escolher = new Scanner(System.in);
                        System.out.println("\nDeseja realizar busca de alguma palavra? \n[1]-Sim \n[2]-Nao");
                        int variavel = escolha.nextInt();
                        if(variavel == 1){
                            System.out.println("Digite o nome a ser buscado :");
                            String varString = escolher.nextLine();
                            long tempoprocuraAvl = System.currentTimeMillis();
                            System.out.println("\n É " + arvore.busca(varString) + " que o elemento esta na arvore");
                            System.out.println("\nTempo de procura da arvore Avl:");
                            System.out.println(System.currentTimeMillis() - tempoprocuraAvl + " ms");
                        } else{
                            System.out.println("\nRetornando ao menu");
                        }
                        System.out.println("\nListando Avl ...");
                        arvore.inorder();//chama a função pra listar a arvore
                        System.out.println("\n\nPalavras contidas respectivamente com a ordem lista acima : " + est.keySet());
                        break;
                    case 2:
                        Scanner selectInt = new Scanner(System.in);
                        Scanner selectString = new Scanner(System.in);
                        Scanner enterHash = new Scanner(System.in);
                        System.out.println("\nDeseja realizar busca de alguma palavra? \n[1]-Sim \n[2]-Nao");
                        int variavelHash = selectInt.nextInt();
                        if(variavelHash == 1){
                            System.out.println("Digite o nome a ser buscado :");
                            String varStringHash = selectString.nextLine();
                            long tempoprocuraHash = System.currentTimeMillis();
                            System.out.println("\n É " + est.containskey(varStringHash) + " que o elemento esta no hash");//a função verifica se contem no map
                            System.out.println("\nTempo de procura do Hash:");
                            System.out.println(System.currentTimeMillis() - tempoprocuraHash + " ms");
                            System.out.println("Memória usada: " + (runtime.totalMemory()- runtime.freeMemory())/(1024*1024) + "MB");
                        } else{
                            System.out.println("\nRetornando ao menu");
                        }

                        System.out.println("Listando hash...");
                        System.out.println("Pressione Enter para continuar");
                        enterHash.nextLine();
                        for(String st: est.keySet()){
                            System.out.println(est.get(st));
                        }
                        System.out.println("\n\nPalavras contidas respectivamente com a ordem lista acima : " + est.keySet());
                        break;
                    case 3:
                        System.out.println("Carregando interseção...");//crisscross // antimicrob
                        String[] palavras = {"crisscross"};//palavra escolhida para ver a relevancia separada(se for mais de uma por {"","",...}

                        //System.out.println("Relevancia : " + relev.relevancia(palavras, 573));//teste
                        ArrayList<Integer> resultado = relev.interseccao(est, palavras);
                        for (Integer linha : resultado) {
                          System.out.println("Relevância para linha " + linha + ":" + relev.relevancia(palavras, linha));
                        }
                        break;
                    case 4:
                        System.out.println("Numero de linhas do arquivo....");
                        System.out.println("\n Tamanho dicionario: " + est.getTamanhoDicionario());
                        break;
                }
            }
        }
}
