package gpxcleaner.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import gpxcleaner.models.ArquivoGpx;
import gpxcleaner.models.PontoDeReferencia;
import gpxcleaner.models.Segmento;
import gpxcleaner.models.Trajeto;

import java.io.FileOutputStream;
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
    public void reduzirTrajetoQuilometragem(Float quilometragemMaximaDesejada, String caminhoOrigem, String caminhoDestino){
        try {
            Trajeto trajeto = obtemTrajetoGpx(caminhoOrigem);
            List<PontoDeReferencia> pontosDeReferencia = obtemPontosDeReferencia(trajeto);
            List<PontoDeReferencia> pontosDeReferenciaReduzido = pontoDeReferenciaController.eliminarPontosDeReferenciaPorQuilometragem(quilometragemMaximaDesejada, pontosDeReferencia);
            gerarArquivoGpx(caminhoDestino, pontosDeReferenciaReduzido);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Obtem um trajeto apartir de um arquivo GPX
     */
    private Trajeto obtemTrajetoGpx(String caminhoArquivo) throws IOException {
        FileReader arquivoFormatoGpx = new FileReader(caminhoArquivo);

        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(ArquivoGpx.class);
        xStream.processAnnotations(Trajeto.class);
        xStream.processAnnotations(Segmento.class);
        xStream.processAnnotations(PontoDeReferencia.class);

        ArquivoGpx arquivoGpx = (ArquivoGpx) xStream.fromXML(arquivoFormatoGpx);

        Trajeto trajeto = arquivoGpx.getTrajeto();

        return trajeto;
    }

    /**
     * Gera um arquivo gpx
     */
    private void gerarArquivoGpx(String caminho, List<PontoDeReferencia> pontosDeReferencia) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(caminho);
        fileOutputStream.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n".getBytes("ISO-8859-1"));

        Segmento segmento = new Segmento(pontosDeReferencia);
        Trajeto trajeto = new Trajeto("Trajeto Reduzido", segmento);
        ArquivoGpx arquivoGpx = new ArquivoGpx("Claudio, Rafael e Ricardo", "1.0", trajeto);

        XStream xStream = new XStream(new DomDriver());
        xStream.autodetectAnnotations(true);

        String novoGpx = xStream.toXML(arquivoGpx);

        byte[] bytes = novoGpx.getBytes("ISO-8859-1");
        fileOutputStream.write(bytes);
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
