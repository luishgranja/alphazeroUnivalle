/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Random;

/**
 *
 * @author luis
 */
public class Control {
    
    
    
    public int[] generarJuego(int cantidadItems){
        final int[] juego = new Random().ints(0, 36).distinct().limit((cantidadItems+2)).toArray();
        return juego;
    }
}
