import java.util.LinkedList;

class Elemento {
    int chave;
    String valor;

    public Elemento(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public int getChave() {
        return chave;
    }

    public String getValor() {
        return valor;
    }
}

class TabelaHashDupla<E> {
    private LinkedList<E>[][] tabela;
    private int tamanhoNivel1;
    private int tamanhoNivel2;

    public TabelaHashDupla(int tamanhoNivel1, int tamanhoNivel2) {
        this.tamanhoNivel1 = tamanhoNivel1;
        this.tamanhoNivel2 = tamanhoNivel2;
        tabela = new LinkedList[tamanhoNivel1][tamanhoNivel2];
        for (int i = 0; i < tamanhoNivel1; i++) {
            for (int j = 0; j < tamanhoNivel2; j++) {
                tabela[i][j] = new LinkedList<>();
            }
        }
    }

    private int hashNivel1(int chave) {
        return chave % tamanhoNivel1;
    }

    private int hashNivel2(int chave) {
        return chave % tamanhoNivel2;
    }

    public void inserir(int chave, E elemento) {
        int indiceNivel1 = hashNivel1(chave);
        int indiceNivel2 = hashNivel2(chave);
        tabela[indiceNivel1][indiceNivel2].add(elemento);
    }

    public E buscar(int chave) {
        int indiceNivel1 = hashNivel1(chave);
        int indiceNivel2 = hashNivel2(chave);
        LinkedList<E> lista = tabela[indiceNivel1][indiceNivel2];
        
        for (E elemento : lista) {
            if (elemento instanceof Elemento) {
                Elemento elementoChave = (Elemento) elemento;
                if (elementoChave.getChave() == chave) {
                    return elemento;
                }
            }
        }
        
        return null;
    }

    public void remover(int chave, E elemento) {
        int indiceNivel1 = hashNivel1(chave);
        int indiceNivel2 = hashNivel2(chave);
        LinkedList<E> lista = tabela[indiceNivel1][indiceNivel2];

        lista.remove(elemento);
    }
}

public class TesteTabela {
    public static void main(String[] args) {
        TabelaHashDupla<Elemento> tabelaHash = new TabelaHashDupla<>(10, 10);

        Elemento elemento1 = new Elemento(5, "Objeto 1");
        Elemento elemento2 = new Elemento(15, "Objeto 2");

        tabelaHash.inserir(elemento1.getChave(), elemento1);
        tabelaHash.inserir(elemento2.getChave(), elemento2);

        Elemento resultado = tabelaHash.buscar(5);
        if (resultado != null) {
            System.out.println("Resultado da busca: " + resultado.getValor());
        } else {
            System.out.println("Chave não encontrada.");
        }

        tabelaHash.remover(5, elemento1);
    }
}
