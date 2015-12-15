package gpxcleaner.controllers;

public class ArquivoGpxController {
    TrajetoController trajetoController = new TrajetoController();
    //TODO-implementar função que reduz o arquivo gpx
    public void gerarArquivoReduzido(String valor, String caminhoOrigem, String caminhoDestino){
        String tipoDeReducao = obterTipoDeReducao(valor);

        if (tipoDeReducao.equals("quilometragem")) {
            reducacaoPorQuilometragem(valor, caminhoOrigem, caminhoDestino);
        }
        else if(tipoDeReducao.equals("porcentagem")){
            reducacaoPorPorcentagem();
        }
    }

    /**
     * Obtem o tipo de redução que deve ser realizada através do valor passado
     */
    private String obterTipoDeReducao(String valor){
        return valor.contains("%") ? "porcentagem":"quilometragem";
    }

    /**
     * Realiza a redução do arquivo eliminando os pontos com base na quilometragem passada
     */
    private void reducacaoPorQuilometragem(String valor, String caminhoOrigem, String caminhoDestino){
        float quilometragemMaximaDesejada = Float.parseFloat(valor);
        trajetoController.reduzirTrajetoQuilometragem(quilometragemMaximaDesejada, caminhoOrigem, caminhoDestino);
    }

    /**
     * Realiza a redução do arquivo eliminando os pontos com base na porcentagem passada
     */
    //TODO-implemetar função reducacaoPorPorcentagem
    private void reducacaoPorPorcentagem(){

    }
}
