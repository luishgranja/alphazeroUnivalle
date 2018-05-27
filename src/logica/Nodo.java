
package logica;

public class Nodo {
        Nodo padre;
        int posMaquina;
        int posJugador;
        //Variable para saber quien va ganando o si van empatados
        //-1 si la maquina va perdiendo , 1 si va ganando y 0 si estan empatados
        int estado;
        int manzanas;
        int turno;
        int profundidad;
        int utilidad;
  
public Nodo(){
      padre = null;
      posMaquina = 0;
      posJugador = 0;
      estado = 0;
      turno = 0;
      manzanas = 0;
      profundidad = 0;
      utilidad = 0;
     }
  
    public Nodo( Nodo pPadre, int pPosMaquina, int pPosJugador, int pEstado, int pManzanas, int pTurno, int pProfundidad, int pUtilidad){
      padre = pPadre;
      posMaquina = pPosMaquina;
      posJugador = pPosJugador;
      estado = pEstado;
      manzanas = pManzanas; 
      turno = pTurno;
      profundidad = pProfundidad;
      utilidad = pUtilidad;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void setPosMaquina(int posMaquina) {
        this.posMaquina = posMaquina;
    }

    public void setPosJugador(int posJugador) {
        this.posJugador = posJugador;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setManzanas(int manzanas) {
        this.manzanas = manzanas;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Nodo getPadre() {
        return padre;
    }

    public int getPosMaquina() {
        return posMaquina;
    }

    public int getPosJugador() {
        return posJugador;
    }

    public int getEstado() {
        return estado;
    }

    public int getManzanas() {
        return manzanas;
    }

    public int getTurno() {
        return turno;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
    
    
    
}
