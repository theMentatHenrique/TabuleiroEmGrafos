import com.company.Coord;
import com.company.Peça;
import com.company.Player;
import com.company.Porta;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Node {
        Coord pos;
        String id = "";
        Peça[][] p;
        int lc;
    }

    public static void main(String[] args) {
        Coord cordenada=new Coord(1,2);

        //Queue<Integer> posicoes=new LinkedList<Integer>();
        //posicoes.add(1);
       // Coord
        //posicoes.add(2);
       // posicoes.add(3);
       // System.out.println(posicoes.element());

        ArrayList<Node> n = leArquivo("caso04.txt");

        for (int i=0;i<n.size(); i++) {
            if (!(n.get(i).id.equals(""))) {
                Player p = new Player(n.get(i).id, n.get(i).pos.getX(), n.get(i).pos.getY());
              ArrayList<Coord> posicaoPlayer=new ArrayList<Coord>();
                posicaoPlayer.add(new Coord(n.get(i).pos.getX(),n.get(i).pos.getY()));
               int resp= encontraCaminhos(p, n.get(i).p, n.get(i).lc,posicaoPlayer);
                System.out.println(resp);
            }
        }


    }

    public static void imprimeCenario(Peça[][] tabuleiro, int lc) {
        for (int i = 0; i < lc; i++) {
            for (int j = 0; j < lc; j++) {
                System.out.print(tabuleiro[i][j].getIdentificador());
            }
            System.out.println();
        }
    }

    public static ArrayList<Node> leArquivo(String nomeArquivo) {
        ArrayList<Node> n = new ArrayList<Node>();
        int lc = 0;
        Peça[][] tab = null;

        Path path = Paths.get(nomeArquivo);
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("utf8"))) {
            String line = br.readLine();
            String[] separa = line.split("");
            lc = separa.length;
            tab = new Peça[lc][lc];

            BufferedReader aux = Files.newBufferedReader(path, Charset.forName("utf8"));
            int p = 0;
            for (int i = 0; i < lc; i++) {
                line = aux.readLine();
                separa = line.split("");
                for (int j = 0; j < lc; j++) {
                    tab[i][j] = new Peça(separa[j]);
                    if ((separa[j]).matches("[0-9]")) {
                        Node auxNode = new Node();
                        auxNode.pos = new Coord(i, j);
                        auxNode.id = separa[j];
                        auxNode.lc = lc;
                        n.add(auxNode);
                        p++;
                    }
                }
            }
            for (Node node : n) {
                node.p = tab;
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
        return n;
    }

    public static int encontraCaminhos(Player player, Peça[][] tabuleiro,int lc,ArrayList<Coord>posicoes) {
       // System.out.println(posicoes.size());
        if(posicoes.size()==0){
         //   imprimeCenario(tabuleiro,lc);
            return player.getContador();}
        //System.out.println("LINHA:"+ posicoes.get(0).getX()+ " "+ "COLUNA:" + posicoes.get(0).getY() );
       // imprimeCenario(tabuleiro,lc);

        Coord pos=posicoes.get(0);
        posicoes.remove(0);

        Coord cima=new Coord(pos.getX()-1,pos.getY());
        Coord direita=new Coord(pos.getX() ,pos.getY()+1);
        Coord baixo=new Coord(pos.getX()+1,pos.getY());
        Coord esquerda= new Coord(pos.getX() ,pos.getY()-1);


        if(!tabuleiro[pos.getX()-1][pos.getY()].getIdentificador().equals("#") && tabuleiro[pos.getX()-1][pos.getY()].getVerificado()==false) {
            posicoes.add(cima);
        }
        if(!tabuleiro[pos.getX()][pos.getY()+1].getIdentificador().equals("#") && tabuleiro[pos.getX()][pos.getY()+1].getVerificado()==false){
            posicoes.add(direita);

        }
        if(!tabuleiro[pos.getX()+1][pos.getY()].getIdentificador().equals("#") && tabuleiro[pos.getX()+1][pos.getY()].getVerificado()==false) {
            posicoes.add(baixo);

        }
        if(!tabuleiro[pos.getX()][pos.getY()-1].getIdentificador().equals("#") && tabuleiro[pos.getX()][pos.getY()-1].getVerificado()==false){
            posicoes.add(esquerda);

        }

            for(int i=0;i<posicoes.size();i++){
                Coord ajuda=posicoes.get(i);
                if(tabuleiro[ajuda.getX()][ajuda.getY()].getIdentificador().equals("#")) {
                    System.out.println("aqui:" + " " +tabuleiro[ajuda.getX()][ajuda.getY()].getIdentificador());
                }
            }

       // tabuleiro[pos.getX()-1][pos.getY()].setIdentificador("1");;//CIMA
       // tabuleiro[pos.getX()][pos.getY()+1].setIdentificador("1");;//DIREITA
       // tabuleiro[pos.getX()+1][pos.getY()].setIdentificador("1");;//BAIXO
       // tabuleiro[pos.getX()][pos.getY()-1].setIdentificador("1");;//ESQUERDA


        boolean cima1 = percorreRevisado(tabuleiro, cima, player, lc);
        boolean direita1 = percorreRevisado(tabuleiro, direita , player, lc);
        boolean baixo1 = percorreRevisado(tabuleiro, baixo, player, lc);
       boolean esquerda1 = percorreRevisado(tabuleiro, esquerda, player, lc);


       // imprimeCenario(tabuleiro,lc);
            return encontraCaminhos(player, tabuleiro,lc,posicoes);



    }

    public static boolean percorreRevisado(Peça[][] tabuleiro, Coord pos, Player player, int lc) {
        Peça posicao = tabuleiro[pos.getX()][pos.getY()];
       // System.out.println("LINHA:" +pos.getX() + "  " + "COLUNA:" + pos.getY());
        if (!posicao.getIdentificador().equals("#") && posicao.getVerificado() == false) {
            if (!posicao.getIdentificador().matches("\\.|[0-9]")) {
                String aux = posicao.getIdentificador().toUpperCase();
                if (aux.equals(posicao.getIdentificador())) {
                    player.addPorta(posicao.getIdentificador(), new Coord(pos.getX(), pos.getY()));
                } else {
                    player.addChave(posicao.getIdentificador());
                }
                //tabuleiro[pos.getX()][pos.getY()].setIdentificador(player.getIdentificador());
                //imprimeCenario(tabuleiro, lc);

                return true;
            } else {
                player.incrementaContador();
                tabuleiro[pos.getX()][pos.getY()].verificado();
                //tabuleiro[pos.getX()][pos.getY()].setIdentificador(player.getIdentificador());
              //  imprimeCenario(tabuleiro, lc);

                return true;
            }
        }
        return false;
    }
}