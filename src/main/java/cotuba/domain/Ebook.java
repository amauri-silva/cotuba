package cotuba.domain;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class Ebook {
	private final FormatoEbook formato;
	private final List<Capitulo> capitulos;
	private final Path arquivoDeSaida;

	public Ebook(FormatoEbook formato, List<Capitulo> capitulos, Path arquivoDeSaida) {
		this.formato = formato;
		this.capitulos = Collections.unmodifiableList(capitulos);
		this.arquivoDeSaida = arquivoDeSaida;
	}

	public FormatoEbook getFormato() {
		return formato;
	}

	public List<Capitulo> getCapitulos() {
		return Collections.unmodifiableList(capitulos);
	}

	public Path getArquivoDeSaida() {
		return arquivoDeSaida;
	}
}
