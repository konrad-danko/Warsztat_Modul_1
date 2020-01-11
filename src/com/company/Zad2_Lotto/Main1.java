/*Symulator LOTTO
Jak wszystkim wiadomo, LOTTO to gra liczbowa polegająca na losowaniu 6 liczb z zakresu od 1 do 49.
Zadaniem gracza jest poprawne wytypowanie losowanych liczb. Nagradzane jest trafienie 3, 4, 5 lub 6
poprawnych liczb.
Napisz program, który:
- zapyta o typowane liczby, przy okazji sprawdzi następujące warunki:
* czy wprowadzony ciąg znaków jest poprawną liczbą,
* czy użytkownik nie wpisał tej liczby już poprzednio,
* czy liczba należy do zakresu 1-49,
- po wprowadzeniu 6 liczb, posortuje je rosnąco i wyświetli na ekranie,
- wylosuje 6 liczb z zakresu i wyświetli je na ekranie,
- poinformuje gracza, czy trafił przynajmniej "trójkę".*/

package com.company.Zad2_Lotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Zaczynamy grę w LOTTO.");
        System.out.println("Jest to gra liczbowa polegająca na losowaniu 6 liczb z zakresu od 1 do 49.");
        System.out.println("Zadaniem gracza jest poprawne wytypowanie losowanych liczb.");
        System.out.println("Nagradzane jest trafienie 3, 4, 5 lub 6 poprawnych liczb.");

        //wybieranie liczb przez użytkownika:
        int[] userNumbs = new int[6];
        for (int i=0 ; i<userNumbs.length ; i++) {
            System.out.print("\n" + "Podaj liczbę nr " + (i+1)+": ");
            while (true) {
                int goFlag = 1;
                int userNumb = getIntValue();
                if (userNumb<1 || userNumb>49) {//sprawdzenie czy liczba jest z przedziału 1-49
                    System.out.print("Liczba z przedziału 1-49. Jeszcze raz: ");
                    goFlag=0;
                }
                for (int j=0; j<i ;j++) {
                    if (userNumb==userNumbs[j]) {//sprawdzenie czy liczba jest "nowa"
                        System.out.print("To już było. Jeszcze raz: ");
                        goFlag=0;
                    }
                }
                if (goFlag==1) {
                    userNumbs[i] = userNumb; //przypisanie wybranej liczby do tablicy
                    System.out.println("Wybrałeś liczbę: " + userNumb);
                    break;
                }
            }
        }
        Arrays.sort(userNumbs);
        System.out.println("\n" + "Wybrałeś następujące liczby: " + Arrays.toString(userNumbs));

        //losowanie liczb przez komputer:
        int[] randNumbs = new int[6];
        Random rand = new Random();
        for (int i=0 ; i<randNumbs.length ; i++) {
            while (true) {
                int goFlag = 1;
                int randNumb = rand.nextInt(49)+1; //losowanie liczby int z zakresu 1-49:
                for (int j=0; j<i ;j++) {
                    if (randNumb==randNumbs[j]) {//sprawdzenie czy wylosowana liczba jest "nowa"
                        goFlag=0;
                    }
                }
                if (goFlag==1) {
                    randNumbs[i] = randNumb; //przypisanie wylosowanej liczby do tablicy
                    break;
                }
            }
        }
        Arrays.sort(randNumbs);
        System.out.println("Wylosowano następujące liczby: " + Arrays.toString(randNumbs)+"\n");

        //porównanie obu zbiorów liczb:
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i : userNumbs) {
            for (int j : randNumbs) {
                if (i == j) {
                    counter++;
                    sb.append(i).append(", ");
                }
            }
        }
        System.out.println("Ilość trafień: " + counter);
        if (counter==0) {
            System.out.println("Brak celnych trafień.");
        } else System.out.println("Celne trafienia to: " + sb.toString());
    }
    static int getIntValue () {//metoda na pobieranie int-ów z konsoli:
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.print("To nie jest liczba. Jeszcze raz: ");
        }
        int result = scan.nextInt();
        scan.nextLine();
        return result;
    }
}

