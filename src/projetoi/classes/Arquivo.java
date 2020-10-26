/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoi.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class Arquivo {
    String NomeArq;
    
    public String getTexto(String NomeArq){
        try{
            StringBuilder conteudo = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(NomeArq));
            while (reader.ready()) {
                String linha = reader.readLine();
                conteudo.append(linha);
            }
            reader.close();
            return conteudo.toString();
         }catch(IOException e){
         
         }   
         return "."; 
        
}
    public static List<String> getPalavrasDoTexto(String conteudoTexto) throws IOException {
        List <String> listaDePalavrasDoTexto = new ArrayList<>();
        String palavra = "";
        int posicaoInicioBusca = 0;
        for (Character caracter : conteudoTexto.toCharArray()) {
            if (Character.isAlphabetic(caracter)) { // verificação para armazenar somente letras
                palavra += caracter;
            } else {
                if (!palavra.isEmpty() && !listaDePalavrasDoTexto.contains(palavra.toLowerCase())) { // verificação para não pegar a palavra novamente caso já tenha encontrado antes
                    int posicaoDeInicio = conteudoTexto.indexOf(palavra, posicaoInicioBusca); // aqui pegamos a posição da palavra a partir da posicao da ultima palavra
                    posicaoInicioBusca = posicaoDeInicio;

                    listaDePalavrasDoTexto.add(palavra.toLowerCase());
                }

                palavra = "";
            }
        }

        return listaDePalavrasDoTexto;
    }

    private void abreArquivoResposta() throws IOException {
        List<String> Palavra = getPalavrasDoTexto(getTexto(NomeArq));
        try{
            File f = new File("resultado.txt");
            boolean existe = f.exists();
                if(existe == false){
                    boolean criar = f.createNewFile();
                    if (criar){
                        FileWriter arquivo = new FileWriter(f);
                        BufferedWriter grava = new BufferedWriter(arquivo);
                        grava.write(Palavra.toString());
                        grava.close();
                        arquivo.close();
                    }
                    else{                      
                        FileWriter arquivo = new FileWriter(f);
                        BufferedWriter grava = new BufferedWriter(arquivo);
                        grava.write(Palavra.toString());
                        grava.close();
                        arquivo.close();
                    }
            }else System.out.println("Não criou nada");

            }catch(IOException e){
                    System.out.println("Erro: "+e.toString());
            }
    }
}
    


