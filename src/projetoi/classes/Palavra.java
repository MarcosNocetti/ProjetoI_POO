/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoi.classes;

/**
 *
 * @author gabri
 */
public class Palavra {

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getPalavraProxima() {
        return PalavraProxima;
    }

    public void setPalavraProxima(String PalavraProxima) {
        this.PalavraProxima = PalavraProxima;
    }

    public Palavra(String Nome, String PalavraProxima) {
        this.Nome = Nome;
        this.PalavraProxima = PalavraProxima;
    }
    String Nome;
    String PalavraProxima;    
}
