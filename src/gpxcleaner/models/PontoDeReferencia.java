package gpxcleaner.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Pontos que referenciam um local so espaço
 */
@XStreamAlias("trkpt")
public class PontoDeReferencia {
    @XStreamAlias("lat")
    @XStreamAsAttribute
    private Double latitude;
    @XStreamAlias("lon")
    @XStreamAsAttribute
    private Double longitude;
    @XStreamAlias("ele")
    private Double elevacao;
    @XStreamAlias("time")
    private String momentoDeRegistro;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevacao() {
        return elevacao;
    }

    public void setElevacao(Double elevacao) {
        this.elevacao = elevacao;
    }

    public String getMomentoDeRegistro() {
        return momentoDeRegistro;
    }

    public void setMomentoDeRegistro(String momentoDeRegistro) {
        this.momentoDeRegistro = momentoDeRegistro;
    }
}
