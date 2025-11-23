/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package idealweightcalculator;

import java.util.Scanner;
public class IdealWeightCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get user input for name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

         // Get user input
        System.out.print("Enter your gender (Male/Female): ");
        String gender = scanner.next().toLowerCase();

        System.out.print("Enter your height in cm: ");
        double heightCm = scanner.nextDouble();

        System.out.print("Enter your actual weight in kg: ");
        double actualWeight = scanner.nextDouble();

        // Convert height from cm to inches
        double heightInches = heightCm / 2.54;

        double idealWeight;

        // Apply Devine Formula based on gender
        if (gender.equals("male")) {
            idealWeight = 50 + 2.3 * (heightInches - 60);
        } else if (gender.equals("female")) {
            idealWeight = 45.5 + 2.3 * (heightInches - 60);
        } else {
            System.out.println("Invalid gender input! Please enter Male or Female.");
            return; // Exit if input is invalid
        }

        // Calculate ideal weight range (±10%)
        double minWeight = idealWeight * 0.9;
        double maxWeight = idealWeight * 1.1;

        // Display results
        System.out.printf("Your Ideal Body Weight (IBW) is: %.2f kg\n", idealWeight);
        System.out.printf("Your Ideal Weight Range: %.2f kg - %.2f kg\n", minWeight, maxWeight);
        System.out.printf("Your Actual Weight is %.2f kg\n", actualWeight);

        // Provide health tips based on weight status
        if (actualWeight < minWeight) {
            System.out.println(" You are UNDERWEIGHT. Consider increasing your calorie intake with protein-rich foods.");
        } else if (actualWeight > maxWeight) {
            System.out.println(" You are OVERWEIGHT. A balanced diet and regular exercise can help you maintain a healthy weight.");
        } else {
            System.out.println(" You are within the ideal weight range. Keep maintaining a healthy lifestyle!");
        }

        scanner.close();
    }
    
}
