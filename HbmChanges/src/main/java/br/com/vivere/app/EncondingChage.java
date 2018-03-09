package br.com.vivere.app;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class EncondingChage {
	
	public static void main(String[] args) throws IOException {
		
		String diretorio = "C:\\workarea_mva_dev\\workspace\\vivere-param2(7ee73953)\\vivere-param2\\vivere-param2-est.app\\src\\main\\resources\\hibernate\\domain\\com\\viverebrasil\\app\\parametrizador\\com\\viverebrasil\\app\\parametrizador";
		
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
		
    }

}
