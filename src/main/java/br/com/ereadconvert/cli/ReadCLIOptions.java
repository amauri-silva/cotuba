package br.com.ereadconvert.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.itextpdf.kernel.log.SystemOutCounter;

public class ReadCLIOptions {

    // TODO colocar no MAIN
    Path diretorioDosMD;
    String formato;
    Path arquivoDeSaida;
    boolean modoVerboso = false;
    CommandLine cmd;
    String[] args;


    public ReadCLIOptions(String[] args) {
        this.args = args;
    }


    public Path getDosMDDiretory() {

        try {
            String nomeDoDiretorioDosMD = cmd.getOptionValue("dir");

            if (nomeDoDiretorioDosMD != null) {
                diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
                if (!Files.isDirectory(diretorioDosMD)) {
                    throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
                }
            } else {
                Path diretorioAtual = Paths.get("");
                diretorioDosMD = diretorioAtual;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return diretorioDosMD;
    }


    public String getFormat() {

        try {
            
            String nomeDoFormatoDoEbook = cmd.getOptionValue("format");

            if (nomeDoFormatoDoEbook != null) {
                formato = nomeDoFormatoDoEbook.toLowerCase();
            } else {
                formato = "pdf";
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        return formato;
    }

    
    
    public Path getOutputFile() {

        try {
            String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue("output");
            
            if (nomeDoArquivoDeSaidaDoEbook != null) {
                arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
                
                if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
                    throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
                }
            } else {
                arquivoDeSaida = Paths.get("book." + formato.toLowerCase());
            }

            modoVerboso = cmd.hasOption("verbose");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
        return arquivoDeSaida;
    }

    
    public Boolean isVerboseMode() {

        try {
            modoVerboso = cmd.hasOption("verbose");
            if(modoVerboso)
                return modoVerboso;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
        return modoVerboso;
    }


    
    //TODO verificar o que será feito com esse código
    public void runCli() {
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

        Option opcaoModoVerboso = new Option("v", "verbose", false, "Habilita modo verboso.");
        options.addOption(opcaoModoVerboso);
        options.addOption(new Option("h", "help", false, "Imprimir esta lista de opções do programa."));

        CommandLineParser cmdParser = new DefaultParser();
        HelpFormatter ajuda = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = cmdParser.parse(options, this.args);
            if (cmd.hasOption("help")) {
                ajuda.printHelp("./cotuba.sh", options);
                System.exit(1);
                return;
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            ajuda.printHelp("./cotuba.sh", options);
            System.exit(1);
            return;
        }
    }
}
