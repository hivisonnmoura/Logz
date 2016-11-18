package utilidades;

import java.util.List;

import entidades.EntidadeThread;

public class DirecionaStack {
	
	
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
}
