package fabricas;

import entidades.EntidadeNo;
import entidades.EntidadeProcesso;

import java.util.List;

public class FabricaNo {

    private FabricaNo() {

    }

    public static FabricaNo nova() {
        return new FabricaNo();
    }

    public EntidadeNo criarNo(String nome, String data, List<EntidadeProcesso> processos) {
        return EntidadeNo.criarNo(nome, data, processos);
    }

}