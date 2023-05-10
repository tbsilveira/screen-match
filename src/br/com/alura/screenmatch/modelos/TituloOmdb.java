package br.com.alura.screenmatch.modelos;

public class TituloOmdb {
    private String title;
    private String year;
    private String runtime;
    private String response;

    public TituloOmdb(String title, String year, String runtime, String response) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.response = response;
    }

    public String duracaoEmMinutosAjustada() {
        return getRuntime().replace(" min", "");
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getResponse() {
        return response;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

}