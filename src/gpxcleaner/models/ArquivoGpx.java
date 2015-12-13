package gpxcleaner.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("gpx")
public class ArquivoGpx {
    @XStreamAlias("trk")
    Trajeto trajeto;

    public Trajeto getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(Trajeto trajeto) {
        this.trajeto = trajeto;
    }
}
