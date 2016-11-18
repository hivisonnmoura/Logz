package utilidades;

import entidades.EntidadeProcesso;
import servicos.ServicoProcesso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class ProcessaCpuProcess {

    public static EntidadeProcesso processaArquivoCpuProcess(File pastaFinal) {
        BufferedReader br = null;
        String[] cpuProcessDados;
        String linhaCpuProcess = null;
        String caminhoDoArquivo;

        caminhoDoArquivo = pastaFinal.getName();


        String[] cpuProcessAtributos = caminhoDoArquivo.split("_");
        cpuProcessAtributos[3] = tratarHora(cpuProcessAtributos);


        try {
            br = new BufferedReader(new FileReader(pastaFinal));

            linhaCpuProcess = br.readLine();
            linhaCpuProcess = br.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        assert linhaCpuProcess != null;
        cpuProcessDados = linhaCpuProcess.split("\\s+");
        cpuProcessDados[0] = cpuProcessAtributos[3];


        int pid = Integer.parseInt(cpuProcessDados[1]);
        String username = cpuProcessDados[2];
        String hora = cpuProcessDados[0];
        String cpu = cpuProcessDados[9];
        String[] quebraNlwp = cpuProcessDados[10].split("/");
        int nlwp = Integer.parseInt(quebraNlwp[1]);
        String process = quebraNlwp[0];
        String time = cpuProcessDados[8];
        String diretorio = pastaFinal.getAbsolutePath();


        EntidadeProcesso processo = new EntidadeProcesso(pid, username, time, cpu, nlwp, process, hora, diretorio);
        ServicoProcesso servicoProcesso = new ServicoProcesso();
        servicoProcesso.solicitarCriacaoProcesso(pid, username, time, cpu, nlwp, process, hora, diretorio);


        return processo;
    }

    private static String tratarHora(String[] ajustaHoraComDivisao) {
        String ajustaHora;
        ajustaHora = ajustaHoraComDivisao[3];
        ajustaHora = new StringBuffer(ajustaHora).insert(2, ":").delete(5, 9).toString();
        return ajustaHora;
    }
}