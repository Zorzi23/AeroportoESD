package aeroporto;

import java.util.Arrays;
import java.util.Comparator;

import listas_lineares.Fila;

public class FilaDecolagem extends Fila<Aeronave> {
    
    //#region Atributos/Constantes
    //#endregion

    //#region Constructor

    public FilaDecolagem(int iCapacidade) {
        super(iCapacidade);
    }

    public void adicionarAeronave(Aeronave oAeronave) {
        super.enfileirar(oAeronave.setTipoOperacao(TipoOperacao.DECOLAGEM));
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

    //#region Métodos Publicos

    public FilaDecolagem ordenarPrioridade() {
        
        Aeronave[] aElementos = new Aeronave[this.getTamanho()];

        FilaDecolagem aFilaDecolagem = new FilaDecolagem(this.getCapacidade());

        for(int iAeronave = 0; iAeronave < this.getTamanho(); iAeronave++) {
            aElementos[iAeronave] = aFilaDecolagem.desenfileirar();
        }

        Arrays.sort(aElementos, new Comparator<Aeronave>() { // ordena o array com base na prioridade
            @Override
            public int compare(Aeronave oAeronave1, Aeronave oAeronave2) {

                if (oAeronave1 == null || oAeronave2 == null) {
                    return 0;
                }

                return oAeronave1.getPrioridade() - oAeronave2.getPrioridade();
            }
        });

        for(int iAeronave = 0; iAeronave < this.getTamanho(); iAeronave++) {
            aFilaDecolagem.enfileirar(aElementos[iAeronave]);
        }

        return aFilaDecolagem;
    }

    //#endregion

}
