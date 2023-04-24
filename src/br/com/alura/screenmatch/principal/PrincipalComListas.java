package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args) {

        Filme meuFilme = new Filme("O Poderoso Chefão", 1970);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.avalia(6);
        Filme filmeDoPaulo = new Filme("Dogville", 2003);
        filmeDoPaulo.avalia(10);
        Serie umaSerie = new Serie("Lost", 2000);
        Serie outraSerie = new Serie("Vikings", 2008);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoPaulo);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(umaSerie);
        lista.add(outraSerie);

        for (Titulo item: lista) {
            System.out.println(item);
            if (item instanceof Filme filme) {
                System.out.println("Classificação: " + filme.getClassificacao());
            } else {
                System.out.println("Classificação: N/A");
            }
        }
        List<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("John Travolta");
        buscaPorArtista.add("George Clooney");
        buscaPorArtista.add("Morgan Freeman");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Lista de artistas ordenados:");
        System.out.println(buscaPorArtista);
        System.out.println("Lista de títulos ordenada por nome:");
        Collections.sort(lista);
        System.out.println(lista);

        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Lista de títulos ordenada por ano de lançamento");
        System.out.println(lista);

    }
}
