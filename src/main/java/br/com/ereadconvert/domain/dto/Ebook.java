package br.com.ereadconvert.domain.dto;

import java.nio.file.Path;
import java.util.List;


public class Ebook {

    private String format;
    private Path arquivoDeSaida;
    private List<Chapter> chapter;
    
    public String getFormato() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }
    public void setArquivoDeSaida(Path arquivoDeSaida) {
        this.arquivoDeSaida = arquivoDeSaida;
    }
    public List<Chapter> getChapter() {
        return chapter;
    }
    public void setCaptulo(List<Chapter> chapter) {
        this.chapter = chapter;
    }
    
}
