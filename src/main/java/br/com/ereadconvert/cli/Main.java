package br.com.ereadconvert.cli;

import java.nio.file.Path;

import br.com.ereadconvert.application.Cotuba;

public class Main {

    public static void main(String[] args) {

        ReadCLIOptions cliOptins = new ReadCLIOptions(args);

        String format = cliOptins.getFormat();
        Path diretorioDosMD = cliOptins.getDosMDDiretory();
        Path outputFile = cliOptins.getOutputFile();
        boolean verboseMode = cliOptins.isVerboseMode();

        try {
            Cotuba cotuba = new Cotuba();
            cotuba.execute(format, diretorioDosMD, outputFile);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (verboseMode)
                ex.printStackTrace();
        }


    }

}
