package aeroporto;

import java.io.Console;
import java.util.Random;

import listas_lineares.Fila;
import listas_lineares.ListaEncadeada;

public class ControllerAeroporto {
    
    //#region Atributos/Constantes

    private static ControllerAeroporto oInstancia;

    private Aeroporto oAeroporto;
    private AeronaveFactory oAeronaveFactory;

    private int iQuantidadeAeronavesAterrisadas = 0;
    private int iQuantidadeAeronavesDecoladas = 0;
    private int iQuantidadeAeronavesAterrisadasEmergencia = 0;

    private ListaEncadeada<Aeronave> aAeronavesAterrisadas = new ListaEncadeada<Aeronave>();
    private ListaEncadeada<Aeronave> aAeronavesDecoladas = new ListaEncadeada<Aeronave>();
    
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
        
        this.beforeGerenciar(oAeroporto);
        
        System.out.println(this.oAeroporto.getPistas());
        System.out.println("\n");

        while(!this.oAeroporto.isFilasAterrisagemVazias() || !this.oAeroporto.isFilasDecolagemVazias()) {

            try {
                this.aterrisarAeronaves();
                this.decolarAeronaves();
                this.gerenciarCombustivel();
                this.gerenciarTempoEspera();
                this.gerenciarPrioridade();
                this.adicionarAeronavesRandom();
            } catch (IllegalStateException oEx) {
                System.out.println(oEx.getMessage());                
                break;
            }
        }
        
        System.out.println("\n");
        System.out.println("Aeronaves Aterrisadas: " + this.iQuantidadeAeronavesAterrisadas);
        System.out.println("Aeronaves Decoladas: " + this.iQuantidadeAeronavesDecoladas);
        System.out.println("Aeronaves Aterrisadas Emergencia: " + this.iQuantidadeAeronavesAterrisadasEmergencia);
        System.out.println("Tempo Médio Aterrisagem: " + this.calcularTempoEsperaAterrisagem());
        System.out.println("Tempo Médio Decolagem: " + this.calcularTempoEsperaDecolagem());

