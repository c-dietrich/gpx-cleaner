package gpxcleaner.controllers;

import com.thoughtworks.xstream.XStream;
import gpxcleaner.models.ArquivoGpx;
import gpxcleaner.models.PontoDeReferencia;
import gpxcleaner.models.Segmento;
import gpxcleaner.models.Trajeto;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrajetoController {
    PontoDeReferenciaController pontoDeReferenciaController = new PontoDeReferenciaController();
    /**
     * Reduz o trajeto com base na quilometragem máxima entre dois pontos
     */
    //TODO-implementar função reduzirTrajetoQuilometragem
    public void reduzirTrajetoQuilometragem(Float quilometragemMaximaDesejada, String caminhoOrigem){
        try {
            Trajeto trajeto = obtemTrajetoGpx(caminhoOrigem);
            List<PontoDeReferencia> pontosDeReferencia = obtemPontosDeReferencia(trajeto);
            List<PontoDeReferencia> pontosDeReferenciaReduzido = pontoDeReferenciaController.eliminarPontosDeReferenciaPorQuilometragem(quilometragemMaximaDesejada, pontosDeReferencia);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Obtem um trajeto apartir de um arquivo GPX
     */
    private Trajeto obtemTrajetoGpx(String caminhoArquivo) throws IOException {
        FileReader arquivoFormatoGpx = new FileReader(caminhoArquivo);

        XStream xStream = new XStream();
        xStream.processAnnotations(ArquivoGpx.class);
        xStream.processAnnotations(Trajeto.class);
        xStream.processAnnotations(Segmento.class);
        xStream.processAnnotations(PontoDeReferencia.class);

        ArquivoGpx arquivoGpx = (ArquivoGpx) xStream.fromXML(arquivoFormatoGpx);

        Trajeto trajeto = arquivoGpx.getTrajeto();

        return trajeto;
    }

    /**
     * Obtem os pontos de referência de um trajeto
     */
    private List<PontoDeReferencia> obtemPontosDeReferencia(Trajeto trajeto){
        List<Segmento> segmentos = trajeto.getSegmentos();
        List<PontoDeReferencia> pontosDeReferencia = new ArrayList<>();

        for(Segmento segmento: segmentos){
            for (PontoDeReferencia pontoDeReferencia: segmento.getPontosDeReferencia()){
                pontosDeReferencia.add(pontoDeReferencia);
            }
        }

        return pontosDeReferencia;
    }
}
