package net.runelite.api.util;

import net.runelite.api.Constants;

import static java.lang.Thread.sleep;

public class Numbers {
    public static int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static long getRandomNumber(long min, long max)
    {
        return (long) ((Math.random() * (max - min)) + min);
    }
    public static int getRandomNumberBetween(int min, int max)
    {
        min = min + getRandomNumber(-25, 25);
        max = max + getRandomNumber(-25, 25);
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void sleepRandom(int min, int max)
    {
        min = min + getRandomNumber(-25, 25);
        max = max + getRandomNumber(-25, 25);
        try {
            sleep(getRandomNumberBetween(min, max));
        } catch (InterruptedException ignored) { }
    }
    public static void sleepRand()
    {
        sleepRandom(100, 250);
    }
    public static void sleepRandTick()
    {
       sleepRandTick(1);
    }
    public static void sleepRandTick(int ticks)
    {
        sleepRandom(Constants.GAME_TICK_LENGTH, (Constants.GAME_TICK_LENGTH * ticks) + 450);
    }
}
