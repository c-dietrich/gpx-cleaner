package gpxcleaner.controllers;

import gpxcleaner.models.PontoDeReferencia;

import java.util.List;

public class PontoDeReferenciaController {
    /**
     * Reduz o trajeto com base na quilometragem máxima entre dois pontos
     */
    public List<PontoDeReferencia> eliminarPontosDeReferenciaPorQuilometragem(Float quilometragemMaximaDesejada, List<PontoDeReferencia> pontosDeReferencia){
        for(int i = 0; i < pontosDeReferencia.size() - 1; i++){
            double distanciaEntreDoisPontosDeReferencia = calcularDistanciaEntreDoisPontosDeReferencia(pontosDeReferencia.get(i), pontosDeReferencia.get(i+1));
            if(distanciaEntreDoisPontosDeReferencia <= quilometragemMaximaDesejada){
                pontosDeReferencia.remove(i+1);
                i = 1;
            }
        }

        return pontosDeReferencia;
    }

    /**
     * Reduz o trajeto com base na porcentagem
     */
    public List<PontoDeReferencia> eliminarPontosDeReferenciaPorPorcentagem(float quantidadeDePontosParaEliminar, List<PontoDeReferencia> pontosDeReferencia){
        PontoDeReferencia ponto3 = new PontoDeReferencia();
        double menorDistancia = 0;
        int index = 0;

        for(int i=0; i<quantidadeDePontosParaEliminar; i++){
            for(int j = 0; j < pontosDeReferencia.size() - 2; j++){
                setaCoordenadasDeP3(pontosDeReferencia.get(j), pontosDeReferencia.get(j + 1), pontosDeReferencia.get(j + 2), ponto3);
                double distanciaEntreDoisPontosDeReferencia = calcularDistanciaEntreDoisPontosDeReferencia(pontosDeReferencia.get(j), ponto3);
                if(j == 0){
                    index = j;
                    menorDistancia = distanciaEntreDoisPontosDeReferencia;
                }
                else if(distanciaEntreDoisPontosDeReferencia < menorDistancia){
                    index = j;
                    menorDistancia = distanciaEntreDoisPontosDeReferencia;
                }
            }
            pontosDeReferencia.remove(index);
        }

        return pontosDeReferencia;
    }

    /**
     * Calcula a distância entre dois pontos
     */
    private double calcularDistanciaEntreDoisPontosDeReferencia(PontoDeReferencia ponto0, PontoDeReferencia ponto1){
        double x1 = ponto0.getLatitude();
        double x2 = ponto1.getLatitude();
        double y1 = ponto0.getLongitude();
        double y2 = ponto1.getLongitude();

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

    private void setaCoordenadasDeP3(PontoDeReferencia ponto0, PontoDeReferencia ponto1, PontoDeReferencia ponto2, PontoDeReferencia ponto3){
        double x0 = ponto0.getLatitude();
        double x1 = ponto1.getLatitude();
        double x2 = ponto2.getLatitude();
        double y0 = ponto0.getLongitude();
        double y1 = ponto1.getLongitude();
        double y2 = ponto2.getLongitude();

        double a = (y0 - y2) / (x0 - x2);

        double b = y2 - a*x2;

        double c = a*x1 + y1;

        double x3 = c - b / 2*a;

        double y3 = a*x3 + b;

        ponto3.setLatitude(x3);
        ponto3.setLongitude(y3);
    }
}
