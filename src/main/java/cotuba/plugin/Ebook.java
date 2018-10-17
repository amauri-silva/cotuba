package cotuba.plugin;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.FormatoEbook;

public interface Ebook {
	public FormatoEbook getFormato();

	public List<? extends Capitulo> getCapitulos();

	public Path getArquivoDeSaida();
}
