package utilidades;

import java.io.File;


public class ProcessaDadosDoNo {
    private static ProcessaDadosCPU processaDadosCPU = new ProcessaDadosCPU();


    public String[] processaDiretorio(File file) {
        String[] arquivos = file.list();
        String diretorioDoTemp = file.getAbsolutePath();
        processaDiretorioDoArquivo(arquivos, diretorioDoTemp);
        return arquivos;
    }

    private static void processaDiretorioDoArquivo(String[] arquivos, String diretorioDoTemp) {
        for (String file1 : arquivos) {
            String[] noAtributoData = file1.split("_");
            String nomeNo = noAtributoData[1];
            String dataNo = noAtributoData[2];
            String diretorio = diretorioDoTemp.concat(File.separator).concat(file1);
            processaDadosCPU.processaDiretorioDoNo(diretorio, nomeNo, dataNo);
        }
    }

}

