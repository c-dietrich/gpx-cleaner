package gpxcleaner.controllers;

public class ArquivoGpxController {
    TrajetoController trajetoController = TrajetoController.getInstance();

    /**
     * Implementação singleton ArquivoGpxController
     */
    private static ArquivoGpxController instance = null;

    public static ArquivoGpxController getInstance()
    {
        if (instance == null)
            instance = new ArquivoGpxController();

        return instance;
    }

    public void gerarArquivoReduzido(String valor, String caminhoOrigem, String caminhoDestino){
        String tipoDeReducao = obterTipoDeReducao(valor);

        if (tipoDeReducao.equals("quilometragem")) {
            reducacaoPorQuilometragem(valor, caminhoOrigem, caminhoDestino);
        }
        else if(tipoDeReducao.equals("porcentagem")){
            reducacaoPorPorcentagem(valor, caminhoOrigem, caminhoDestino);
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
    private void reducacaoPorPorcentagem(String valor, String caminhoOrigem, String caminhoDestino){
        float porcentagem = Float.parseFloat(valor.replace("%",""));
        trajetoController.reduzirTrajetoPorcentagem(porcentagem, caminhoOrigem, caminhoDestino);
    }
}
