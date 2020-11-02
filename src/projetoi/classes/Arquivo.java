/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoi.classes;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Arquivo {

  


    public ArrayList<String> getGrafo() {
        return grafo;
    }

    public ArrayList<String> getOrdenacaoAlfabetica() {
        return OrdenacaoAlfabetica;
    }

    public void setOrdenacaoAlfabetica(ArrayList<String> OrdenacaoAlfabetica) {
        this.OrdenacaoAlfabetica = OrdenacaoAlfabetica;
    }

    public StringBuilder getBuilder_string() {
        return builder_string;
    }

    public void setBuilder_string(StringBuilder builder_string) {
        this.builder_string = builder_string;
    }

    public void setGrafo(ArrayList<String> grafo) {
        this.grafo = grafo;
    }
    public String format_words(String words) {
        return words.toLowerCase();
    }
    
    private ArrayList<String> grafo = new ArrayList();
    private ArrayList<String> OrdenacaoAlfabetica = new ArrayList<>();
    private StringBuilder builder_string = new StringBuilder();

    public List<String> ChecarRepeticao() {
            Set<String> Filtrada = new LinkedHashSet<>(getOrdenacaoAlfabetica());
            List<String> Lista = new ArrayList<>(Filtrada);
            return Lista;
    }
    public ArrayList<String> alphabetic_order(ArrayList<String> list) {
        Collections.sort(list);
        return list;
    }

	public void get_data_archives(String path) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"))) {
			String line = br.readLine();
			while (line != null) {
				String[] wordsFromline;
                            wordsFromline = line.split("[!-.:-@\\s]");
				for (String word : wordsFromline) {
					if (!"".equals(word.trim())) {
						this.grafo.add(word);
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error:" + e);
		}
	}

	public void process_elements() {
		try {
			for (int i = 0; i < getGrafo().size(); i++) {
				getGrafo().set(i, format_words(getGrafo().get(i)));
				getOrdenacaoAlfabetica().add(i, getGrafo().get(i));
			}
			setOrdenacaoAlfabetica(alphabetic_order(getOrdenacaoAlfabetica()));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error:" + e);
		}

	}
    


public void Saida() {
		try {
			int count = 0;
			int countOc = 0;
			int countRep = 0;
			ArrayList<Integer> Posicao = new ArrayList<>();
			ArrayList<String> Palavra = new ArrayList<>();
			while (count < ChecarRepeticao().size()) {
				countOc = 0;
				countRep = 0;
				Posicao.clear();
				Palavra.clear();
                                int i = 0;
				while (i < getGrafo().size() - 1) {
					if (ChecarRepeticao().get(count).equals(getGrafo().get(i))) {
						Posicao.add(i);
						countOc++;
					}
                                         i++;
				}
				if (countOc == 1) {
					getBuilder_string().append(ChecarRepeticao().get(count)).append(",").append(getGrafo().get(Posicao.get(0) + 1)).append('\r');
				} else {
                                        i = 0;
					while (i < countOc) {
                                            
						if (countRep == 0) {
							Palavra.add(getGrafo().get(Posicao.get(i) + 1));
							countRep++;
							getBuilder_string().append(ChecarRepeticao().get(count)).append(",").append(getGrafo().get(Posicao.get(i) + 1));
						} else if (Palavra.contains(getGrafo().get(Posicao.get(i) + 1))) {
						} else {
							Palavra.add(getGrafo().get(Posicao.get(i) + 1));
							getBuilder_string().append(",").append(getGrafo().get(Posicao.get(i) + 1));
						}
                                                i++;
					}
					getBuilder_string().append("\r");
				}
				count++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:" + e);
		}
	}

    
    public void EscreverLista(String path, String ArquivoCSV) {
		try {
			PrintWriter printWriter = new PrintWriter(
					new File(path)+"\\"+ArquivoCSV+".csv");
			Saida();// Proper output
			printWriter.write(getBuilder_string().toString());
			printWriter.close();
			JOptionPane.showMessageDialog(null, "Resposta escrita em: "+ArquivoCSV+"\n Local: "+path+"\\"+ArquivoCSV+".csv");
		} catch (HeadlessException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error:" + e);
		}
    }

}

    


