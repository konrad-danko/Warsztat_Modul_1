/*Gra w zgadywanie liczb
Napisz prostą grę w zgadywanie liczb. Komputer ma wylosować liczbę w zakresie od 1 do 100.
Następnie:
1. wypisać: "Zgadnij liczbę" i pobrać liczbę z klawiatury;
2. sprawdzić, czy wprowadzony napis, to rzeczywiście liczba i w razie błędu wyświetlić komunikat: "To
nie jest liczba", po czym wrócić do pkt. 1;
3. jeśli liczba podana przez użytkownika jest mniejsza niż wylosowana, wyświetlić komunikat: "Za
mało!", po czym wrócić do pkt. 1;
4. jeśli liczba podana przez użytkownika jest większa niż wylosowana, wyświetlić komunikat: "Za
dużo!", po czym wrócić do pkt. 1;
5. jeśli liczba podana przez użytkownika jest równa wylosowanej, wyświetlić komunikat: "Zgadłeś!",
po czym zakończyć działanie programu.*/

package com.company.Zad1_Gra_zgad_liczb1;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        System.out.println("Zaczynamy grę w zgadywanie liczb."+"\n");
        Random rand = new Random();
        int randNumb = rand.nextInt(100)+1; //przypisanie losowej liczby int z zakresu 1-100:
        while (true) {
            int userNumb = getIntValue();
            if (userNumb>randNumb) {
                System.out.println("Za dużo!" + "\n");
            } else if(userNumb<randNumb) {
                System.out.println("Za mało!" + "\n");
            } else break;
        }
        System.out.println("Zgadłeś!"+"\n"+"\n"+"Koniec gry.");
    }
    static int getIntValue () {//metoda na pobieranie int-ów z konsoli:
        System.out.print("Zgadnij liczbę: ");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.print("To nie jest liczba."+"\n"+"Zgadnij liczbę: ");
        }
        int result = scan.nextInt();
        scan.nextLine();
        return result;
    }
}