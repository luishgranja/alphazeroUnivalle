/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.*;
/**
 *
 * @author ennuikibun
 */
public class Min_Max {

    ArrayList<Integer>[] totalPosibilidades;
    ArrayList<Nodo> arbol;
    ArrayList<Integer> posManzanas;
    ArrayList<Nodo> soluciones;
    int resultado;

    public Min_Max() {
        totalPosibilidades =  (ArrayList<Integer>[])new ArrayList<?>[36];

        for(int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = new ArrayList<>();
        }

        arbol = new ArrayList<>();
        posManzanas = new ArrayList<>();
        soluciones = new ArrayList<>();
        resultado=0;
    }

    public Boolean validarCasillaMaquina(int pos, int maquina, int oponente, Nodo padre) {
        if(padre.getPadre()!=-1) {
            if(arbol.get(padre.getPadre()).getPadre()!=-1) {
                return pos!=maquina && pos!=oponente && arbol.get(arbol.get(padre.getPadre()).getPadre()).getPosMaquina()!=pos;
            } else
                return pos!=maquina && pos!=oponente;
        } else
            return pos!=maquina && pos!=oponente;
    }

    public Boolean validarCasillaOponente(int pos, int maquina, int oponente, Nodo padre) {
        if(padre.getPadre()!=-1) {
            if(arbol.get(padre.getPadre()).getPadre()!=-1) {
                return pos!=maquina && pos!=oponente && arbol.get(arbol.get(padre.getPadre()).getPadre()).getPosJugador()!=pos;
            } else
                return pos!=maquina && pos!=oponente;
        } else
            return pos!=maquina && pos!=oponente;
    }

    public void miniMax(Nodo miNodo, int pos) {
        if(miNodo.getManzanas()==0 ||miNodo.getProfundidad()>=8 ){
           if(miNodo.getProfundidad()>=8)
               miNodo.setEstado(true);
            switch (miNodo.getTurno()) {
                case 0:
                    resultado=arbol.get(miNodo.getSucesor()).getPosMaquina(); //asigna el siguiente movimiento que debe hacer la máquina.
                    break;
                case 1:
                    if(miNodo.getEstado()==false)
                        miNodo.setUtilidad(10-miNodo.getProfundidad());
                    if(arbol.get(miNodo.getPadre()).getEstado()==false){
                        arbol.get(miNodo.getPadre()).setUtilidad(miNodo.getUtilidad());
                        arbol.get(miNodo.getPadre()).setSucesor(pos);
                        arbol.get(miNodo.getPadre()).setEstado(true);
                    }else{
                        if(miNodo.getPadre()==0){
                            if(miNodo.getUtilidad()>arbol.get(miNodo.getPadre()).getUtilidad()){
                                arbol.get(miNodo.getPadre()).setUtilidad(miNodo.getUtilidad());
                                arbol.get(miNodo.getPadre()).setSucesor(pos);
                            }
                        }else{
                            if(miNodo.getUtilidad()<arbol.get(miNodo.getPadre()).getUtilidad()){
                                arbol.get(miNodo.getPadre()).setUtilidad(miNodo.getUtilidad());
                                arbol.get(miNodo.getPadre()).setSucesor(pos);
                            }
                        }
                    }  break;
                case 2:
                    if(miNodo.getEstado()==false)
                        miNodo.setUtilidad(-1);
                    if(arbol.get(miNodo.getPadre()).getEstado()==false){
                        arbol.get(miNodo.getPadre()).setUtilidad(miNodo.getUtilidad());
                        arbol.get(miNodo.getPadre()).setSucesor(pos);
                        arbol.get(miNodo.getPadre()).setEstado(true);
                    }else{
                        if(miNodo.getUtilidad()>arbol.get(miNodo.getPadre()).getUtilidad()){
                            arbol.get(miNodo.getPadre()).setUtilidad(miNodo.getUtilidad());
                            arbol.get(miNodo.getPadre()).setSucesor(pos);
                        }
                    }   break;
                default:
                    break;
            }
        }
        else{
            int maq = miNodo.getPosMaquina();
            int jug = miNodo.getPosJugador();
            ArrayList<Integer> posibles;
            int manz1 = miNodo.getManzanas();
            int man=0;
            if(miNodo.getTurno()%2==0){
                posibles = getMovimientos(maq);
                    for(int i=0; i<posibles.size(); i++){
                        if(validarCasillaMaquina(posibles.get(i),maq, jug, miNodo)) { //si no está ocupada
                            if(posManzanas.contains(posibles.get(i))) {
                                manz1--;
                                man=1;
                            }
                            Nodo aux = new Nodo(pos, posibles.get(i), jug,false, manz1,1,miNodo.getProfundidad()+1,miNodo.getManzanas()+man,0);
                            arbol.add(aux);
                            miniMax(aux,arbol.size()-1);       
                        }
                    }
                    miNodo.setManzanas(0);
                    miniMax(miNodo,pos);
            }
            else if(miNodo.getTurno()%2!=0) {
                    posibles = getMovimientos(jug);
                    for(int i=0; i<posibles.size(); i++) {
                        if(validarCasillaOponente(posibles.get(i),maq, jug, miNodo)) { //si no está ocupada
                            if(posManzanas.contains(posibles.get(i))) {
                                manz1--;
                                man=1;
                            }
                            Nodo aux = new Nodo(pos, maq, posibles.get(i), false, manz1, 2, miNodo.getProfundidad()+1,miNodo.getManzanas()-man,0);
                            arbol.add(aux);
                            miniMax(aux,arbol.size()-1);
                        }
                    }
                    miNodo.setManzanas(0);
                    miniMax(miNodo,pos);
            }
        }
    }

    public int crearRaiz(int maquina, int jugador, int manz) {
       arbol.clear();
        Nodo raiz = new Nodo(-1, maquina, jugador, false,manz, 0,0,0,0);
        arbol.add(raiz);
        miniMax(raiz,0);
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
    public void calcularTotalPosibilidades() {
        for (int i = 0; i < totalPosibilidades.length; i++) {
            totalPosibilidades[i] = posibilidades(i);
        }
    }

    public ArrayList<Integer> getMovimientos(int pos) {
        return totalPosibilidades[pos];
    }

    public void setPosManzanas(ArrayList<Integer> posManz) {
        this.posManzanas = posManz;
    }

}