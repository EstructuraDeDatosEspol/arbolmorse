/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * 
 */
public class Node extends StackPane{
    
    private final Circle content;
    private Node right;
    private Node left;
    private String data;
    private final Timeline timeline;
    private Text text;
    
    public Node(String data) {
        this.data = data;
        content = new Circle();
        right = left = null;
        timeline = new Timeline();
        view();
    }

    private void view() {
        text = new Text(data);
        text.setFont(Font.font(24)); 
        content.setRadius(20); 
        super.getChildren().addAll(content, text);
        super.setAlignment(Pos.CENTER); 
    }
    
    public void setFill(Color color){
        content.setFill(color); 
    }
    
    public void pass(Color color) {
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyValue kv = new KeyValue(content.fillProperty(), color);
        KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
    
     public void play(Color color) {
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyValue kv = new KeyValue(content.fillProperty(), color);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);
        timeline.play();
    }
    
    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        text.setText(data); 
        this.data = data;
    }
    
    public double getCenterX() {
        return content.getCenterX();
    }
    
    public double getCenterY() {
        return content.getCenterY();
    }
    public Circle getContent() {
        return content;
    }
}
