
package logica;

public class Nodo {
        int nodoPadre;
        int posMaquina;
        int posJugador;
        //Variable para saber quien va ganando o si van empatados
        //-1 si la maquina va perdiendo , 1 si va ganando y 0 si estan empatados
        Boolean estado;
        int manzanas;
        int turno;
        int profundidad;
        int utilidad;
        int sucesor;
  
public Nodo(){
      nodoPadre = -1;
      posMaquina = 0;
      posJugador = 0;
      estado = false;
      turno = 0;
      manzanas = 0;
      profundidad = 0;
      utilidad = 0;
      sucesor=0;
     }
  
    public Nodo( int pPadre, int pPosMaquina, int pPosJugador, Boolean pEstado, int pManzanas, int pTurno, int pProfundidad, int pUtilidad, int posSucesor){
      nodoPadre = pPadre;
      posMaquina = pPosMaquina;
      posJugador = pPosJugador;
      estado = pEstado;
      manzanas = pManzanas; 
      turno = pTurno;
      profundidad = pProfundidad;
      utilidad = pUtilidad;
      sucesor = posSucesor;
    }

    public void setPadre(int padre) {
        this.nodoPadre = padre;
    }

    public void setPosMaquina(int posMaquina) {
        this.posMaquina = posMaquina;
    }

    public void setPosJugador(int posJugador) {
        this.posJugador = posJugador;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setManzanas(int manzanas) {
        this.manzanas = manzanas;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setSucesor(int sucesor) {
        this.sucesor = sucesor;
    }

    public int getPadre() {
        return nodoPadre;
    }

    public int getPosMaquina() {
        return posMaquina;
    }

    public int getPosJugador() {
        return posJugador;
    }

    public Boolean getEstado() {
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

    public int getSucesor() {
        return sucesor;
    }
    
    
    
}
