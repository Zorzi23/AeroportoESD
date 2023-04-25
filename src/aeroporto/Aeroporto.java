package aeroporto;

import listas_lineares.ListaEncadeada;

public class Aeroporto {
    
    //#region Atributos/Constantes

    private String sNome;

    private ListaEncadeada<Pista> aPistas; 

    //#endregion

    //#region Constructor 

    public Aeroporto(String sNome, int iQuantidadePistas) {
        this.setNome(sNome);
        this.setPistas(Aeroporto.criaPistas(iQuantidadePistas));
    }

    //#endregion

    //#region Getters/Setters
    
    public void setNome(String sNome) {
        this.sNome = sNome;
    }

    public String getNome() {
        return this.sNome;
    }

    public void setPistas(ListaEncadeada<Pista> aPistas) {
        this.aPistas = aPistas;
    } 
    
    public ListaEncadeada<Pista> getPistas() {
        return this.aPistas;
    }

    public boolean isFilasAterrisagemVazias() {

        for(Pista oPista: this.getPistas()) {
            if(oPista.getFilaAterrisagem().isVazia()) {
                return true;
            }
        }

        return false;
    }

    public boolean isFilasDecolagemVazias() {

        for(Pista oPista: this.getPistas()) {
            if(oPista.getFilaDecolagem().isVazia()) {
                return true;
            }
        }

        return false;
    }

    //#endregion
    
    //#region Métodos Publicos

    //#endregion

    //#region

    public static ListaEncadeada<Pista> criaPistas(int iQuantidadePistas) {
        
        ListaEncadeada<Pista> aListaPistas = new ListaEncadeada<Pista>(); 

        for(int iPista = 1; iPista <= iQuantidadePistas; iPista++) {
            aListaPistas.adicionar(new Pista("Pista " + iPista));
        }

        return aListaPistas;
    }

    //#endregion

    //#region ToString

    //#endregion
}
