package view;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.SocketHandler;

public class EntradaVerificada {
    private Scanner scan;

    public EntradaVerificada(Scanner scan){
        this.scan = scan;
    }

    public Integer receberInteger(){
        try{
            Integer result = this.scan.nextInt();
            this.scan.nextLine();
            return result;
        }catch (Exception e){
            System.out.print("DIGITE UM NÚMERO: ");
            this.scan.nextLine();
            return receberInteger();
        }
    }

    public LocalDateTime receberLocalData(){
        try {
            System.out.print("digite o dia: ");
            Integer dia = receberInteger();
            System.out.print("digite o mês: ");
            Integer mes = receberInteger();
            System.out.print("digite o ano: ");
            Integer ano = receberInteger();
            System.out.print("digite a hora: ");
            Integer hora = receberInteger();
            System.out.print("digite os minutos: ");
            Integer minuto = receberInteger();
            return LocalDateTime.of(ano,mes,dia,hora,minuto);
        }catch (Exception e){
            System.out.println("DIGITE UMA DATA VALIDA");
            return receberLocalData();
        }
    }

    public void scanClose(){
        this.scan.close();
    }

    public Long receberLong(){
        try {
            Long leitura = this.scan.nextLong();
            this.scan.nextLine();
            return leitura;
        }catch (Exception e){
            System.out.println("Formato invalido");
            System.out.print("Digite novamente: ");
            return receberLong();
        }
    }

    public String receberString(){
        return scan.nextLine();
    }
}
