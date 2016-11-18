package servicos;

import entidades.EntidadeNo;
import entidades.EntidadeThread;
import interfaces.FrmDiretorio;
import interfaces.FrmNodos;
import repositorios.RepositorioNo;
import repositorios.RepositorioProcesso;
import repositorios.RepositorioThread;
import utilidades.DirecionaStack;
import utilidades.ProcessaDadosDoNo;
import javax.swing.*;
import java.io.File;
import java.util.List;

public class ServicoFachada {

	private ServicoDescompactador servicoDescompactador = new ServicoDescompactador();
	private ProcessaDadosDoNo processaDadosDoNo = new ProcessaDadosDoNo();
	private RepositorioProcesso repositorioProcesso = new RepositorioProcesso();
	private RepositorioNo repositorioNo = new RepositorioNo();
	private RepositorioThread repositorioThread = new RepositorioThread();
	private ServicoValidadorDeNos servicoValidadorDeNos = new ServicoValidadorDeNos();
	private DirecionaStack direcionaStack = new DirecionaStack();

	public File solicitarServicoDescompactador(String caminho, List<String> ListaArquivo) {
		File file = servicoDescompactador.extrairLogs(caminho, ListaArquivo);
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

			JOptionPane.showMessageDialog(null,
					servicoValidadorDeNos.mensagemErro + "\n POR FAVOR, ESCOLHA OUTRO DIRETORIO",
					"ERRO: Arquivo invalido ou em branco", JOptionPane.ERROR_MESSAGE);
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
		return caminhoDiretorio.replace("CPUProcess_", "CPUProcess_Detalhado_");
	}

	public String direcionaCpuCohere(String caminhoDiretorio) {
		return caminhoDiretorio.replace("CPUProcess_", "CPUProcess_Cohere_");
	}

	public List<EntidadeNo> retornaListaEntidadeNo() {
		ServicoNo no = new ServicoNo();
		return no.buscarTodos();
	}

	public List<String> solicitarDirecionaStack(EntidadeThread selectedItem) {
		return direcionaStack.direcionaStack(selectedItem);
	}

	public void deletaStack() {
		repositorioThread.delete();
	}

}
