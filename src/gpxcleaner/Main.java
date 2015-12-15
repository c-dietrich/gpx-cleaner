package gpxcleaner;

import gpxcleaner.controllers.ArquivoGpxController;

public class Main {

    public static void main(String[] args) {
        ArquivoGpxController arquivoGpxController = new ArquivoGpxController();
        String valor = args[0];
        String caminhoOrigem = args[1];
        String caminhoDestino = args[2];

        try {
            arquivoGpxController.gerarArquivoReduzido(valor, caminhoOrigem, caminhoDestino);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
