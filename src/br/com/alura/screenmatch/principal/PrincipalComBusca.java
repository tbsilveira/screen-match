package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        String busca = "";

        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("\nDigite um filme para busca ou [SAIR] para encerrar o programa:");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")) {
                break;
            }

            String baseUrl = System.getenv("OMDB_BASE_URL");
            String apiKey = System.getenv("OMDB_API_KEY");
            URL url = new URL(baseUrl + URLEncoder.encode(busca, StandardCharsets.UTF_8) + apiKey);

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.valueOf(url)))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                String json = response.body();
                
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

                if(meuTituloOmdb.getResponse().equals("True")) {
                    meuTituloOmdb.validaAnoDeLancamento(meuTituloOmdb.getYear());
                    meuTituloOmdb.validaDuracaoEmMinutos(meuTituloOmdb.getRuntime());
                }

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido:");
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de argumento. Verifique o endereço digitado.");
                System.out.println("Mensagem de Erro: " + e.getMessage());
//          }   catch (RuntimeException e) {
//              System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

//        LocalTime myTime = LocalTime.now();
//        DateTimeFormatter myTimeFormatted = DateTimeFormatter.ofPattern("HH-mm-ss");
//        String formattedTime = myTime.format(myTimeFormatted);
//        FileWriter escrita = new FileWriter("filmes-"+ formattedTime +".json");
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("\n*** programa finalizado ***");
    }
}