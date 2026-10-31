/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.passwordx;

/**
 *
 * @author dlvlic
 */

import java.util.Scanner;

public class PasswordX {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("       PASSWORD STRENGTH ANALYZER");
        System.out.println("========================================");

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            input.close();
            return;
        }

        int score = 0;

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean symbol = false;
        boolean space = false;

        // Check password characters
        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c))
                upper = true;

            else if (Character.isLowerCase(c))
                lower = true;

            else if (Character.isDigit(c))
                digit = true;

            else if (Character.isWhitespace(c))
                space = true;

            else
                symbol = true;
        }

        // Password length scoring
        if (password.length() >= 8) score += 15;
        if (password.length() >= 12) score += 10;
        if (password.length() >= 16) score += 15;
        if (password.length() >= 20) score += 10;

        // Character variety scoring
        if (upper) score += 10;
        if (lower) score += 10;
        if (digit) score += 10;
        if (symbol) score += 15;

        // Security deductions
        if (hasRepeated(password)) score -= 10;
        if (hasSequence(password)) score -= 10;
        if (isCommon(password)) score -= 40;
        if (space) score -= 5;

        // Keep score between 0 and 100
        score = Math.max(0, Math.min(score, 100));

        // Display results
        System.out.println("\n========================================");
        System.out.println("          PASSWORD ANALYSIS");
        System.out.println("========================================");

        System.out.println("Password Length: " + password.length());
        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Numbers: " + digit);
        System.out.println("Symbols: " + symbol);
        System.out.println("Contains Spaces: " + space);

        System.out.println("----------------------------------------");
        System.out.println("Security Score: " + score + "/100");
        System.out.println("Strength: " + getStrength(score));
        System.out.println("----------------------------------------");

        // Display suggestions
        System.out.println("\nSECURITY SUGGESTIONS:");

        boolean suggestion = false;

        if (password.length() < 12) {
            System.out.println("- Use at least 12 characters.");
            suggestion = true;
        }

        if (!upper) {
            System.out.println("- Add an uppercase letter.");
            suggestion = true;
        }

        if (!lower) {
            System.out.println("- Add a lowercase letter.");
            suggestion = true;
        }

        if (!digit) {
            System.out.println("- Include at least one number.");
            suggestion = true;
        }

        if (!symbol) {
            System.out.println("- Include a special character.");
            suggestion = true;
        }

        if (hasRepeated(password)) {
            System.out.println("- Avoid three consecutive repeated characters.");
            suggestion = true;
        }

        if (hasSequence(password)) {
            System.out.println("- Avoid predictable character sequences.");
            suggestion = true;
        }

        if (isCommon(password)) {
            System.out.println("- Avoid using common passwords.");
            suggestion = true;
        }

        if (space) {
            System.out.println("- Avoid unnecessary spaces.");
            suggestion = true;
        }

        if (!suggestion) {
            System.out.println("- Great! No basic security issues detected.");
        }

        System.out.println("\n========================================");
        System.out.println("         ANALYSIS COMPLETED");
        System.out.println("========================================");

        input.close();
    }

    // Detect three consecutive repeated characters
    public static boolean hasRepeated(String p) {

        for (int i = 0; i < p.length() - 2; i++) {

            if (p.charAt(i) == p.charAt(i + 1)
                    && p.charAt(i + 1) == p.charAt(i + 2)) {
                return true;
            }
        }

        return false;
    }

    // Detect ascending and descending sequences
    public static boolean hasSequence(String p) {

        for (int i = 0; i < p.length() - 2; i++) {

            char a = Character.toLowerCase(p.charAt(i));
            char b = Character.toLowerCase(p.charAt(i + 1));
            char c = Character.toLowerCase(p.charAt(i + 2));

            if (b == a + 1 && c == b + 1)
                return true;

            if (b == a - 1 && c == b - 1)
                return true;
        }

        return false;
    }

    // Check against common passwords
    public static boolean isCommon(String p) {

        String[] common = {
            "password", "123456", "12345678",
            "qwerty", "admin", "letmein",
            "welcome", "password123",
            "admin123", "123456789"
        };

        for (String s : common) {

            if (p.equalsIgnoreCase(s))
                return true;
        }

        return false;
    }

    // Classify password strength
    public static String getStrength(int score) {

        if (score < 30)
            return "VERY WEAK";

        else if (score < 50)
            return "WEAK";

        else if (score < 70)
            return "MODERATE";

        else if (score < 85)
            return "STRONG";

        else
            return "VERY STRONG";
    }
}
