/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ennuikibun
 */
public class Min_Max {

    ArrayList<Integer>[] totalPosibilidades;
    Queue<Nodo> arbol;
    int manzanasDisp;
    ArrayList<Integer> posManzanas;
    ArrayList<Nodo> soluciones;
    
    public Min_Max(){
       totalPosibilidades =  (ArrayList<Integer>[])new ArrayList<?>[36];
        
       for(int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = new ArrayList<>();
        }
       arbol = new LinkedList<>();
       manzanasDisp = 0;
       posManzanas = new ArrayList<>();
       soluciones = new ArrayList<>();
    }

    public Boolean casillaOcupada(int pos, int maquina, int oponente) {
        return pos!=maquina && pos!=oponente;
    }
    
    //Calcula siguiente moviento a partir de las soluciones hojas
    public int calcMINIMAX(){
        int res = 0;
        return res; 
    }
    
    public void miniMax(int maquina, int jugador){
        ArrayList<Integer> posibles;
            if(arbol.element().getTurno()%2==0){
                posibles = posibilidades(maquina);
                for(int i=0; i<posibles.size(); i++){
                    
                    if(casillaOcupada(posibles.get(i),maquina, jugador)){  //si no está ocupada
                       int estado = 0;
                       int manz1 = arbol.element().getManzanas();
                       if(posManzanas.contains(posibles.get(i))){
                           posManzanas.indexOf(posibles.get(i));
                         manz1--;
                         estado = 1;
                      }    
                      	Nodo aux = new Nodo(arbol.element(), posibles.get(i), jugador,estado, manz1,1);
                        arbol.add(aux);
                   }       
                }
                arbol.remove();
                if(arbol.element().getManzanas()>0)
                	miniMax(arbol.element().posMaquina, jugador); 
                else{
                    soluciones.add(arbol.element());
                    arbol.remove();
                    miniMax(arbol.element().posMaquina, jugador); 
                }
            }
            else if(arbol.element().getTurno()%2!=0){
                posibles = posibilidades(jugador);
                for(int i=0; i<posibles.size(); i++){
                    if(casillaOcupada(posibles.get(i),maquina, jugador)){  //si no está ocupada
                       int estado = 0;
                       int manz1 = arbol.element().getManzanas();
                       if(posManzanas.contains(posibles.get(i))){
                            estado = -1;
                            manz1--; 
                       }    
                        Nodo aux = new Nodo(arbol.element(), maquina, posibles.get(i), estado,manz1, 2);
                        arbol.add(aux);
                   }       
                }
                arbol.remove();
              	if(arbol.element().getManzanas()>0)
                	miniMax(maquina,arbol.element().posJugador); 
               else{
                    soluciones.add(arbol.element());
                    arbol.remove();
                    miniMax(maquina,arbol.element().posJugador); 
                }
            }
    }
    
   public int crearRaiz(int maquina, int jugador, int manz){
        Nodo raiz = new Nodo(null, maquina, jugador, 0,manzanasDisp, 0);
        arbol.add(raiz);
        miniMax(maquina,jugador);
       int resultado = calcMINIMAX();
        arbol.clear();
        return resultado;
    }
    
    //Método que calcula las posibilidades de movimiento de una casilla específica en el tablero.
    //Se utiliza una sola vez en el método "calcularTotalPosibilidades"
    public ArrayList<Integer> posibilidades(int pos) {
        int x = pos/6;
        int y = pos-6*x;
        int aux = 0;
        
        ArrayList<Integer> res;
        res = new ArrayList<>();
        
        //Movimientos 2 arriba 1 izq o 1 dere
        if ((x-2) >= 0 && (x-2) <= 5) { 
            if ((y+1) >= 0 && (y+1) <= 5 ) {          
                aux = (y+1)+6*(x-2);
                res.add(aux);        
            }
            else if ((y-1) >= 0 && (y-1) <= 5) {
               aux = (y-1)+6*(x-2);
               res.add(aux);
            }
        }
        //movimiento 2 abajo y 1izq o 1 der
        else if ((x+2) >= 0 && (x+2) <= 5) {      
            if ((y+1) >= 0 && (y+1) <= 5 ) {       
                aux = (y+1)+6*(x+2);
                res.add(aux);             
            }
            else if ((y-1) >= 0 && (y-1) <= 5) {
                aux = (y-1)+6*(x+2);
                res.add(aux);
            }
        }
        //Movimiento 1 arriba y 2 derecha o 2 izq 
        else if ((x-1) >= 0 && (x-1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) {
                aux = (y-2)+6*(x-1);
                res.add(aux);
            }
            else if ((y+2)>= 0 && (y+2) <= 5) {  
                aux = (y+2)+6*(x-1);
                res.add(aux);
            }
        }
        //Movimiento 1 abajo y 2 der o 2 izq
        else if ((x+1) >= 0 && (x+1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) { 
                aux = (y-2)+6*(x+1);
                res.add(aux);   
            }
            else if ((y+2) >= 0 && (y+2) <= 5) {
                aux = (y+2)+6*(x+1);
                res.add(aux);
            }
        }
        return res;
    }
    
    //Asigna todas las posibilidades que existen para cada una de las casillas dentro del tablero
    //Se calcula una sola vez.
    public void calcularTotalPosibilidades(){
        for (int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = posibilidades(i);
        }  
    }
    
     public ArrayList<Integer> getMovimientos(int pos){
        return totalPosibilidades[pos];
    }

    public void setManzanasDisp(int manzanasDisp) {
        this.manzanasDisp = manzanasDisp;
    }
    
    public void setPosManzanas(ArrayList<Integer> posManz) {
        this.posManzanas = posManz;
    }

}
