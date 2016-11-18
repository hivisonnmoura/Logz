package servicos;

import java.io.File;

public class ServicoValidadorDeNos {
    int contadorProcesso = 0;
    int contadorThread = 0;
    int contadorStack = 0;
    String mensagemErro;
    static Boolean valido;

    public void listar(File diretorio) {

        String[] subDiretorio = diretorio.list();
        if (!diretorio.getName().contains("Cohere")) {

            if (diretorio.getName().startsWith("CPUProcess_cmp")) {
                contadorProcesso++;
            } else if (diretorio.getName().startsWith("CPUProcess_Detalhado")) {
                contadorThread++;
            } else if (diretorio.getName().startsWith("ResultDumps_cmp")) {
                contadorStack++;
            }
        }
        if (subDiretorio != null) {
            for (String dir : subDiretorio) {
                listar(new File(diretorio + File.separator + dir));

            }
            validarContadores(contadorProcesso, contadorThread, contadorStack);
        }

    }

    public Boolean validarContadores(int contadorProcesso, int contadorThread, int contadorStack) {
        if (contadorProcesso == 0 && contadorProcesso == 0 && contadorStack == 0) {
            mensagemErro = "Nao ha arquivos de logs de Processos, Threads e Stacks";
            valido = false;
        } else if (contadorProcesso == contadorStack && contadorProcesso == contadorThread
                && contadorStack == contadorThread) {
            valido = true;
        } else {
            mensagemErro = "O numero de logs de Processos, Threads e Stacks por no, nao sao iguais";
            valido = false;
        }
        return valido;
    }

}