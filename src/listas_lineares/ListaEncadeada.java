package listas_lineares;

import java.util.Arrays;
import java.util.Iterator;

public class ListaEncadeada<T> implements Iterable<T> {
    
    //#region Atributos/Constantes

    private Nodo<T> oInicio;

    private Nodo<T> oFinal;

    private int iTamanho = 0;

    //#region Iterator

    private class ListaEncadeadaIterator implements Iterator<T> {

        private Nodo<T> oAtual;

        public ListaEncadeadaIterator() {
            oAtual = oInicio;
        }

        @Override
        public boolean hasNext() {
            return oAtual != null;
        }

        @Override
        public T next() {
            T xDado = oAtual.getDado();
            oAtual = oAtual.getProximo();
            return xDado;
        }
    }

    //#endregion

    //#endregion

    //#region Constructor
    //#endregion

     //#region Getters/Setters

     public void setInicio(Nodo<T> oInicio) {
        this.oInicio = oInicio;
    }
    
    public Nodo<T> getInicio() {
        return this.oInicio;
    }

    public void setFinal(Nodo<T> oFinal) {
        this.oFinal = oFinal;
    }

    public Nodo<T> getFinal() {
        return this.oFinal;
    }

    public int getTamanho() {
        return this.iTamanho;
    }

    public boolean isVazia() {
        return this.oInicio == null && this.getTamanho() == 0;
    }
    
    //#endregion

    //#region Métodos Públicos

    public void adicionar(T xDado) {
        
        if(this.isVazia()) {
           this.setInicio(new Nodo<T>(xDado));
           this.iTamanho++;
           return;
        }

        Nodo<T> oAtual = this.getInicio();
        
        while(oAtual.getProximo() != null) {
            oAtual = oAtual.getProximo();
        }

        oAtual.setProximo(new Nodo<T>(xDado));

        this.iTamanho++;
    }

    public void adicionarArrayDados(T[] aDados) {
        for(T xDado : aDados) {
            this.adicionar(xDado);
        }
    }

    public void remover(T xDado) {

        if(this.isVazia()) {
            return;
        }

        if(this.getInicio().getDado() == xDado) {
            this.setInicio(this.getInicio().getProximo());;
            this.iTamanho--;
            return;
        }
        
        Nodo<T> oAtual = this.getInicio();

        while(oAtual.getProximo() != null && oAtual.getProximo().getDado() != xDado) {
            oAtual = oAtual.getProximo();
        }

        if(oAtual.getProximo() != null) {
            oAtual.setProximo(oAtual.getProximo().getProximo());
            this.iTamanho--;
        }
    } 

    @Override
    public Iterator<T> iterator() {
        return new ListaEncadeadaIterator();
    }

    //#endregion

    //#region ToString

    @Override
    public String toString() {

        if(this.isVazia()) {
            return "[]";
        }
    
        T[] aElementos = (T[]) new Object[iTamanho];
        
        Nodo<T> oAtual = oInicio;

        int iNodo = 0;
        
        while (oAtual != null) {
            aElementos[iNodo++] = oAtual.getDado();
            oAtual = oAtual.getProximo();
        }
        return Arrays.toString(aElementos);
    }

    //#endregion
}
