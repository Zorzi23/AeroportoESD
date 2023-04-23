package aeroporto;

import listas_lineares.ListaEncadeada;

public class ControllerAeroporto {
    
    //#region Atributos/Constantes

    private static ControllerAeroporto oInstancia;

    private Aeroporto oAeroporto;
    private AeronaveFactory oAeronaveFactory;
    
    //#endregion

    //#region Constructor 
    //#endregion

    //#region Getters/Setters
    
    public static ControllerAeroporto getInstance() {
        
        if(oInstancia == null) {
            return new ControllerAeroporto();
        }

        return oInstancia;
    }

    public void setAeroporto(Aeroporto oAeroporto) {
        this.oAeroporto = oAeroporto;
    }

    public Aeroporto getAeroporto() {
        return this.oAeroporto;
    }

    private void setAeronaveFactory(AeronaveFactory oAeronaveFactory) {
        this.oAeronaveFactory = oAeronaveFactory;
    }

    private AeronaveFactory getAeronaveFactory() {
        return this.oAeronaveFactory;
    }
    
    //#endregion
    
    //#region Métodos Publicos

    public Aeroporto gerenciar(Aeroporto oAeroporto) {
        
        this.setAeroporto(oAeroporto);
        this.setAeronaveFactory(new AeronaveFactory());

        ListaEncadeada<Aeronave> aAeronaves = this.getAeronaveFactory().criarAeronaves(50);
        this.adicionarAeronaves(aAeronaves);

        return oAeroporto;
    }

    public Aeroporto adicionarAeronaveAterrisagem(Aeronave oAeronave) {

        for(Pista oPista: this.getAeroporto().getPistas()) {

            if(oAeronave.getTipoOperacao() == TipoOperacao.ATERRISAGEM) {
                oPista.getFilaAterrisagem().adicionarAeronave(oAeronave);
            }

        }

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronaveDecolagem(Aeronave oAeronave) {

        for(Pista oPista: this.oAeroporto.getPistas()) {

            if(oAeronave.getTipoOperacao() == TipoOperacao.DECOLAGEM) {
                oPista.getFilaDecolagem().adicionarAeronave(oAeronave);
            }

        }

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronaves(ListaEncadeada<Aeronave> aAeronvaes) {

        for(Aeronave oAeronave: aAeronvaes) {
            this.adicionarAeronaveAterrisagem(oAeronave);
            this.adicionarAeronaveDecolagem(oAeronave);
        }

        return this.oAeroporto;
    }

    //#endregion
}
