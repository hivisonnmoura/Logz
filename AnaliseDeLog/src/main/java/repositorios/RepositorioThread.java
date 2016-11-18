package repositorios;

import entidades.EntidadeThread;

import java.util.ArrayList;

public class RepositorioThread implements RepositorioGenerico<EntidadeThread> {


    private static ArrayList<EntidadeThread> threads = new ArrayList<>();


    public ArrayList<EntidadeThread> select(EntidadeThread thread) {
        return select(thread);
    }

    public void insert(EntidadeThread thread) {
        threads.add(thread);

    }


    public ArrayList<EntidadeThread> delete() {
        threads.clear();
        return null;
    }

    public ArrayList<EntidadeThread> findall() {

        return threads;
    }

    public EntidadeThread find(EntidadeThread thread) {

        return null;
    }


}
