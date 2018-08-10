/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * 
 */
public class TreeMorse extends Pane {
    
    private Node root;
    HashMap<String, String[]> map;
    Timeline tl;
    private AudioClip linea;
    private AudioClip punto;
    
    public TreeMorse() {
        root = new Node("Inicio");
        root.setFill(Color.WHITE); 
        root.setTranslateX(Const.X/2 - 20);
        root.setTranslateY(0);
        super.getChildren().add(root);
        map = cargarMapa();
        tl = new Timeline();
        linea = new AudioClip(TreeMorse.class.getResource("/espol/edu/ec/recursos/linea.mp3").toExternalForm());
        punto = new AudioClip(TreeMorse.class.getResource("/espol/edu/ec/recursos/punto.mp3").toExternalForm());
        for(String key: map.keySet()) {
            crearArbol(root, map.get(key), key, 0, map.get(key).length, Const.MAX_WIDTH);
        }
        String[] s = {" "};
        map.put(" ", s);
    }
    
    private Node crearArbol(Node node, String[] data, String key, int i, int j, double ancho) {
        if(i == j) {
            node.setData(key); 
            return node;
        }else if(data[i].equals("-")){
            if(node.getLeft() == null){
                Node n = new Node("");
                node.setLeft(n);
                n.setTranslateX(node.getTranslateX() - ancho/4);
                n.setTranslateY(node.getTranslateY() + 100);
                Line l = new Line(node.getTranslateX()+20, node.getTranslateY()+20, n.getTranslateX()+20, n.getTranslateY()+20);
                l.setStroke(Color.BLUE);
                super.getChildren().remove(node);
                super.getChildren().addAll(l, node, n);
                n.setFill(Color.WHITE); 
                return crearArbol(n, data, key, i+1, j, ancho/2);
            }else{
                return crearArbol(node.getLeft(), data, key, i+1, j, ancho/2);
            }
        }else if(data[i].equals(".")){
            if(node.getRight() == null){
                Node n = new Node("");
                node.setRight(n);
                n.setTranslateX(node.getTranslateX() + ancho/4);
                n.setTranslateY(node.getTranslateY() + 100);
                Line l = new Line(node.getTranslateX()+20, node.getTranslateY()+20, n.getTranslateX()+20, n.getTranslateY()+20);
                l.setStroke(Color.RED); 
                super.getChildren().remove(node);
                super.getChildren().addAll(l, node, n);
                n.setFill(Color.WHITE);
                return crearArbol(n, data, key, i+1, j, ancho/2);
            }else{
                return crearArbol(node.getRight(), data, key, i+1, j, ancho/2);
            }
        }  
        return node;
    }
    
    public void reproducir(String word) {
        word = word.toUpperCase();
        reproducir(word, 0, word.length());
       
    }
    
    private void reproducir(String word, int start, int end){
        if(start == end){
        }else
            reproducir(root, map.get(word.charAt(start)+""), 0, map.get(word.charAt(start)+"").length, word, start, end);
    }
   
    
    private void reproducir(Node node, String[] array, int i, int j, String word, int start, int end) {
        tl.stop();
        tl.getKeyFrames().clear();
        if(i == j) {
            if(array[i-1].equals("-")){
                KeyValue kv = new KeyValue(node.getContent().fillProperty(), Color.ORANGE);
                KeyFrame kf = new KeyFrame(Duration.millis(300), e-> reproducir(word, start+1, end), kv);
                tl.getKeyFrames().add(kf);
                tl.setAutoReverse(false);
                tl.setCycleCount(1);
                tl.play();
            }else {     
                KeyValue kv = new KeyValue(node.getContent().fillProperty(), Color.ORANGE);
                KeyFrame kf = new KeyFrame(Duration.millis(300), e-> reproducir(word, start+1, end), kv);
                tl.getKeyFrames().add(kf);
                tl.setAutoReverse(false);
                tl.setCycleCount(1);
                tl.play(); 
                
            }
        }else if(array[i].equals("-")){ 

            KeyValue kv = new KeyValue(node.getContent().fillProperty(), Color.WHITE);
            KeyFrame kf = new KeyFrame(Duration.millis(1200), 
                    e -> decolorear(node.getLeft(), array, i+1, j, word, start, end, 1200), kv);
            tl.getKeyFrames().add(kf);
            tl.setAutoReverse(false);
            tl.setCycleCount(1);
            tl.play();
        }else if(array[i].equals(".")){

            KeyValue kv = new KeyValue(node.getContent().fillProperty(), Color.WHITE);
            KeyFrame kf = new KeyFrame(Duration.millis(700), 
                    e -> decolorear(node.getRight(), array, i+1, j, word, start, end, 700), kv);
            tl.getKeyFrames().add(kf);
            tl.setAutoReverse(false);
            tl.setCycleCount(1);
            tl.play();
        }else if(array[i].equals(" ")){
            try {
                Thread.sleep(1500);
                reproducir(word, start+1, end);
            } catch (InterruptedException ex) {
                Logger.getLogger(TreeMorse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void decolorear(Node node, String[] array, int i, int j, String word, int start, int end, double ms) {
        tl.stop();
        tl.getKeyFrames().clear();
        KeyValue kv = new KeyValue(node.getContent().fillProperty(), Color.ORANGE);
        KeyFrame kf = new KeyFrame(Duration.millis(ms), 
                e -> reproducir(node, array, i, j, word, start, end), kv);
        tl.getKeyFrames().add(kf);
        tl.setAutoReverse(true);
        tl.setCycleCount(1);
        tl.play();
        linea.stop();
        punto.stop();
        if(ms == 500)
            punto.play();
        else
            linea.play();
    }
    
    private HashMap<String, String[]> cargarMapa() {
        String file = new File("").getAbsolutePath();
        file = Paths.get(file, "src", "espol", "edu", "ec", "recursos", "traducciones.txt").toString();
        HashMap<String, String[]> mapa = new HashMap<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line;
            while((line=bf.readLine()) != null){
                String key = line.charAt(0)+"";
                line = line.substring(2, line.length());
                String[] data = line.split(" ");
                mapa.put(key, data);
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return mapa;
    }
    
    public void reiniciar() {
        recorrer(root);
    }
   
    private void recorrer(Node node) {
        if(node == null){
        
        }else {
            node.setFill(Color.WHITE); 
            recorrer(node.getLeft());
            recorrer(node.getRight());
        }
    }
}
