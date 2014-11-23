package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataGenerator {

    private static String emailHost = "@mailinator.com";
    private static String[] dumpName = {"Rosina", "Vitalii", "Andrew", "Aleks"};
    private static String[] dumpLastName = {"Poroshenko", "Shevchenko", "Timoshenko", "Demin"};
    private static final Random RANDOM = new Random();
    public static final int PASSWORD_LENGTH = 8;

    public static String randomEmail() {
        String randomEmail = RandomStringUtils.randomAlphabetic(10);
        return randomEmail.toLowerCase() + emailHost;
    }

    public static String randomFirstName(){
        Random rnd = new Random();
        return dumpName[rnd.nextInt(dumpName.length - 1)];
    }

    public static String randomLastName(){
        Random rnd = new Random();
        return dumpLastName[rnd.nextInt(dumpLastName.length - 1)];
    }

    public static String randomEmail(String detail){
        if (detail.equals("withoutHost"))
            return RandomStringUtils.randomAlphabetic(10).toLowerCase();
        return RandomStringUtils.randomAlphabetic(10).toLowerCase() +emailHost;
    }

    public static String randomPassword()
    {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

        String pw = "";
        for (int i=0; i< PASSWORD_LENGTH; i++)
        {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            pw += letters.substring(index, index+1);
        }
        return pw;
    }



}
