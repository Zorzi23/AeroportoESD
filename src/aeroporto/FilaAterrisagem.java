package aeroporto;

import java.util.Arrays;
import java.util.Comparator;

import listas_lineares.Fila;

public class FilaAterrisagem extends Fila<Aeronave> {
    
    //#region Atributos/Constantes
    //#endregion

    //#region Constructor

    public FilaAterrisagem(int iCapacidade) {
        super(iCapacidade);
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

    //#region Métodos Públicos

    public void adicionarAeronave(Aeronave oAeronave) {
        super.enfileirar(oAeronave.setTipoOperacao(TipoOperacao.ATERRISAGEM));
    }
    
    public void removerAeronave() {
        super.desenfileirar();
    }

    public FilaAterrisagem ordenarPrioridade() {

        Aeronave[] aElementos = this.getAeronaves();
    
        Arrays.sort(aElementos, new Comparator<Aeronave>() {
            public int compare(Aeronave oAeronave1, Aeronave oAeronave2) {
                return oAeronave2.getCombustivel() - oAeronave1.getCombustivel();
            }
        });
    
        FilaAterrisagem aFilaAterrisagem = new FilaAterrisagem(this.getCapacidade());
    
        for(int iAeronave = 0; iAeronave < this.getTamanho(); iAeronave++) {
            aFilaAterrisagem.enfileirar(aElementos[iAeronave]);
        }
    
        return aFilaAterrisagem;
    }

    //#endregion
}
