package cotuba;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class LeitorOpcoesCLI {

    public LeitorOpcoesCLI(String[] args) {
    	Options options = new Options();

		Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
				"Diretório que contem os arquivos md. Default: diretório atual.");
		options.addOption(opcaoDeDiretorioDosMD);

		Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
				"Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
		options.addOption(opcaoDeFormatoDoEbook);

		Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
				"Arquivo de saída do ebook. Default: book.{formato}.");
		options.addOption(opcaoDeArquivoDeSaida);

		Option opcaoModoVerboso = new Option("v", "verbose", false,
				"Habilita modo verboso.");
		options.addOption(opcaoModoVerboso);
		
		Options opcoesCLI = options;

		CommandLineParser cmdParser = new DefaultParser();
		HelpFormatter ajuda = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = cmdParser.parse(opcoesCLI, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			ajuda.printHelp("cotuba", opcoesCLI);
			System.exit(1);
			return;
		}
    }

  }