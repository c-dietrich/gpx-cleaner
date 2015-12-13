package gpxcleaner.controllers;

import com.thoughtworks.xstream.XStream;
import gpxcleaner.models.ArquivoGpx;
import gpxcleaner.models.PontoDeReferencia;
import gpxcleaner.models.Segmento;
import gpxcleaner.models.Trajeto;

import java.io.FileReader;
import java.io.IOException;

public class TrajetoController {
    /**
     * Obtem um trajeto apartir de um arquivo GPX
     */
    public Trajeto obtemTrajetoGpx(String caminhoArquivo) throws IOException {
        FileReader arquivoFormatoGpx = new FileReader(caminhoArquivo);

        XStream xStream = new XStream();
        xStream.processAnnotations(ArquivoGpx.class );
        xStream.processAnnotations(Trajeto.class);
        xStream.processAnnotations(Segmento.class);
        xStream.processAnnotations(PontoDeReferencia.class);

        ArquivoGpx arquivoGpx = (ArquivoGpx) xStream.fromXML(arquivoFormatoGpx);

        Trajeto trajeto = arquivoGpx.getTrajeto();

        return trajeto;
    }
}
