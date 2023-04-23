package aeroporto;

public class Aeronave {

    //#region Atributos/Constantes

    private int iId;
    private int iTempoEspera = 0;
    private TipoOperacao eTipoOperacao = TipoOperacao.ESPERA;
    private String sCompanhia;
    
    //#endregion

    //#region Constructor

    public Aeronave(int iId, String sCompanhia, TipoOperacao eTipoOperacao, int iTempoEspera) {
        this.setId(iId);
        this.setCompanhia(sCompanhia);

        if(eTipoOperacao != null) {
            this.setTipoOperacao(eTipoOperacao);
        }

        if(iTempoEspera != 0) {
            this.setTempoEspera(iTempoEspera);
        }
    }

    //#endregion

    //#region Getters/Setters

    public int getId() {
        return this.iId;
    }

    public Aeronave setId(int iId) {
        this.iId = iId;
        return this;
    }

    public int getTempoEspera() {
        return this.iTempoEspera;
    }

    public Aeronave setTempoEspera(int iTempoEspera) {
        this.iTempoEspera = iTempoEspera;
        return this;
    }

    public TipoOperacao getTipoOperacao() {
        return this.eTipoOperacao;
    }

    public Aeronave setTipoOperacao(TipoOperacao oTipoOperacao) {
        this.eTipoOperacao = oTipoOperacao;
        return this;
    }

    public String getCompanhia() {
        return this.sCompanhia;
    }

    public Aeronave setCompanhia(String sCompanhia) {
        this.sCompanhia = sCompanhia;
        return this;
    }

    //#endregion

    //#region toString

    @Override
    public String toString() {
        StringBuilder oStringBuilder = new StringBuilder();
        oStringBuilder.append("Aeronave ");
        oStringBuilder.append(this.getId());
        oStringBuilder.append(" (");
        oStringBuilder.append(this.getCompanhia());
        oStringBuilder.append(") - ");
        oStringBuilder.append(this.getTipoOperacao());
        oStringBuilder.append(", tempo de espera: ");
        oStringBuilder.append(this.getTempoEspera());
        oStringBuilder.append(" min");
        return oStringBuilder.toString();
    }

    //#endregion
}
