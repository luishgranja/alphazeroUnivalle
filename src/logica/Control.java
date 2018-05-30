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
    int caballoMaquina;
    int caballoOponente;
    ArrayList<Integer> manzanas;
    int cantidadManzanas;
    
    public Control(){
        
        minimax = new Min_Max();
        caballoMaquina = 0;
        caballoOponente = 0;
        manzanas = new ArrayList<>();
        minimax.calcularTotalPosibilidades();
        cantidadManzanas = 0;
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
        minimax.setPosManzanas(manzanas);
        next = minimax.crearRaiz(caballoMaquina, caballoOponente, manzanas.size());
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

    public Min_Max getMinimax() {
        return minimax;
    }

    public int getCaballoMaquina() {
        return caballoMaquina;
    }

    public int getCaballoOponente() {
        return caballoOponente;
    }

    public ArrayList<Integer> getManzanas() {
        return manzanas;
    }

    public int getCantidadManzanas() {
        return cantidadManzanas;
    }

    public void setCantidadManzanas(int cantidadManzanas) {
        this.cantidadManzanas = cantidadManzanas;
    }
   
    
    
}
