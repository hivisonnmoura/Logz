package utilidades;

import entidades.EntidadeProcesso;
import servicos.ServicoNo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProcessaDadosCPU {


    private static ServicoNo servicoNo = new ServicoNo();

    public void processaDiretorioDoNo(String caminho, String nomeNo, String dataNo) {

        List<String> resposta = new ArrayList<>();
        File arquivo = new File(caminho);
        String[] arquivoCpu = arquivo.list();

        assert null != arquivoCpu;
        for (String file : arquivoCpu) {
            Pattern pattern = Pattern.compile("CPUProcess_" + nomeNo);
            Matcher matcher = pattern.matcher(file);
            if (matcher.find()) {
                resposta.add(file);
            }
        }


        for (String file : resposta) {
            ArrayList<EntidadeProcesso> listaProcesso = new ArrayList<>();
            String diretorioFinal = caminho.concat(File.separator).concat(file);
            File pastaFinal = new File(diretorioFinal);
            EntidadeProcesso processo = ProcessaCpuProcess.processaArquivoCpuProcess(pastaFinal);
            if (!listaProcesso.contains(processo)) {
                listaProcesso.add(processo);
                servicoNo.solicitarCriacaoNo(nomeNo, dataNo, listaProcesso);
            }

        }

    }

}
