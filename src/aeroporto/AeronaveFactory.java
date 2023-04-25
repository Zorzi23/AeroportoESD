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

    public ListaEncadeada<Aeronave> criarAeronavesRandom(int iQuantidade) {

        ListaEncadeada<Aeronave> oAeronaves = new ListaEncadeada<Aeronave>();

        String[] aCompanhias = {"GOL", "AZUL", "LATAM", "AVIANCA", "TAM"};

        TipoOperacao[] aTiposOperacao = {TipoOperacao.ATERRISAGEM, TipoOperacao.DECOLAGEM, TipoOperacao.ESPERA};

        Random oRandom = new Random();

        for(int iAeronave = 0; iAeronave < iQuantidade; iAeronave++) {
            String sCompanhia = aCompanhias[oRandom.nextInt(aCompanhias.length)];
            TipoOperacao eTipoOperacao = aTiposOperacao[oRandom.nextInt(aTiposOperacao.length)];
            int iTempoEspera = oRandom.nextInt(21);
            int iCombustivel = oRandom.nextInt(101);

            Aeronave oAeronave = this.criarAeronave(sCompanhia, eTipoOperacao, iTempoEspera);
            oAeronave.setCombustivel(iCombustivel);

            if(oAeronave.getCombustivel() < 20 || oAeronave.getTempoEspera() > 20) {
                oAeronave.setPrioridade(Aeronave.PRIORIDADE_ALTA);
            }
            else if(oAeronave.getCombustivel() < 40 || oAeronave.getTempoEspera() > 10) {
                oAeronave.setPrioridade(Aeronave.PRIORIDADE_MEDIA);
            }

            oAeronaves.adicionar(oAeronave);
        }

        return oAeronaves;
    }

    public ListaEncadeada<Aeronave> criarAeronavesTipoOperacao(int iQuantidade, TipoOperacao eTipoOperacao) {

        ListaEncadeada<Aeronave> oAeronaves = new ListaEncadeada<Aeronave>();

        String[] aCompanhias = {"GOL", "AZUL", "LATAM", "AVIANCA", "TAM"};

        Random oRandom = new Random();

        for(int iAeronave = 0; iAeronave < iQuantidade; iAeronave++) {
            String sCompanhia = aCompanhias[oRandom.nextInt(aCompanhias.length)];
            int iTempoEspera  = oRandom.nextInt(21);
            int iCombustivel  = eTipoOperacao == TipoOperacao.DECOLAGEM ? 100 : oRandom.nextInt(101);

            Aeronave oAeronave = this.criarAeronave(sCompanhia, eTipoOperacao, iTempoEspera);
            oAeronave.setCombustivel(iCombustivel);

            if(oAeronave.getCombustivel() < 20 || oAeronave.getTempoEspera() > 20) {
                oAeronave.setPrioridade(Aeronave.PRIORIDADE_ALTA);
            }
            else if(oAeronave.getCombustivel() < 40 || oAeronave.getTempoEspera() > 10) {
                oAeronave.setPrioridade(Aeronave.PRIORIDADE_MEDIA);
            }

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
