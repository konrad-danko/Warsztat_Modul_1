/*Kostka do gry
Napisz metodę, która:
1. przyjmie w parametrze kod kostki do gry w postaci String,
2. rozpozna wszystkie dane wejściowe:
- rodzaj kostki,
- liczbę rzutów,
- modyfikator,
3. wykona symulację rzutów i zwróci wynik.
Typy kostek występujące w grach:
D3, D4, D6, D8, D10, D12, D20, D100.*/

package com.company.Zad4_Kostka;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Zaczynamy grę w rzut kostką do gry.");
        System.out.println("Dostępne rodzaje kostek to:\nD3, D4, D6, D8, D10, D12, D20, D100.");
        System.out.println("Podaj jaki mam wykonać rzut: ");
        Scanner skan = new Scanner(System.in);
        String diceThrow;

        while (true) { //pobieranie rzutu kostką aż do podania prawidłowego formatu:
            diceThrow = skan.nextLine();
            if (validate(diceThrow)) {
                break;
            } else System.out.println("Błędny format. Jeszcze raz: ");
        }
        String lastInd = diceThrow.substring(diceThrow.length()-1);
        if (lastInd.equals("+") || lastInd.equals("-")) {
            diceThrow = diceThrow +"0";
        }

        //wyliczenie parametrów rzutu kostką:
        //liczba rzutów kostką:
        int indD = diceThrow.indexOf("D");
        int numOfThrows;
        if (indD == 0) {
            numOfThrows = 1;
        } else numOfThrows = Integer.parseInt(diceThrow.substring(0,diceThrow.indexOf("D")));
        System.out.println("Liczba rzutów kostką: " + numOfThrows);

        //rodzaj kostki:
        int indSign; //indeks znaków +/- w 'diceThrow':
        if (diceThrow.contains("+")) {
            indSign = diceThrow.indexOf("+");
        } else if (diceThrow.contains("-")) {
            indSign = diceThrow.indexOf("-");
        } else indSign = diceThrow.length();
        int diceTypeInt = Integer.parseInt(diceThrow.substring(indD+1,indSign));
        System.out.println("Rodzaj kostki: D" + diceTypeInt);

        //liczba, którą należy dodać/odjąć do wyniku rzutów:
        int addNum = 0;
        if (diceThrow.contains("+") || diceThrow.contains("-")  ) {
            addNum = Integer.parseInt(diceThrow.substring(indSign));
        }
        System.out.println("Liczba dodatkowa to: " + addNum);

        //finalne wyliczenie rzutu kostką:
        Random rand = new Random();
        int sum = 0; // suma rzutu kostką
        for (int i=1 ; i<=numOfThrows ; i++) {
            //przypisanie losowej liczby int z zakresu od 1 do 'diceTypeInt':
            int randNumb = rand.nextInt(diceTypeInt)+1;
            sum = sum + randNumb;
            System.out.println("Rzut nr " + i +", wynik: " + randNumb);
        }
        System.out.println("Finalny wynik to: " + (sum + addNum));

    }
    static boolean validate (String str) {//metoda walidacji podanego stringu
        char[] charTabl = str.toCharArray();
        int counter; //zmienna do zliczania różnych rzeczy,

        //sprawdzenie czy 'str' zawiera tylko cyfry, 'D', '+' lub '-':
        boolean check1;
        counter = 0;
        for (char c : charTabl) {
            if (!(c == 'D' || c == '+' || c == '-' || Character.isDigit(c))) {
                counter++;
            }
        }
        //jeśli 'counter' jest równe '0' to 'check1' jest równe 'true':
        check1 = counter == 0;
        if (!check1) {
            return false;
        }

        //sprawdzenie czy 'D' występuje raz i tylko raz w 'str':
        boolean check2;
        counter = 0;
        for (char c : charTabl) {
            if (c == 'D') {
                counter++;
            }
        }
        //jeśli 'counter' jest równe '1' to 'check2' jest równe 'true':
        check2 = counter == 1;
        if (!check2) {
            return false;
        }

        //sprawdzenie czy '-' '+' występuje max jeden raz w 'str':
        boolean check3;
        counter = 0;
        for (char c : charTabl) {
            if (c == '+' || c == '-') {
                counter++;
            }
        }
        //jeśli 'counter' jest mniejsze/równe '1' to 'check3' jest równe 'true':
        check3 = counter <= 1;
        if (!check3) {
            return false;
        }

        int indD; //indeks litery 'D' w 'str':
        if (str.contains("D")) {
            indD = str.indexOf("D");
        } else indD = str.length();

        //sprawdzenie czy przed 'D' nie występuje '-':
        boolean check4 = !str.substring(0,indD).contains("-");
        if (!check4) {
            return false;
        }

        //sprawdzenie czy przed 'D' nie występuje '+':
        boolean check5 = !str.substring(0,indD).contains("+");
        if (!check5) {
            return false;
        }

        int indSign; //indeks znaków +/- w 'str':
        if (str.contains("+")) {
            indSign = str.indexOf("+");
        } else if (str.contains("-")) {
            indSign = str.indexOf("-");
        } else indSign = str.length();

        //sprawdzenie czy prawidłowo podano rodzaj kostki:
        boolean check6;
        String diceType = str.substring(indD+1,indSign);
        String[] diceTypes = {"3","4","6","8","10","12","20","100"};
        counter=0;
        for (String s : diceTypes) {
            if (diceType.equals(s)) {
                counter++;
            }
        }
        check6 = counter == 1;

        return (check6);
    }
}

