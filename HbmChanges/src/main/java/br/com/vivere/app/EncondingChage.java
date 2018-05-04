package br.com.vivere.app;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import br.com.vivere.util.PropertiesReader;

public class EncondingChage {
	
	public static void main(String[] args) throws IOException {
		
		change();
		
    }
	
	public static void change() throws IOException {
		
		String diretorio = PropertiesReader.getValor("diretorio.hbm");
		
		File file = new File(diretorio);
		File arquivos[] = file.listFiles();
		int i = 0;
		
		for (int j = arquivos.length; i < j; i++) {
			File arquivoHbm = arquivos[i];
			String content = FileUtils.readFileToString(arquivoHbm, "UTF-8");
	        FileUtils.write(arquivoHbm, content, "ISO-8859-1");
	        content = FileUtils.readFileToString(arquivoHbm, "ISO-8859-1");
	        FileUtils.write(arquivoHbm, content, "UTF-8");
	        System.out.println("Enconding Feito: "+(i+1));
		}
		
		System.out.println("Enconding de Todos HBMs realizado com sucesso.");
		
	}

}
