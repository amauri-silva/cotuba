package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public interface GeradorEbook {
	void gera(Ebook ebook);

	public static GeradorEbook cria(FormatoEbook formato) {
		GeradorEbook gerador = formato.getGerador();

		if (gerador == null) {
			throw new RuntimeException("Formato do ebook inválido: " + formato);
		}

		return gerador;
	}
}