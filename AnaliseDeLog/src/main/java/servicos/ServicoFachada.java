package servicos;

import entidades.EntidadeNo;
import entidades.EntidadeThread;
import interfaces.FrmDiretorio;
import interfaces.FrmNodos;
import repositorios.RepositorioNo;
import repositorios.RepositorioProcesso;
import repositorios.RepositorioThread;
import utilidades.ProcessaDadosDoNo;
import utilidades.ProcessaStacksUtil;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicoFachada {

    private ServicoDescompactador servicoDescompactador = new ServicoDescompactador();
    private ProcessaDadosDoNo processaDadosDoNo = new ProcessaDadosDoNo();
    private RepositorioProcesso repositorioProcesso = new RepositorioProcesso();
    private RepositorioNo repositorioNo = new RepositorioNo();
    private RepositorioThread repositorioThread = new RepositorioThread();
    private ServicoValidadorDeNos servicoValidadorDeNos = new ServicoValidadorDeNos();
    private ServicoTratamentoExcessao servicoTratamentoExcessao = new ServicoTratamentoExcessao();




    public File solicitarServicoDescompactador(String caminho, List<String> listaArquivo) {
        File file = servicoDescompactador.extrairLogs(caminho, listaArquivo);
        solicitarServicoValidadorDeNos(file);

        return file;
    }

    public void solicitarServicoValidadorDeNos(File file) {
        servicoValidadorDeNos.listar(file);

        if (ServicoValidadorDeNos.valido) {
            solicitarProcessaDadosDoNo(file);
            solicitarProcessaDadosCpuDetalhado(file);
            FrmDiretorio formUm = new FrmDiretorio();
            FrmNodos formDois = new FrmNodos();
            formDois.setVisible(true);
            formUm.setVisible(false);


        } else {

            JOptionPane.showMessageDialog(null, servicoValidadorDeNos.mensagemErro + "\n POR FAVOR, ESCOLHA OUTRO DIRETORIO", "ERRO: Arquivo invalido ou em branco", JOptionPane.ERROR_MESSAGE);
            FrmDiretorio frmDiretorio = new FrmDiretorio();
            frmDiretorio.setVisible(true);
        }

    }

    private void solicitarProcessaDadosDoNo(File caminhoTemp) {
        processaDadosDoNo.processaDiretorio(caminhoTemp);

    }

    public void deletaRepositorios() {
        repositorioProcesso.delete();
        repositorioNo.delete();
    }

    private void solicitarProcessaDadosCpuDetalhado(File caminhoTemp) {
        processaDadosDoNo.processaDiretorio(caminhoTemp);

    }

    public List<EntidadeThread> buscarTodosObjetosRepositorioThread() {
        ServicoThread thread = new ServicoThread();
        return thread.buscarThreadDoRepositorio();
    }

    public String direcionaCPUProcess(String caminhoDiretorio) {

        return caminhoDiretorio.replace("CPUProcess_",
                "CPUProcess_Detalhado_");

    }

    public String direcionaCpuCohere(String caminhoDiretorio){
        return  caminhoDiretorio.replace("CPUProcess_", "CPUProcess_Cohere_");
    }





    public List<EntidadeNo> retornaListaEntidadeNo() {
        ServicoNo no = new ServicoNo();
        return no.buscarTodos();
    }

    public List<String> direcionaStack(EntidadeThread selectedItem) {
        String caminhoDaStack;
        int decimalLwpid;
        if(selectedItem.getCaminho().contains("CPUProcess_Detalhado_")){

           caminhoDaStack = selectedItem.getCaminho().replace("CPUProcess_Detalhado_", "ResultDumps_")
                    .replace(".txt", ".log");
            decimalLwpid = selectedItem.getLwpid();

        }else{
            caminhoDaStack = selectedItem.getCaminho().replace("CPUProcess_Cohere_", "ResultDumps_Coherence_")
                    .replace(".txt", ".log");
             decimalLwpid = selectedItem.getLwpid();
        }

        return ProcessaStacksUtil.processaStack(caminhoDaStack, decimalLwpid);

    }

    public void deletaStack() {
        repositorioThread.delete();
    }


    public List<String> identificarQuantidadeTarGz(File arquivo) {
        List<String> ListaArquivo = new ArrayList<>();

        if (arquivo.listFiles().length == 0)
            throw new NullPointerException();
        for (File f : arquivo.listFiles()) {
            if (f.isFile()) {
                if (f.getName().endsWith(".tar.gz")) {
                    ListaArquivo.add(f.getName());
                } else {
                    throw new NullPointerException();
                }
            }
        }

        return ListaArquivo;
    }

    public void solicitaTratamentoDeExcessaoDiretorioInvalido(JButton btnLocalizar, JTextArea textArea) {
            servicoTratamentoExcessao.tratamentoDeExcessaoDiretorioInvalido(btnLocalizar,textArea);
    }


}
