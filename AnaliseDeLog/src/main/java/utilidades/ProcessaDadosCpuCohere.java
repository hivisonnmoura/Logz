package utilidades;

import servicos.ServicoThread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProcessaDadosCpuCohere {

        private static ServicoThread servicoThread = new ServicoThread();
        private static List<String> listaCpuCohere = new ArrayList<>();

    public static void processaCpuCohere(String caminhoCpuCohere) {
        File caminhoCpuCoherence = new File(caminhoCpuCohere);
        String caminhoDoArquivo = caminhoCpuCoherence.getAbsolutePath();

        try (Stream<String> stream = Files.lines(Paths.get(caminhoDoArquivo))) {
            listaCpuCohere = stream.filter(cpuCohere -> cpuCohere.contains("java"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String cpuCohere : listaCpuCohere) {
            String[] arrayCpuCohere = cpuCohere.split("\\s+");
            String[] arrayCpuCoherequebraBarra = arrayCpuCohere[10].split("/");
            String pidCohereDadoString = arrayCpuCohere[1];
            int pidCohereDadoInt = Integer.parseInt(pidCohereDadoString);
            String cpuCohereDado = arrayCpuCohere[9];
            String lwpidCohereDadoString = arrayCpuCoherequebraBarra[1];
            int lwpidCohereDadoInt = Integer.parseInt(lwpidCohereDadoString);
           

            servicoThread.solicitarCriacaoThread(pidCohereDadoInt, cpuCohereDado, lwpidCohereDadoInt,
                    caminhoDoArquivo);


        }

    }

}

