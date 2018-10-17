package cotuba.domain;

import java.nio.file.Path;
import java.util.List;

public class Ebook implements cotuba.plugin.Ebook {
	private FormatoEbook formato;
	private List<Capitulo> capitulos;
	private Path ArquivoDeSaida;
	
	public FormatoEbook getFormato() {
		return formato;
	}
	public void setFormato(FormatoEbook formato) {
		this.formato = formato;
	}
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	public Path getArquivoDeSaida() {
		return ArquivoDeSaida;
	}
	public void setArquivoDeSaida(Path arquivoDeSaida) {
		ArquivoDeSaida = arquivoDeSaida;
	}
}
