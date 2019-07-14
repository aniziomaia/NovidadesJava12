package com.br.code.java.io;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static java.nio.file.StandardCopyOption. *;

/**
 *
 * @author aniziomaia
 */
public class Javaio {
 
	public static void main(String[] args) throws IOException {
	    
//		atualizarOffLine("OLA, ISTO É SOMENTE UM TESTE DE LEITURA E CRIACAO DE ARQUIVOS EM JAVA. \n" + "TEXTO COM DUAS LINHAS");
//		
//		System.out.println(carregarOffLine("e:\\teste\\tabuada_15_16_2019.txt"));
//		
//		transferindoImagemComPath();
//		
//		lendoUmFileComPath();
//		
//		movendoUmFileComPath();
		
//		copiandoUmDirComChannel();//nao funciona para dir
//		
//		copiandoUmDirComStream();//nao funciona para dir
//		
//		movendoUmDirComPath();//deve ser executado sozinho
//		
//		usandoScaner();
		
		copiandoUmDirComPath();//deve ser executado sozinho
		
	 
	}
	
	private static void copiandoUmDirComPath() throws IOException {
		// Primeiro utilize o Path para localizar o arquivo
		Path pathOrigem = Paths.get("e:\\teste");
		Path pathDestino = Paths.get("e:\\teste2");

		try {
			
			//modo 1:Em alguns sistemas operacionais, pode não ser possível remover um 
			//arquivo quando ele estiver aberto e em uso por essa máquina virtual Java ou outros programas.
			CustomFileVisitor fileVisitor = new CustomFileVisitor(pathOrigem, pathDestino);
	        //You can specify your own FileVisitOption
	        
			Path temp = Files.walkFileTree(pathOrigem, fileVisitor);
			if(temp != null) { 
	            System.out.println("Diretorio copiado..."); 
	        } 
	        else{ 
	        	System.out.println("Diretorio nao copiado..."); 
	        } 
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void copiandoUmDirComChannel() throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(new File("e:\\teste")).getChannel();
	        destChannel = new FileOutputStream(new File("e:\\teste2")).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
	/**
	 * modo antigo 1
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copiandoUmDirComStream() throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(new File("e:\\teste"));
	        os = new FileOutputStream(new File("e:\\teste2"));
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
	        is.close();
	        os.close();
	    }
	}
	
	private static void movendoUmDirComPath(){
		// Primeiro utilize o Path para localizar o arquivo
		Path pathOrigem = Paths.get("e:\\teste2");
		Path pathDestino = Paths.get("\\\\192.168.0.17\\teste");

		try {
			
			//modo 1:Em alguns sistemas operacionais, pode não ser possível remover um 
			//arquivo quando ele estiver aberto e em uso por essa máquina virtual Java ou outros programas.
			Path temp = Files.move(pathOrigem, pathDestino,REPLACE_EXISTING);
			if(temp != null) { 
	            System.out.println("File renamed and moved successfully"); 
	        } 
	        else{ 
	            System.out.println("Failed to move the file"); 
	        } 
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void movendoUmFileComPath(){
		// Primeiro utilize o Path para localizar o arquivo
		Path pathOrigem = Paths.get("e:\\teste\\tabuada_15_16_2019.txt");
		Path pathDestino = Paths.get("e:\\teste\\destino\\tabuada_15_16_2019.txt");

		try {
			
			//modo 1
			Path temp = Files.move(pathOrigem, pathDestino);
			if(temp != null) { 
	            System.out.println("File renamed and moved successfully"); 
	        } 
	        else{ 
	            System.out.println("Failed to move the file"); 
	        } 
			
			//modo 2:  Executa a movimentação mesmo quando o arquivo de destino já existe
			Path temp2 = Files.move(pathOrigem, pathDestino,REPLACE_EXISTING);
			if(temp2 != null) { 
	            System.out.println("File renamed and moved successfully"); 
	        } 
	        else{ 
	            System.out.println("Failed to move the file"); 
	        } 
			
			//modo 3: Se o sistema de arquivos não suportar um movimento atômico, uma exceção será lançada. 
			//Com um ATOMIC_MOVEvocê pode mover um arquivo em um diretório e ter a garantia de que 
			//qualquer processo que esteja assistindo o diretório acesse um arquivo completo.
			Path temp3 = Files.move(pathOrigem, pathDestino,ATOMIC_MOVE);
			if(temp3 != null) { 
	            System.out.println("File renamed and moved successfully"); 
	        } 
	        else{ 
	            System.out.println("Failed to move the file"); 
	        } 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void lendoUmFileComPath(){
		// Primeiro utilize o Path para localizar o arquivo
		Path path = Paths.get("e:\\teste\\tabuada_15_16_2019.txt");

		try {
			//modo 1
			//List<String> linhasArquivo = Files.readAllLines(path, Charset.forName("UTF-8"));
			List<String> linhasArquivo = Files.readAllLines(path, Charset.forName("ISO-8859-1"));
			for (String linha : linhasArquivo) {
			    System.out.println( linha ); 
			}
			
			//modo 2
			StringBuilder sb = new StringBuilder();
			Files.lines(path,  Charset.forName("ISO-8859-1")).forEach(s -> sb.append(s));
			System.out.println("\n" + sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void transferindoImagemComPath() throws IOException {
		// Primeiro utilize o Path para localizar o arquivo
		Path path = Paths.get("e:\\teste\\eu.jpg");

		// Tendo o path pode carregar o arquivo
		byte[] bytesArquivo = Files.readAllBytes(path);

		// gravamos os bytes em outro arquivo
		Path pathTo = Paths.get("e:\\teste\\img\\eu.jpg");
		Files.write(pathTo, bytesArquivo);
	}
  
	private static void atualizarOffLine(String listaEmployee) {

		FileWriter arq;
		try {
			File dir = new File("e:\\teste");
			dir.mkdir(); 
			
			arq = new FileWriter("e:\\teste\\tabuada_15_16_2019.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(listaEmployee);
			arq.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static String carregarOffLine(String path) {
		StringBuilder resultado = new StringBuilder();
		try {
			  
		      FileReader arq = new FileReader(path);
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine(); 
		      // lê a primeira linha
		      // a variável "linha" recebe o valor "null" quando o processo
		      // de repetição atingir o final do arquivo texto
		      while (linha != null) {
		        System.out.printf("%s\n", linha);
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		        resultado.append(linha);
		      }
		 
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		return resultado.toString().substring(0, resultado.toString().length() - 4);
	}
	
	private static void usandoScaner() {
		String texto = "MEDIDAS DO EXAME\n"
				+ "RESULTADO.0...: @996204@##########    @UN996204@##########      Valores de Referência\n"
				+ "                                                                @TABREFA996204@#######################\n"
				+ "VALORES DE TESTE.2...:@TABREFB996204@#######################";

		// StringBuilder textoAtualizado = new StringBuilder();
		Scanner ler = new Scanner(texto);
		StringBuilder macro = new StringBuilder("");

		int contadorLinha = 0;
		int contadorArroba = 0;
		String TAB = "@TAB";
		boolean novaLinha = false;

		while (ler.hasNext()) {
			contadorLinha++;
			String linha = ler.nextLine();

			boolean temMacro = false;

			if (linha.contains(TAB)) {
				temMacro = true;
				linha = linha.trim() + " ";
				StringBuilder espacoDireita = new StringBuilder("");
				while (temMacro) {
					for (int i = 0; i < linha.length(); i++) {
						char valor = linha.charAt(i);

						if (valor == '@' && contadorArroba != 2) {
							contadorArroba++;
							macro.append(linha.charAt(i));
							if (contadorArroba == 2) {
								continue;
							}
						}
						if (contadorArroba == 0 || valor != '@') {
							continue;
						}
						if (valor != '#' && valor != '@') {// so dados diferente de #
							macro.append(linha.charAt(i));

						}
						if (valor == '#') {// so o espaco apos a macro
							espacoDireita.append(linha.charAt(i));
							macro.append(linha.charAt(i));

						}
						if (valor != '#' && contadorArroba == 2 && valor == ' ') {// faz o replace da macro com o valore
																					// real
							// resultadoExame = resultadoExame.replace(macro, "De um a 5 anos");
							// String s = macro.toString();
							// s.replace(macro.toString(), "De um a 5 anos");
							texto.replace(macro.toString(), "De um a 5 anos");
							contadorArroba = 0;
							temMacro = false;
						}
					}
				}
			} // else textoAtualizado.append(linha);
		}
		System.out.println("\n");
		System.out.println("contadorLinha: " + contadorLinha);
		System.out.println("textoFinal: " + texto);
	}
	
	
}
