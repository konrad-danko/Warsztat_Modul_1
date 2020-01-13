/*Gra w zgadywanie liczb 2
Odwróćmy teraz sytuację z warsztatu "Gra1": to użytkownik pomyśli sobie liczbę z
zakresu 1-1000, a komputer będzie zgadywał i zrobi to maksymalnie w 10 ruchach (pod warunkiem,
że gracz nie będzie oszukiwał).
Zadaniem gracza będzie udzielanie odpowiedzi "więcej", "mniej", "trafiłeś".*/

package com.company.Zad3_Gra_zgad_liczb2;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Zaczynamy grę w zgadywanie liczb.");
        System.out.println("Tym razem Ty pomyśl sobie liczbę całkowitą z zakresu 1-1000, a ja ją zgadnę w max. 10-u próbach."+"\n");
        int counter = 0; //licznik prób odgadnięcia liczby
        int min = 1;
        int max = 1000;
        int dispGuess = 1; //parametr sterujący wyświetleniem komunikatu 'guess'
        Scanner skan = new Scanner(System.in);
        while (true) {
            int guess = (max-min)/2 + min;
            if (dispGuess==1) {//ten komunikat wyświetla się w zależności od parametru sterującego
                System.out.println("Zgaduję, że Twoja liczba to: " + guess);
                System.out.println("Jaka jest odpowiedź? 'za dużo', 'za mało', 'zgadłeś'?");
            }
            String odp = skan.nextLine();
            if (odp.equals("zgadłeś")) {
                counter++;
                System.out.println("\n"+"OK. Wygrałem w " + counter + " krokach.");
                break;
            } else if (odp.equals("za dużo")) {
                counter++;
                max=guess;
                dispGuess = 1;
            } else if (odp.equals("za mało")) {
                counter++;
                min=guess;
                dispGuess = 1;
            } else {
                System.out.println("Poproszę o bardziej precyzyjną odpowiedź.");
                dispGuess = 0;
            }
        }
    }
}




