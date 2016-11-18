package utilidades;

import objetodevalor.Regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessaStacksUtil {


    public static List<String> processaStack(String caminhoDaStack, int decimal) {
        String hexaDecimal = "0x" + Integer.toHexString(decimal);
        String stringContatenaTexto;

        List<String> blocoStack = new ArrayList<>();
        List<String> listaAuxiliar = new ArrayList<>();
        List<String> listaDeStacks = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(caminhoDaStack))) {

            listaAuxiliar = stream.collect(Collectors.toList());

        } catch (IOException e) {

            e.printStackTrace();

        }

        stringContatenaTexto = String.join("\n", listaAuxiliar);

        String[] arrayDivideStackEmBlocos = stringContatenaTexto.split("\n\n");

        listaDeStacks.addAll(Arrays.asList(arrayDivideStackEmBlocos).subList(1, arrayDivideStackEmBlocos.length - 1));


        listaAuxiliar.clear();
        arrayDivideStackEmBlocos = null;


        Map<String, List<String>> hashMapDasStacks = new HashMap<>();
        List<String> doLogs;


        for (String stack : listaDeStacks) {

            Regex regexDelimitaNid = Regex.REGEX_DELIMITA_NID;
            Matcher matchVerificaSeExisteNid = regexDelimitaNid.getPattern().matcher(stack);

            while (matchVerificaSeExisteNid.find()) {

                String key = stack.substring(matchVerificaSeExisteNid.start() + 5, matchVerificaSeExisteNid.end() - 1);

                if (hashMapDasStacks.containsKey(key)) {
                    hashMapDasStacks.get(key).add(stack);
                } else {
                    doLogs = new ArrayList<>();
                    doLogs.add(stack);
                    hashMapDasStacks.put(key, doLogs);
                }

            }

        }


        hashMapDasStacks.forEach((k, v) -> {

            if (hexaDecimal.equals(k)) {
                blocoStack.addAll(v);
            }
        });


        return blocoStack;
    }

}



