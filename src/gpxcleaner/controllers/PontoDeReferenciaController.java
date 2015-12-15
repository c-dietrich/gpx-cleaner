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
        Double x1 = ponto1.getLatitude();
        Double x2 = ponto2.getLatitude();
        Double y1 = ponto1.getLongitude();
        Double y2 = ponto2.getLongitude();

        // Distancia entre os 2 pontos no plano cartesiano ( pitagoras )
        //double distancia = System.Math.Sqrt( System.Math.Pow( (x2 - x1), 2 ) + System.Math.Pow( (y2 - y1), 2 ) );

        // ARCO AB = c
        double c = 90 - (y2);

        // ARCO AC = b
        double b = 90 - (y1);

        // Arco ABC = a
        // Diferença das longitudes:
        double a = x2 - x1;

        // Formula: cos(a) = cos(b) * cos(c) + sen(b)* sen(c) * cos(A)
        double cos_a = Math.cos(b) * Math.cos(c) + Math.sin(c) * Math.sin(b) * Math.cos(a);

        double arc_cos = Math.acos(cos_a);

        // 2 * pi * Raio da Terra = 6,28 * 6.371 = 40.030 Km
        // 360 graus = 40.030 Km
        // 3,2169287 = x
        // x = (40.030 * 3,2169287)/360 = 357,68 Km

        double distancia = (40030 * arc_cos) / 360;

        return distancia;
    }
}
