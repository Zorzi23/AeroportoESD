package listas_lineares;

public class FilaNodo<T> extends Fila<Nodo<T>> {
    
    //#region Atributos/Constantes

    //#endregion

    //#region Constructor

    public FilaNodo(int iCapacidade) {
        super(iCapacidade);
    }

    //#endregion

    //#region Getters/Setters

    public Nodo<T>[] getNodos() {
        return (Nodo<T>[]) super.getElementos();
    }

    //#endregion

    //#region Métodos Publicos

    public void adicionar(T xDado) {
        this.enfileirar(new Nodo<T>(xDado));
    }

    public void remover() {
        this.desenfileirar();
    }

    public void enfileirar(Nodo<T> xNodo) {
        super.enfileirar(xNodo);
    }

    public Nodo<T> desenfileirar() {
        Nodo<T> oNodoDesenfileirado = (Nodo<T>) super.desenfileirar();
        return oNodoDesenfileirado;
    }

    //#endregion

    //#region ToString

    @Override
    public String toString() {
        StringBuilder oString = new StringBuilder();
        oString.append("[");

        Nodo<T>[] aElementos = this.getNodos();

        for (int iElemento = 0; iElemento < this.getTamanho(); iElemento++) {
            Nodo<T> oNodoAtual = (Nodo<T>) aElementos[(this.iInicio + iElemento) % this.getElementos().length];
            oString.append(oNodoAtual.getDado());
            if (iElemento < this.getTamanho() - 1) {
                oString.append(", ");
            }
        }
        oString.append("]");
        return oString.toString();
    }

    //#endregion
}
