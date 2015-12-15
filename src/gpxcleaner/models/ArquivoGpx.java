package gpxcleaner.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("gpx")
public class ArquivoGpx {
    @XStreamAlias("creator")
    @XStreamAsAttribute
    private String criador;
    @XStreamAlias("version")
    @XStreamAsAttribute
    private String versao;

    @XStreamAlias("trk")
    Trajeto trajeto;

    public ArquivoGpx() {

    }

    public ArquivoGpx(String criador, String versao, Trajeto trajeto) {
        this.criador = criador;
        this.versao = versao;
        this.trajeto = trajeto;
    }

    public Trajeto getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(Trajeto trajeto) {
        this.trajeto = trajeto;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
