package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Bank bank = new Bank();
        try {
            bank.addRekening(new BankRekening("000-0000011-11"));
            bank.addRekening(new BankRekening("000-0000022-22"));
            bank.addRekening(new BankRekening("000-0000033-33"));
            bank.addRekening(new BankRekening("000-0000011-11"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        toonRekeningen(bank);
        System.out.println("We storten 1000 EUR op rekening 000-0000011-11");
        bank.stortGeld("000-0000011-11", 1000);
        toonRekeningen(bank);
        try {
            System.out.println("We proberen 1000 EUR te storten op rekening 000-0000044-44");
            bank.stortGeld("000-0000044-44", 1000);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        toonRekeningen(bank);
        System.out.println("We schrijven 500 EUR over van 000-0000011-11 naar 000-0000022-22");
        bank.schrijfGeldOver("000-0000011-11", "000-0000022-22", 500);
        toonRekeningen(bank);
        System.out.println("We proberen 1000 EUR over te schrijven van 000-0000011-11 naar 000-0000022-22");
        try {
            bank.schrijfGeldOver("000-0000011-11", "000-0000022-22", 1000);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        toonRekeningen(bank);
    }

    private static void toonRekeningen(Bank bank) {
        for (String rekeningnummer : bank.getRekeningnummers()) {
            BankRekening br = bank.getRekening(rekeningnummer);
            System.out.printf("Op rekening %s staat %.2f EURO%n", br.getRekeningnummer(), br.getSaldo());
        }
    }
}
class BankRekening {
    private String rekeningnummer;
    private double saldo;

    public BankRekening(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
        saldo = 0;
    }

    public String getRekeningnummer() {
        return rekeningnummer;
    }

    public double getSaldo() {
        return saldo;
    }

    public void storten(double bedrag) {
        saldo += bedrag;
    }

    public void afhalen(double bedrag) {
        if (bedrag > saldo) { throw new IllegalArgumentException("Saldo mag niet negatief worden");}
        saldo -= bedrag;
    }
}
