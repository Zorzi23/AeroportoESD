package aeroporto;

import listas_lineares.Fila;

public class FilaEspera extends Fila<Aeronave> {
    
    //#region Atributos/Constantes
    //#endregion

    //#region Constructor

    public FilaEspera(int iCapacidade) {
        super(iCapacidade);
    }

    public void adicionarAeronave(Aeronave oAeronave) {
        super.enfileirar(oAeronave.setTipoOperacao(TipoOperacao.ESPERA));
    }

    public void removerAeronave(Aeronave oAeronave) {
        super.desenfileirar();
    }

    //#endregion

    //#region Getters/Setters
    
    public int getCapacidade() {
        return this.iCapacidade;
    }

    public void setCapacidade(int iCapacidade) {
        this.iCapacidade = iCapacidade;
    }

    public Aeronave[] getAeronaves() {
        return super.getElementos();
    }

    public Aeronave getAeronave(int iAeronave) {

        Aeronave[] aAeronaves = this.getAeronaves();

        return aAeronaves[iAeronave];
    }

    //#endregion

}
