package infra.db;

import java.util.*;

public class BancoDeDados {

    private static final HashMap OBJETOS = new HashMap();
    private Long id;

    public void inserirObjeto(Object objeto) throws Exception {
        Set objetos = colecaoDeObjetos(objeto.getClass());
        objetos.add(objeto);
    }

    public void excluirObjeto(Object objeto) {
        Set objetos = colecaoDeObjetos(objeto.getClass());
        objetos.remove(objeto);
    }

    public List buscarObjetosPorTipo(Class clas) {
        Set objetos = colecaoDeObjetos(clas);
        return new ArrayList(objetos);
    }

    private Set colecaoDeObjetos(Class clas) {
        Set objetos = (Set) BancoDeDados.OBJETOS.get(clas);
        if (objetos == null) {
            objetos = new HashSet();
            BancoDeDados.OBJETOS.put(clas, objetos);
        }
        return objetos;
    }

    public Long proximoId() {
        id = id + 1;
        return id;
    }

}
