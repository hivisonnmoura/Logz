package repositorios;

import entidades.EntidadeProcesso;

import java.util.ArrayList;

public class RepositorioProcesso implements RepositorioGenerico<EntidadeProcesso> {
    private static ArrayList<EntidadeProcesso> processos = new ArrayList<>();

    public ArrayList<EntidadeProcesso> select(EntidadeProcesso processo) {
        return select(processo);
    }

    public EntidadeProcesso selectByHora(String hora) {
        for (EntidadeProcesso itemProcesso : processos) {
            if (hora.equals(itemProcesso.getHora())) {
                return itemProcesso;
            }
        }
        return null;
    }

    public void insert(EntidadeProcesso processo) {
        processos.add(processo);

    }

    public ArrayList<EntidadeProcesso> delete() {
        processos.clear();
        return null;
    }

    public ArrayList<EntidadeProcesso> findall() {

        return processos;
    }

    public EntidadeProcesso find(EntidadeProcesso processo) {

        return null;
    }

}
