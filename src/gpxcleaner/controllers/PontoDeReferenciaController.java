package gpxcleaner.controllers;

import gpxcleaner.models.PontoDeReferencia;

import java.util.ArrayList;
import java.util.List;

public class PontoDeReferenciaController {
    /**
     * Reduz o trajeto com base na quilometragem máxima entre dois pontos
     */
    public List<PontoDeReferencia> eliminarPontosDeReferenciaPorQuilometragem(Float quilometragemMaximaDesejada, List<PontoDeReferencia> pontosDeReferencia){
        for(int i = 0; i < pontosDeReferencia.size() - 1; i++){
            Double distanciaEntreDoisPontosDeReferencia = calcularDistanciaEntreDoisPontosDeReferencia(pontosDeReferencia.get(i),pontosDeReferencia.get(i+1));
            if(distanciaEntreDoisPontosDeReferencia <= quilometragemMaximaDesejada){
                pontosDeReferencia.remove(i+1);
                i = 1;
            }
        }

        return pontosDeReferencia;
    }

    /**
     * Calcula a distância entre dois pontos
     */
    private Double calcularDistanciaEntreDoisPontosDeReferencia(PontoDeReferencia ponto1, PontoDeReferencia ponto2){
        Double a = Math.pow((ponto1.getLatitude() - ponto2.getLatitude()), 2);
        Double b = Math.pow((ponto1.getLongitude() - ponto2.getLongitude()), 2);
        Double distancia = Math.sqrt(a + b);

        return distancia;
    }
}
