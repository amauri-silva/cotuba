package cotuba.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public interface Tema {
	String cssDoTema();

	public static List<String> listaDeTemas() {
		ArrayList<String> temas = new ArrayList<>();

		ServiceLoader<Tema> loader = ServiceLoader.load(Tema.class);
		for (Tema plugin : loader) {
			String css = plugin.cssDoTema();
			temas.add(css);
		}

		return temas;
	}
}
