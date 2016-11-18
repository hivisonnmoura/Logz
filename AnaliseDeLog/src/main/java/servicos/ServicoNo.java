package servicos;

import entidades.EntidadeNo;
import entidades.EntidadeProcesso;
import fabricas.FabricaNo;
import repositorios.RepositorioNo;

import java.util.ArrayList;

public class ServicoNo {


    private RepositorioNo repositorioNo = new RepositorioNo();

    public ServicoNo() {

    }

    public EntidadeNo solicitarCriacaoNo(String nome, String data, ArrayList<EntidadeProcesso> processos) {
        EntidadeNo no = FabricaNo.nova().criarNo(nome, data, processos);
        repositorioNo.insert(no);
        return no;
    }

    /*public EntidadeNo solicitarselectByData(String data) {
        return repositorioNo.selectByData(data);
    }*/

    public ArrayList<EntidadeNo> buscarTodos() {

        return repositorioNo.findall();

    }

}