package edu.uptc.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import edu.uptc.presenter.HandlingCalls;

public class Runner {

	

    HandlingCalls handlingCalls = new HandlingCalls();
    Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        
        new Runner().mainMenu();
        
    }

    public void mainMenu(){

        String menu = "----- Main menu -----\n" 
            + "1. Add a phone\n"
            + "2. Find phone\n"
            + "3. Add minutes\n"
            + "4. Register call\n"
            + "5. Exit";

        String option = "";
        int optionNum = 0;

        while (optionNum != 5) {

            try {

                System.out.println(menu);
                option = sc.nextLine();
                optionNum = Integer.parseInt(option);

                while (optionNum != 1 & optionNum != 2 & optionNum != 3 & optionNum != 4 & optionNum != 5){
                    System.out.println("Enter a correct option");
                    option = sc.nextLine();
                    optionNum = Integer.parseInt(option);
                }

                switch (optionNum) {
                    case 1: addPhone();                  
                        break;
                
                    case 2: findPhone();                  
                        break;
                    
                    case 3: addMinutes();                  
                        break;

                    case 4: registryCall();                  
                        break;
                }
            
            } catch (NumberFormatException e) {
                System.out.println("*** Enter a numeric value ***");
            }
            
        }

    }

    public void addPhone(){
        String imei = "";
        String number = "";
        LocalDate manufacturingDate = LocalDate.of(2020, 01, 01);
        String dateString = "";
        String description = "";
        int minutes = 0;
        boolean validation = true;
        boolean operation = true; 
        
        //IMEI validation
        System.out.println("--- Enter a phone IMEI ---");
        imei = sc.nextLine();

        while (imei.length() != 15) {

            System.out.println("*** The IMEI moust have 15 characters, enter a correct phone IMEI ***");
            imei = sc.nextLine();

        }

        //Phone number validation
        System.out.println("--- Enter a phone number ---");
        number = sc.nextLine();

        while (number.length() != 10) {

            System.out.println("*** The number moust have 10 characters, enter a correct number ***");
            number = sc.nextLine();

        }

        do {
            try {

            long numberNum = Long.parseLong(number);
            validation = false;
            
            } catch (NumberFormatException e) {
                System.out.println("*** Enter a correct number ***");
                number = sc.nextLine();
            }
        } while (validation);
        

        //Manufacturiong date validation
        validation = true;
        System.out.println("--- Enter a phone manufacturing date ---" 
            + "\nPlease observe the following format: yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateString = sc.nextLine();
        
        while (validation) {
            try {

                manufacturingDate = LocalDate.parse(dateString, formatter);
                validation = false;
                
            } catch (DateTimeException e) {
                System.out.println("*** Enter the date with respect to the format ***");
                dateString = sc.nextLine();
                validation = true;
            }
        }

        //Description
        System.out.println("--- Enter a cell plan description ---");
        description = sc.nextLine();

        //Minutes validation
        System.out.println("--- Enter a cell plan minutes ---");
        String minutesStr = sc.nextLine();
        validation = true;

        do {
            try {
                minutes = Integer.parseInt(minutesStr);
                validation = false;
                
            
            } catch (Exception e) {
                System.out.println("*** Enter a correct number ***");
                minutesStr = sc.nextLine();
            }
        } while (validation);
        
        operation = handlingCalls.addPhone(imei, number, manufacturingDate, description, minutes);       
    }

    public void findPhone() {
        String imei = "";
        System.out.println("--- Enter the IMEI you want to search ---");
        imei = sc.nextLine();

        while (imei.length() != 15) {
            System.out.println("*** The IMEI must have 15 characters, enter a correct phone IMEI ***");
            imei = sc.nextLine();
        }

        try {
            String result = handlingCalls.findPhone(imei);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("Phone not found");
            }
        } catch (NullPointerException e) {
            System.out.println("Phone not found");
        }
    }



    public void addMinutes() {
        String imei = "";
        System.out.println("--- Enter the phone IMEI ---");
        imei = sc.nextLine();
        int minutes = 0;
        boolean validation = true;

        while (imei.length() != 15) {
            System.out.println("*** The IMEI must have 15 characters, enter a correct phone IMEI ***");
            imei = sc.nextLine();
        }

        System.out.println("--- Enter the number of minutes ---");
        String minutesStr = sc.nextLine();
        validation = true;

        do {
            try {
                minutes = Integer.parseInt(minutesStr);
                validation = false;                
            } catch (NumberFormatException e) {
                System.out.println("*** Enter a correct number ***");
                minutesStr = sc.nextLine();
            }
        } while (validation);

        try {
            int newMinutes = handlingCalls.addMinutes(imei, minutes);
            System.out.println("New minutes: " + newMinutes);
        } catch (NullPointerException e) {
            System.out.println("Phone not found");
        }
    }


    public void registryCall() {
        String imei = "";
        String number = "";
        int numberMin = 0;
        boolean validation = true;
        String minutes = "";

        System.out.println("--- Enter the IMEI ---");
        imei = sc.nextLine();

        while (imei.length() != 15) {
            System.out.println("*** The IMEI must have 15 characters, enter a correct phone IMEI ***");
            imei = sc.nextLine();
        }

        System.out.println("--- Enter the telephone number you wish to call ---");
        number = sc.nextLine();

        do {
            try {
                long numberNum1 = Long.parseLong(number);
                validation = false;
            } catch (NumberFormatException e) {
                System.out.println("*** Enter a correct number ***");
                number = sc.nextLine();
            }
        } while (validation);

        System.out.println("--- Enter the minutes consumed ---");
        minutes = sc.nextLine();

        validation = true;
        do {
            try {
                numberMin = Integer.parseInt(minutes);
                validation = false;
            } catch (NumberFormatException e) {
                System.out.println("*** Enter a correct minutes ***");
                minutes = sc.nextLine();
            }
        } while (validation);

        try {
            if (handlingCalls.registryCall(imei, number, numberMin)) {
                System.out.println("Registered call");
            } else {
                System.out.println("Insufficient minutes");
            }

            System.out.println(handlingCalls.findPhone(imei));
        } catch (NullPointerException e) {
            System.out.println("Phone not found");
        }
    }



}
