package listas_lineares;

public class Nodo<T> {
    
    //#region Atributos/Constantes

    private T xDado;

    private Nodo oProximo;
    
    private Nodo oAnterior;

    //#endregion
    
    //#region Constructor

    public Nodo(T xDado) {
        this.setDado(xDado);
    }

    //#endregion

    //#region Getters/Setters

    public void setDado(T xDado) {
        this.xDado = xDado;
    }

    public void setProximo(Nodo oProximo) {
        this.oProximo = oProximo;
    }

    public void setAnterior(Nodo oAnterior) {
        this.oAnterior = oAnterior;
    }

    public Nodo getNodo() {
        return this.oProximo;
    }

    public T getDado() {
        return this.xDado;
    }

    public Nodo getProximo() {
        return this.oProximo;
    }

    public Nodo getAnterior() {
        return this.oAnterior;
    }

    //#endregion
} 