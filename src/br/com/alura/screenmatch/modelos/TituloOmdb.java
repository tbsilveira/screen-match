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

    public void validaAnoDeLancamento(String year){
        if(this.getYear().length() != 4) {
            this.setYear(String.valueOf(1900));
            System.out.println("## Atenção: Ano de lançamento inválido na base de dados ##");
        }
    }

    public void validaDuracaoEmMinutos(String runtime) {
        String regex = "[a-zA-Z]";
        if(this.getRuntime().substring(0,1).matches(regex)){
            this.setRuntime(String.valueOf(1));
            System.out.println("## Atenção: Duração em minutos inválida na base de dados ##");
        }
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