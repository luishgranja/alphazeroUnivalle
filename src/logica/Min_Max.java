/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.*;
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

    public Boolean validarCasillaMaquina(int pos, int maquina, int oponente, Nodo padre) {
      if(padre.getPadre()!=null){
          if(padre.getPadre().getPadre()!=null){
              return pos!=maquina && pos!=oponente && padre.getPadre().getPadre().getPosMaquina()!=pos;
          }else
              return pos!=maquina && pos!=oponente;
      }else
          return pos!=maquina && pos!=oponente;
    }
    
    public Boolean validarCasillaOponente(int pos, int maquina, int oponente, Nodo padre) {
      if(padre.getPadre()!=null){
          if(padre.getPadre().getPadre()!=null){
              return pos!=maquina && pos!=oponente && padre.getPadre().getPadre().getPosJugador()!=pos;
          }else
              return pos!=maquina && pos!=oponente;
      }else
          return pos!=maquina && pos!=oponente;
    }    
    
    //Calcula siguiente moviento a partir de las soluciones hojas
    public int calcMINIMAX(){
        int res = 0;
        int mayor=soluciones.get(0).getProfundidad();
        for(int i=1;i<soluciones.size();i++){
            if(soluciones.get(i).getProfundidad()>mayor)
                mayor=soluciones.get(i).getProfundidad();
        }
        ArrayList<Nodo>aux = new ArrayList<>();
        for(int i=0;i<soluciones.size();i++){
            if(soluciones.get(i).getProfundidad()==mayor)
                aux.add(soluciones.get(i));
        }
        ArrayList<Nodo>hrnos = new ArrayList<>();
        while(aux.size()>0){
            hrnos.clear();
            Nodo aux2 = aux.get(0);
            aux.remove(0);
            for(int i=0;i<aux.size();i++){
                if(aux.get(i).getPadre()==aux2.getPadre()){
                    hrnos.add(aux.get(i));
                    aux.remove(i);
                    i--;
                }               
            }
            for (Nodo hrno : hrnos) {
                
            }
        }
        
        
        
        return res; 
    }
    
    public void miniMax(int maquina, int jugador){
        int maq = maquina;
        int jug = jugador;
        while(!arbol.isEmpty()){
            Nodo nodo = arbol.peek();
          	maq = nodo.getPosMaquina();
          	jug = nodo.getPosJugador();
            if(nodo.getManzanas()>0 && nodo.getProfundidad()<=8){
                ArrayList<Integer> posibles;          
                if(nodo.getTurno()%2==0){
                     posibles = getMovimientos(maq);
                     for(int i=0; i<posibles.size(); i++){  
                         if(validarCasillaMaquina(posibles.get(i),maq, jug, nodo)){  //si no está ocupada
                            int estado = 0;
                            int manz1 = nodo.getManzanas();
                            if(posManzanas.contains(posibles.get(i))){ 
                                manz1--;
                                estado = 1;
                           }    
                           Nodo aux = new Nodo(nodo, posibles.get(i), jug, estado, manz1,1,nodo.getProfundidad()+1,nodo.getUtilidad()+1);
                           arbol.add(aux);
                        }       
                     } 
                     arbol.poll();
                 }
                 else if(nodo.getTurno()%2!=0){
                     posibles = getMovimientos(jug);
                     for(int i=0; i<posibles.size(); i++){
                         if(validarCasillaOponente(posibles.get(i),maq, jug, nodo)){  //si no está ocupada
                            int estado = 0;
                            int manz1 = nodo.getManzanas();
                            if(posManzanas.contains(posibles.get(i))){
                                 estado = -1;
                                 manz1--; 
                            }    
                             Nodo aux = new Nodo(nodo, maq, posibles.get(i), estado, manz1, 2, nodo.getProfundidad()+1,nodo.getUtilidad()-1);
                             arbol.add(aux);
                        }       
                     }
                    arbol.poll(); 
                 }  
            }
            else{
                if(nodo.getProfundidad()<8){
                    if(nodo.getTurno()==1)
                        nodo.setUtilidad(1);
                    else
                        nodo.setUtilidad(-1);
                }
                soluciones.add(nodo);
                arbol.poll();
                System.out.println("agregó solución");
              	if(nodo.getTurno()==2){
                  int pj = nodo.getPosJugador();
                  System.out.println("La posición del jugador es: "+pj);
                }else{
                  int pm = nodo.getPosMaquina();
                  System.out.println("La posición de la maquina es es: "+pm);
                }
            }            
        }
        System.out.println("Tam Soluciones "+soluciones.size());
    }
    
   public int crearRaiz(int maquina, int jugador, int manz){
        Nodo raiz = new Nodo(null, maquina, jugador, 0,manzanasDisp, 0,0,0);
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
            if ((y-1) >= 0 && (y-1) <= 5) {
               aux = (y-1)+6*(x-2);
               res.add(aux);
            }
        }
        //movimiento 2 abajo y 1izq o 1 der
        if ((x+2) >= 0 && (x+2) <= 5) {      
            if ((y+1) >= 0 && (y+1) <= 5 ) {       
                aux = (y+1)+6*(x+2);
                res.add(aux);             
            }
            if ((y-1) >= 0 && (y-1) <= 5) {
                aux = (y-1)+6*(x+2);
                res.add(aux);
            }
        }
        //Movimiento 1 arriba y 2 derecha o 2 izq 
        if ((x-1) >= 0 && (x-1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) {
                aux = (y-2)+6*(x-1);
                res.add(aux);
            }
            if ((y+2)>= 0 && (y+2) <= 5) {  
                aux = (y+2)+6*(x-1);
                res.add(aux);
            }
        }
        //Movimiento 1 abajo y 2 der o 2 izq
       if ((x+1) >= 0 && (x+1) <= 5) {
            if ((y-2) >= 0 && (y-2) <= 5 ) { 
                aux = (y-2)+6*(x+1);
                res.add(aux);   
            }
            if ((y+2) >= 0 && (y+2) <= 5) {
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
