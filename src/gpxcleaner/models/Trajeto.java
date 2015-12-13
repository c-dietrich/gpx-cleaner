package gpxcleaner.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.List;

/**
 * Classe que contem o trajeto percorrido
 */
@XStreamAlias("trk")
public class Trajeto {
    /**
     * Atributo utilizado para que o XStream ignore a tag gpx
     */
    @XStreamOmitField
    private Object gpx;
    @XStreamAlias("name")
    private String nome;
    @XStreamImplicit
    private List<Segmento> segmentos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Segmento> getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(List<Segmento> segmentos) {
        this.segmentos = segmentos;
    }
}
