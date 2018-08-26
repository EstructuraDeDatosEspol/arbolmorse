/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.tda.Const;
import espol.edu.ec.tda.TreeMorse;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        TreeMorse tm = new TreeMorse();
        
        Text text = new Text("Ábol de Códigos Morse");
        text.setFont(Font.font(text.getFont().getFamily(), FontWeight.BOLD, 24));  
        text.setFill(Color.BLUE);
        TextField input = new TextField();
        Button btn = new Button(">");
        Button reiniciar = new Button("@");
        HBox box = new HBox(10, new Text("Texto"),input, btn, reiniciar);
        box.setAlignment(Pos.CENTER); 
        VBox v = new VBox(5, text, box, tm);
        v.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY))); 
        v.setAlignment(Pos.CENTER); 
        stage.setScene(new Scene(v, Const.MAX_WIDTH, Const.MAX_HEIGHT));
         
        btn.setOnAction(e-> tm.reproducir(input.getText()));
        
        reiniciar.setOnAction(e ->{
            tm.reiniciar();
            input.setText(""); 
        }); 
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
    
}
