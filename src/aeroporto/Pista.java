package aeroporto;

public class Pista {

    //#region Atributos/Constantes

    private String sNome;

    private EstadoPista eEstado = EstadoPista.DISPONIVEL;
    
    private FilaAterrisagem oFilaAterrisagem;
    private FilaDecolagem   oFilaDecolagem;

    private Aeronave oAeronaveAterrisagem;
    private Aeronave oAeronaveDecolagem;

    public static final int CAPACIDADE_PADRAO = 5;

    //#endregion

    //#region Constructor
    
    public Pista(String sNome) {
        this.setNome(sNome);
        this.setFilaAterrisagem(new FilaAterrisagem(CAPACIDADE_PADRAO));
        this.setFilaDecolagem(new FilaDecolagem(CAPACIDADE_PADRAO));
    }

    //#endregion

    //#region Getters/Setters

    public void setNome(String sNomePista) {
        this.sNome = sNomePista;
    }

    public String getNome() {
        return this.sNome;
    }

    public void setEstado(EstadoPista eEstado) {
        this.eEstado = eEstado;
    }

    public EstadoPista getEstado() {
        return this.eEstado;
    }

    public void setFilaAterrisagem(FilaAterrisagem oFilaAterrisagem) {
        this.oFilaAterrisagem = oFilaAterrisagem;
    }

    public FilaAterrisagem getFilaAterrisagem() {
        return this.oFilaAterrisagem;
    }
    
    public void setFilaDecolagem(FilaDecolagem oFilaDecolagem) {
        this.oFilaDecolagem = oFilaDecolagem;
    }

    public FilaDecolagem getFilaDecolagem() {
        return this.oFilaDecolagem;
    }

    public void setCapacidadeFilaDecolagem(int iCapacidade) {
        this.oFilaDecolagem.setCapacidade(iCapacidade);
    }

    public void setCapacidadeFilaAterrisagem(int iCapacidade) {
        this.oFilaAterrisagem.setCapacidade(iCapacidade);
    }

    public int getCapacidadeFilaDecolagem() {
        return this.oFilaDecolagem.getCapacidade();
    }

    public int getCapacidadeFilaAterrisagem() {
        return this.oFilaAterrisagem.getCapacidade();
    }

    public void setAeronaveAterrisagem(Aeronave oAeronaveAterrisagem) {
        this.oAeronaveAterrisagem = oAeronaveAterrisagem.setTipoOperacao(TipoOperacao.ATERRISANDO);
    }

    public Aeronave getAeronaveAterrisagem() {
        return this.oAeronaveAterrisagem;
    }

    public void setAeronaveDecolagem(Aeronave oAeronaveDecolagem) {
        this.oAeronaveDecolagem = oAeronaveDecolagem.setTipoOperacao(TipoOperacao.DECOLANDO);
    }

    public Aeronave getAeronaveDecolagem() {
        return this.oAeronaveDecolagem;
    }

    public boolean isOcupada() {
        return this.getEstado() == EstadoPista.OCUPADA;
    }

    //#endregion

    //#region Métodos Publicos

    public Aeronave decolar() {
        
        FilaDecolagem aFilaDecolagemPrioridade = this.getFilaDecolagem(); 

        this.setAeronaveDecolagem(aFilaDecolagemPrioridade.getPrimeiro());
        
        Aeronave oAeronaveDecolada = aFilaDecolagemPrioridade.desenfileirar().setTipoOperacao(TipoOperacao.DECOLADO);

        System.out.println("\n");
        System.out.println(oAeronaveDecolada);
        System.out.println("\n");
        
        return oAeronaveDecolada;
    }

    public Aeronave aterrisar() {

        FilaAterrisagem aFilaAterrisagemPrioridade = this.getFilaAterrisagem(); 

        this.setAeronaveAterrisagem(aFilaAterrisagemPrioridade.getPrimeiro());
        
        Aeronave oAeronaveAterrisada = aFilaAterrisagemPrioridade.desenfileirar().setTipoOperacao(TipoOperacao.ATERRISADO);

        System.out.println("\n");
        System.out.println(oAeronaveAterrisada);
        System.out.println("\n");

        return oAeronaveAterrisada;
    }

    //#endregion

    //#region ToString

    @Override
    public String toString() {
        StringBuilder oStringBuilder = new StringBuilder();
        oStringBuilder.append("Pista: ").append(this.sNome).append("\n");
        oStringBuilder.append("Estado: ").append(this.eEstado).append("\n");
        oStringBuilder.append("Fila de aterrissagem: ").append(this.oFilaAterrisagem.toString()).append("\n");
        oStringBuilder.append("Fila de decolagem: ").append(this.oFilaDecolagem.toString()).append("\n");
        return oStringBuilder.toString();
    }

    //#endregion

}