        return oAeroporto;
    }

    public Aeroporto gerenciarCombustivel() {

        for(Pista oPista: this.getAeroporto().getPistas()) {

            for(Aeronave oAeronave: oPista.getFilaAterrisagem()) {
                
                int iCombustivel = oAeronave.getCombustivel();

                oAeronave.setCombustivel(iCombustivel--);

                if(oAeronave.getCombustivel() == 0) {
                    throw new IllegalStateException(oAeronave.toString() + " Caiu.");
                }
            }
            
            for(Aeronave oAeronave: oPista.getFilaAterrisagem()) {
                
                int iCombustivel = oAeronave.getCombustivel();

                oAeronave.setCombustivel(iCombustivel--);

                if(oAeronave.getCombustivel() == 0) {
                    throw new IllegalStateException(oAeronave.toString() + " Caiu.");
                }
            }
        }

        return this.oAeroporto;
    }

    public Aeroporto gerenciarTempoEspera() {

        for(Pista oPista: this.getAeroporto().getPistas()) {

            for(Aeronave oAeronave: oPista.getFilaAterrisagem()) {
                
                int iTempoEspera = oAeronave.getTempoEspera();

                oAeronave.setTempoEspera(iTempoEspera++);
            }

            for(Aeronave oAeronave: oPista.getFilaDecolagem()) {

                int iTempoEspera = oAeronave.getTempoEspera();

                oAeronave.setTempoEspera(iTempoEspera++);
            }
        }

        return this.oAeroporto;
    }

    public Aeroporto gerenciarPrioridade() {

        for(Pista oPista: this.getAeroporto().getPistas()) {

            for(Aeronave oAeronave: oPista.getFilaAterrisagem()) {
                this.gerenciarPrioridadeAeronave(oAeronave);                
            }
            
            for(Aeronave oAeronave: oPista.getFilaDecolagem()) {
                this.gerenciarPrioridadeAeronave(oAeronave);                
            }
        }

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronaveAterrisagem(Pista oPista, Aeronave oAeronave) {

        if(oAeronave.getTipoOperacao() == TipoOperacao.ATERRISAGEM) {
            oPista.getFilaAterrisagem().adicionarAeronave(oAeronave);
        }

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronaveDecolagem(Pista oPista, Aeronave oAeronave) {

        if(oAeronave.getTipoOperacao() == TipoOperacao.DECOLAGEM) {
            oPista.getFilaDecolagem().adicionarAeronave(oAeronave);
        }

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronavesRandom() {

        Random oRandom = new Random();

        int iQuantidade = oRandom.nextInt(3);

        this.adicionarAeronaves(iQuantidade);

        return this.oAeroporto;
    }

    public Aeroporto adicionarAeronaves(int iQuantidade) {
        
        for(Pista oPista: this.oAeroporto.getPistas()) {
            ListaEncadeada<Aeronave> aAeronavesAterrisagem = this.getAeronaveFactory().criarAeronavesTipoOperacao(iQuantidade, TipoOperacao.ATERRISAGEM);
            ListaEncadeada<Aeronave> aAeronavesDecolagem = this.getAeronaveFactory().criarAeronavesTipoOperacao(iQuantidade, TipoOperacao.DECOLAGEM);
            
            for(Aeronave oAeronave: aAeronavesAterrisagem) {
                this.adicionarAeronaveAterrisagem(oPista, oAeronave);
            }

            for(Aeronave oAeronave: aAeronavesDecolagem) {
                this.adicionarAeronaveDecolagem(oPista, oAeronave);
            }
        }

        return this.oAeroporto;
    }

    public Aeroporto aterrisarAeronaves() {
        
        for(Pista oPista: this.oAeroporto.getPistas()) {

            if(oPista.isOcupada()) {
                return this.oAeroporto;
            }

            oPista.setEstado(EstadoPista.OCUPADA);
            
            Aeronave oAeronave = oPista.aterrisar();
            
            this.iQuantidadeAeronavesAterrisadas++;
            
            this.aAeronavesAterrisadas.adicionar(oAeronave);

            if(oAeronave.getPrioridade() == Aeronave.PRIORIDADE_ALTA) {
                iQuantidadeAeronavesAterrisadasEmergencia++;
            }

            oPista.setEstado(EstadoPista.DISPONIVEL);
        }
        
        return this.oAeroporto;
    }
    
    public Aeroporto decolarAeronaves() {

        for(Pista oPista: this.oAeroporto.getPistas()) {

            if(oPista.isOcupada()) {
                return this.oAeroporto;
            }

            oPista.setEstado(EstadoPista.OCUPADA);
            
            Aeronave oAeronave = oPista.decolar();
            
            this.aAeronavesDecoladas.adicionar(oAeronave);

            this.iQuantidadeAeronavesDecoladas++;

            oPista.setEstado(EstadoPista.DISPONIVEL);
        }
        
        return this.oAeroporto;
    }

    public Aeroporto aterrisarAeronave(Aeronave oAeronave) {

        for(Pista oPista: this.oAeroporto.getPistas()) {
            
            if(oPista.getEstado() == EstadoPista.DISPONIVEL) {
                oPista.setAeronaveAterrisagem(oAeronave);
                return this.oAeroporto;
            }

        }

        return this.oAeroporto;
    }

    public Aeroporto decolarAeronave(Aeronave oAeronave) {


        for(Pista oPista: this.oAeroporto.getPistas()) {
            
            if(oPista.getEstado() == EstadoPista.DISPONIVEL) {
                oPista.setAeronaveDecolagem(oAeronave);
                return this.oAeroporto;
            }

        }

        return this.oAeroporto;
    }

    public boolean isAeronavePrioridade(Aeronave oAeronave) {
        return oAeronave.getTempoEspera() > 15 || oAeronave.getCombustivel() < 10;
    }

    public void gerenciarPrioridadeAeronave(Aeronave oAeronave) {

        if(oAeronave.getCombustivel() < 20 || oAeronave.getTempoEspera() > 20) {
            oAeronave.setPrioridade(Aeronave.PRIORIDADE_ALTA);
        }
        else if(oAeronave.getCombustivel() < 40 || oAeronave.getTempoEspera() > 10) {
            oAeronave.setPrioridade(Aeronave.PRIORIDADE_MEDIA);
        }
        else {
            oAeronave.setPrioridade(Aeronave.PRIORIDADE_BAIXA);
        }
    }

    public double calcularTempoEsperaAterrisagem() {

        int iQuantidadeAeronavesAterrisadas = this.iQuantidadeAeronavesAterrisadas + this.iQuantidadeAeronavesAterrisadasEmergencia;

        long iTempoEspera = 0;

        for(Aeronave oAeronave: this.aAeronavesAterrisadas) {
            iTempoEspera = oAeronave.getTempoEspera();
        }

        if(iQuantidadeAeronavesAterrisadas > 0) {
            return (double) iTempoEspera / iQuantidadeAeronavesAterrisadas;
        } 
        
        return 0.0;
    }

    public double calcularTempoEsperaDecolagem() {

        long iTempoEspera = 0;

        for(Aeronave oAeronave: this.aAeronavesDecoladas) {
            iTempoEspera = oAeronave.getTempoEspera();
        }

        if(this.iQuantidadeAeronavesDecoladas > 0) {
            return (double) iTempoEspera / iQuantidadeAeronavesDecoladas;
        } 
        
        return 0.0;
    }

    //#endregion

    //#region Métodos Privados

    private void beforeGerenciar(Aeroporto oAeroporto) {
        this.setAeroporto(oAeroporto);
        this.setAeronaveFactory(new AeronaveFactory());
        this.adicionarAeronaves(Pista.CAPACIDADE_PADRAO);
    }

    private static void clearConsole() {
        Console console = System.console();
        if (console != null) {
            console.writer().print("\033[H\033[2J");
            console.flush();
        }
    }

    //#endregion
}
