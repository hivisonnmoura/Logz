package repositorios;

import entidades.EntidadeNo;

import java.util.ArrayList;

public class RepositorioNo implements RepositorioGenerico<EntidadeNo> {

    private static ArrayList<EntidadeNo> nos = new ArrayList<>();

    public ArrayList<EntidadeNo> select(EntidadeNo no) {
        return select(no);
    }

    public EntidadeNo selectByData(String data) {
        for (EntidadeNo itemNo : nos) {
            if (data.equals(itemNo.getData())) {
                return itemNo;
            }
        }
        return null;
    }

    public void insert(EntidadeNo no) {
        nos.add(no);

    }

    public ArrayList<EntidadeNo> delete() {
        nos.clear();
        return null;
    }

    public ArrayList<EntidadeNo> findall() {
        return nos;
    }

    public EntidadeNo find(EntidadeNo no) {

        return null;
    }

}
