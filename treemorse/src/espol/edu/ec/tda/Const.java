/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author Kenny Camba
 */
public class Const {
    
    private Const(){
        //
    } 
    
    public static final Rectangle2D VISUAL_BOUNDS = Screen.getPrimary().getVisualBounds();
    public static final double MAX_WIDTH = VISUAL_BOUNDS.getWidth();
    public static final double MAX_HEIGHT = VISUAL_BOUNDS.getHeight();
    public static final String FONT_BOLD = "-fx-font-weight:bold";
    public static final double Y = VISUAL_BOUNDS.getMaxY();
    public static final double X = VISUAL_BOUNDS.getMaxX();
    
}
