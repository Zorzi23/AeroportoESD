package listas_lineares;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Fila<T> implements Iterable<T> {

    //#region Atributos/Constantes

    protected T[] xElementos;

    protected int iCapacidade = 0;

    protected int iTamanho;
    
    protected int iInicio = 0;

    protected int iFim = -1;

    //#endregion

    //#region Iterator

    @Override
    public Iterator<T> iterator() {
        return new FilaIterator();
    }

    private class FilaIterator implements Iterator<T> {

        private int iAtual = iInicio;

        @Override
        public boolean hasNext() {
            return iAtual < iTamanho;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T element = getElementos()[(iAtual++) % getCapacidade()];
            return element;
        }
    }

    //#endregion

    //#region Constructor

    public Fila(int iCapacidade) {

        if(iCapacidade > 0) {
            this.setCapacidade(iCapacidade);
        } 

        this.setElementos();
    }

    //#endregion

    //#region Getters/Setters

    public void setCapacidade(int iCapacidade) {
        this.iCapacidade = iCapacidade;
    }

    public int getCapacidade() {
        return this.iCapacidade;
    }

    public void setTamanho(int iTamanho) {
        this.iTamanho = iTamanho;
    }

    public int getTamanho() {
        return this.iTamanho;
    }

    protected void setElementos() {
        this.xElementos = this.criaArray(this.getCapacidade());
    }

    public T[] getElementos() {
        return this.xElementos;
    }

    public T getPrimeiro() {

        if(this.isVazia()) {
            return null;
        }


        return this.getElementos()[this.iInicio];
    }

    public boolean isVazia() {
        return this.getTamanho() == 0;
    }

    //#endregion

    //#region Métodos Públicos

    public void enfileirar(T xElemento) {
        
        if(this.getTamanho() == this.getElementos().length) {
            throw new IllegalStateException("Fila cheia.");
        }

        this.iFim++;

        if(this.iFim == this.getElementos().length) {
            this.iFim = 0;
        }

        this.getElementos()[this.iFim] = xElemento;

        this.iTamanho++;
    }

    public T desenfileirar() {

        if(this.isVazia()) {
            return null;
        }

        T xElementoRemovido = this.getElementos()[this.iInicio];

        this.iInicio++;

        if(this.iInicio == this.getElementos().length) {
            this.iInicio = 0;
        }
        
        this.iTamanho--;
        return xElementoRemovido;
    }

    //#endregion

    //#region Métodos Privados

    protected T[] criaArray(int iTamanho) {
        return (T[]) new Object[iTamanho];
    }

    //#endregion

    //#region ToString

    public String toString() {
        StringBuilder oString = new StringBuilder();
        oString.append("[ ");

        for (int iElemento = 0; iElemento < this.getTamanho(); iElemento++) {
            
            T oElemento = this.getElementos()[(this.iInicio + iElemento) % this.getElementos().length];

            oString.append("\n");
            oString.append(" { ");
            oString.append(oElemento.toString());
            oString.append(" } ");
            oString.append("\n");
            
            if (iElemento < this.getTamanho() - 1) {
                oString.append(", ");
            }
            
        }
        
        oString.append("]");
        oString.append("\n");
        return oString.toString();
    }

    //#endregion
}
