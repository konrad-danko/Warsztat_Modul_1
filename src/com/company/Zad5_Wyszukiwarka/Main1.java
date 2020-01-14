/*Wyszukiwarka najpopularniejszych słów
Wyszukaj w popularnych serwisach internetowych nagłówków artykułów, a następnie zapisz
pojedyncze słowa w nich występujące do pliku o nazwie popular_words.txt .

Wywołaj pobieranie dla wybranych serwisów internetowych.
Pomiń wszystkie elementy krótsze niż 3-znakowe.
Utwórz tablicę elementów wykluczonych np. oraz, ponieważ
Wczytaj utworzony plik popular_words.txt i na jego podstawie utwórz plik
filtered_popular_words.txt , który zawierać będzie wszystkie znalezione słowa, pomijając słowa
wykluczone.*/

package com.company.Zad5_Wyszukiwarka;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) throws IOException {

        //pobieranie danych z Internetu i zapis do pliku:
        //deklaracja obiektu klasy Connection (ściąganie danych z Internetu)
        Connection connect = Jsoup.connect("https://www.onet.pl/");
        FileWriter filWrit = new FileWriter("popular_words.txt", false);
        //deklaracja tablicy ze znakami do usunięcia, na wszelki wypadek niektóre są wyescapowane
        String[] charsRemoved = {"\"","\\.","\\+","\\[","\\]","\\?","'","–","«","»","-","!",":",","};
        try {
            Document document = connect.get(); //ściąganie danych z Internetu
            Elements links = document.select("span.title"); //ściąganie danych z Internetu
            for (Element elem : links) {
                String line = elem.text(); //zamiana na String obiektu klasy Element
                System.out.println(line);
                for (String s: charsRemoved) {
                    line = line.replaceAll(s , "");
                }
                String[] words = line.split(" ");
                for (String s: words) {
                    if (s.length()>3) {
                        filWrit.append(s).append("\n");
                    }
                }
            }
            filWrit.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tablica słów wykluczonych
        String[] excluded = {"oraz","ponieważ","albo","tylko"};

        //Zapis do nowego pliku z wykluczeniem słów z tabeli "excluded"
        File filRead = new File("popular_words.txt");
        FileWriter filWritFilt = new FileWriter ("filtered_popular_words.txt",false);
        Scanner scan = new Scanner(filRead);
        int goFlag;
        try {
            while (scan.hasNextLine()) {
                String lineRead = scan.nextLine();
                goFlag=0;
                for (String s : excluded) {
                    if (s.equalsIgnoreCase(lineRead)) {
                        goFlag = 1;
                        break;
                    }
                }
                if (goFlag==0) {
                    filWritFilt.append(lineRead).append("\n");
                }
            }
            filWritFilt.close();
        } catch (FileNotFoundException ex1) {
            System.out.println(ex1);
        }
    }
}
