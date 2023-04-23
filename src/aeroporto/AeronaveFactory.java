package aeroporto;

import java.util.Random;

import listas_lineares.ListaEncadeada;

public class AeronaveFactory {

    //#region Atributos/Constantes

    private int iIdAtual = 0;

    //#endregion

    //#region Constructor

    public Aeronave criarAeronave(String sCompanhia, TipoOperacao eTipoOperacao, int iTempoEspera) {
        Aeronave oAeronave = new Aeronave(this.getIdAtual(), sCompanhia, eTipoOperacao, iTempoEspera);
        this.setProximoId();
        return oAeronave;
    }

    public ListaEncadeada<Aeronave> criarAeronaves(int iQuantidade) {

        ListaEncadeada<Aeronave> oAeronaves = new ListaEncadeada<Aeronave>();

        String[] aCompanhias = {"GOL", "AZUL", "LATAM", "AVIANCA", "TAM"};

        TipoOperacao[] aTiposOperacao = {TipoOperacao.ATERRISAGEM, TipoOperacao.DECOLAGEM, TipoOperacao.ESPERA};

        Random oRandom = new Random();

        for(int iAeronave = 0; iAeronave < iQuantidade; iAeronave++) {
            String sCompanhia = aCompanhias[oRandom.nextInt(aCompanhias.length)];
            TipoOperacao eTipoOperacao = aTiposOperacao[oRandom.nextInt(aTiposOperacao.length)];
            int iTempoEspera = oRandom.nextInt(21);

            Aeronave oAeronave = this.criarAeronave(sCompanhia, eTipoOperacao, iTempoEspera);
            oAeronaves.adicionar(oAeronave);
        }

        return oAeronaves;
    }

    //#endregion

    //#region Getters/Setters

    public void setProximoId() {
        this.iIdAtual++;
    }

    public int getIdAtual() {
        return this.iIdAtual;
    }

    //#endregion

}
