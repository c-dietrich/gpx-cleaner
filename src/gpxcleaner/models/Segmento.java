package gpxcleaner.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Seguimento do trajeto que contem pontos de referencia
 */
@XStreamAlias("trkseg")
public class Segmento {
    @XStreamImplicit
    List<PontoDeReferencia> pontosDeReferencia;

    public List<PontoDeReferencia> getPontosDeReferencia() {
        return pontosDeReferencia;
    }

    public void setPontosDeReferencia(List<PontoDeReferencia> pontosDeReferencia) {
        this.pontosDeReferencia = pontosDeReferencia;
    }
}
