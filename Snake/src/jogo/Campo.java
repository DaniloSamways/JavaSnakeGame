/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 2info2021
 */
public class Campo extends JPanel implements ActionListener {

    Fruta fruta;
    Cobra cobra;
    Cobra corpo[] = new Cobra[1000];
    Cenario cenario[] = new Cenario[10];
    int tamanhocenario = 4;
    int tamanho = 3;
    Timer t;
    Image fundo;
    int PontoJ = 0;
    int vida = 3;
    String msg = "";

    public Campo(int largura, final int altura) {
        setFocusable(true);
        setDoubleBuffered(true);
        setSize(largura, altura);
        fundo = new ImageIcon(this.getClass().getResource("/imagens/campo.jpg")).getImage().getScaledInstance(largura, altura, 1);
        inicializa();
        repaint();
        t = new Timer(100, this);
        t.start();
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_UP){
                    cobra.setDy(-20);
                    cobra.setDx(0);
                }
                if (tecla == KeyEvent.VK_DOWN){
                    cobra.setDy(20);
                    cobra.setDx(0);
                }
                if (tecla == KeyEvent.VK_LEFT){
                    cobra.setDx(-20);
                    cobra.setDy(0);
                }
                if (tecla == KeyEvent.VK_RIGHT){
                    cobra.setDx(20);
                    cobra.setDy(0);
                }
                if (tecla == 'P' || tecla == 'p'){
                    if (t.isRunning()){
                        msg = "Pausa";
                        repaint();
                        t.stop();
                    } else{
                        t.restart();
                        msg = "";
                    }
                }
                if(tecla == 'r'|| tecla == 'R'){
                    inicializa();
                    t.start();
                }   
                if (tecla == KeyEvent.VK_SPACE && vida == 0){
                    msg = "";
                    t.start();
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i = tamanho - 1; i > 0; i--){
            corpo[i].setX(corpo[i - 1].getX());
        }
    }
}
