package servicos;

import java.io.File;

public class ServicoValidadorDeNos {
	int contadorProcesso = 0;
	int contadorThread = 0;
	int contadorThreadCoherence = 0;
	int contadorStack = 0;
	int contadorStackCoherence = 0;
	String mensagemErro;
	static Boolean valido;

	public void listar(File diretorio) {

		String[] subDiretorio = diretorio.list();

		if (diretorio.getName().startsWith("CPUProcess_cmp")) {
			contadorProcesso++;
		} else if (diretorio.getName().startsWith("CPUProcess_Cohere")) {
			contadorThreadCoherence++;
		} else if (diretorio.getName().startsWith("CPUProcess_Detalhado")) {
			contadorThread++;
		} else if (diretorio.getName().startsWith("ResultDumps_cmp")) {
			contadorStack++;
		} else if (diretorio.getName().startsWith("ResultDumps_Coherence")) {
			contadorStackCoherence++;
		}
		if (subDiretorio != null) {
			for (String dir : subDiretorio) {
				listar(new File(diretorio + File.separator + dir));

			}

			validarContadores(contadorProcesso, contadorThread, contadorThreadCoherence, contadorStack,
					contadorStackCoherence);
		}

	}

	public Boolean validarContadores(int contadorProcesso, int contadorThread, int contadorThreadCoherence,
			int contadorStack, int contadorStackCoherence) {

		if (contadorProcesso == 0 && contadorProcesso == 0 && contadorStack == 0) {
			mensagemErro = "N\u00e3o h\u00e1 arquivos de logs de Processos, Threads e Stacks";
			valido = false;
		} else if (contadorProcesso == contadorStack && contadorProcesso == contadorThread
				&& contadorStack == contadorThread) {
			valido = true;
			if (contadorThreadCoherence != contadorStackCoherence) {
				mensagemErro = "O n\u00famero de logs de Coherence por n\u00f3, n\u00e3o s\u00e3o iguais";
				valido = false;
			}

		} else {
			mensagemErro = "O n\u00famero de logs de Processos, Threads e Stacks por n\u00f3, n\u00e3o s\u00e3o iguais";
			valido = false;
		}
		return valido;
	}

}