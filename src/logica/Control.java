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
    
    Min_Max  minimax;
    ArrayList<Integer>[] totalPosibilidades;
    int caballoMaquina;
    int caballoOponente;
    ArrayList<Integer> manzanas;
    
    public Control(){
        totalPosibilidades =  (ArrayList<Integer>[])new ArrayList<?>[36];
        
        for(int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = new ArrayList<>();
        }
        
        minimax = new Min_Max();
        caballoMaquina = 0;
        caballoOponente = 0;
        manzanas = new ArrayList<>();
    }
    
    //Método que calcula las posibilidades de movimiento de una casilla específica en el tablero.
    //Se utiliza una sola vez en el método "calcularTotalPosibilidades"
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
            }
            else if ((y-1) >= 0 && (y-1) <= 5) {
                aux = (y-1)+6*(x-2);
                resultado.add(aux);
            }
        }
        //movimiento 2 abajo y 1izq o 1 der
        else if ((x+2) >= 0 && (x+2) <= 5) {      
            if ((y+1) >= 0 && (y+1) <= 5 ) {       
                aux = (y+1)+6*(x+2);
                resultado.add(aux);             
            }
            else if ((y-1) >= 0 && (y-1) <= 5) {
                aux = (y-1)+6*(x+2);
                resultado.add(aux);
            }
        }
        //Movimiento 1 arriba y 2 derecha o 2 izq 
        else if ((x-1) >= 0 && (x-1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) {
                aux = (y-2)+6*(x-1);
                resultado.add(aux);
            }
            else if ((y+2)>= 0 && (y+2) <= 5) {  
                aux = (y+2)+6*(x-1);
                resultado.add(aux);
            }
        }
        //Movimiento 1 abajo y 2 der o 2 izq
        else if ((x+1) >= 0 && (x+1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) { 
                aux = (y-2)+6*(x+1);
                resultado.add(aux);   
            }
            else if ((y+2) >= 0 && (y+2) <= 5) {
                aux = (y+2)+6*(x+1);
                resultado.add(aux);
            }
        }
        return resultado;
    }
    
    //Asigna todas las posibilidades que existen para cada una de las casillas dentro del tablero
    //Se calcula una sola vez.
    public void calcularTotalPosibilidades(){
        for (int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = posibilidades(i);
        }  
    }
        
    //Asigna aleatoriamente las posiciones de los items dentro del tablero.
    //Los dos primeros elementos son los caballos. El resto son las x manzanas.
    public int[] generarJuego(int cantidadItems){
        final int[] juego = new Random().ints(0, 36).distinct().limit((cantidadItems+2)).toArray();
        return juego;
    }

    //Valida si la casilla seleccionada está ocupada
    public Boolean casillaOcupada(int pos) {
        return pos!=caballoMaquina && pos!=caballoOponente;
    }
    
    public int moverMaquina(){
        int next = 0;
        minimax.miniMax(totalPosibilidades[caballoMaquina], totalPosibilidades[caballoOponente]);
        return next;
    }
    
    //Valida que el movimiento que desea hacer el jugador sea Válido.
    public Boolean validaMovOponente(int pos){
        return casillaOcupada(pos);
    }
    
    public void setCaballoMaquina(int caballoMaquina) {
        this.caballoMaquina = caballoMaquina;
    }

    public void setCaballoOponente(int caballoOponente) {
        this.caballoOponente = caballoOponente;
    }

    public void setManzanas(ArrayList<Integer> manzanas) {
        this.manzanas = manzanas;
    }
    
    
    
}
