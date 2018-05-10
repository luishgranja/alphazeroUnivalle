/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author luis
 */
public class Control {
    
    ArrayList<Integer>[] totalPosibilidades;
    
    public Control(){
        
        totalPosibilidades =  (ArrayList<Integer>[])new ArrayList<?>[36];
        
        for(int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = new ArrayList<>();
        }
    }
    
    public ArrayList<Integer> posibilidades(int pos) {
        
        int x = pos/6;
        int y = pos-6*x;
        int aux = 0;
        
        ArrayList<Integer> resultado = new ArrayList<>();
        
        //Movimientos 2 arriba 1 izq o 1 dere
        if ((x-2) >= 0 && (x-2) <= 5) {
            
            if ((y+1) >= 0 && (y+1) <= 5 ) {
                
                aux = (y+1)+6*(x-2);
                resultado.add(aux);
                
            }else if ((y-1) >= 0 && (y-1) <= 5) {
                
                aux = (y-1)+6*(x-2);
                resultado.add(aux);
            }
            //movimiento 2abajo y 1izq o 1 der
        }else if ((x+2) >= 0 && (x+2) <= 5) {
            
            if ((y+1) >= 0 && (y+1) <= 5 ) {
                
                aux = (y+1)+6*(x+2);
                resultado.add(aux);
                
            }else if ((y-1) >= 0 && (y-1) <= 5) {
                
                aux = (y-1)+6*(x+2);
                resultado.add(aux);
                
            }
            //Movimiento 1 arriba y 2 derecha o 2 izq 
        }else if ((x-1) >= 0 && (x-1) <= 5) {
            
            if ((y-2) >= 0 && (y-2) <= 5 ) {
                
                aux = (y-2)+6*(x-1);
                resultado.add(aux);
                
                
            }else if ((y+2)>= 0 && (y+2) <= 5) {
                
                aux = (y+2)+6*(x-1);
                resultado.add(aux);
            }
            //Movimiento 1 abajo y 2 der o 2 izq
        }else if ((x+1) >= 0 && (x+1) <= 5) {
            
            if ((y-2) >= 0 && (y-2) <= 5 ) {
                
                aux = (y-2)+6*(x+1);
                resultado.add(aux);
                
            }else if ((y+2) >= 0 && (y+2) <= 5) {
                
                aux = (y+2)+6*(x+1);
                resultado.add(aux);
            }
        }
        
        return resultado;
    }
    
    public void calcularTotalPosibilidades(){
        
        for (int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = posibilidades(i);
        }
        
    }
    
    
    
    public int[] generarJuego(int cantidadItems){
        final int[] juego = new Random().ints(0, 36).distinct().limit((cantidadItems+2)).toArray();
        return juego;
    }
}
