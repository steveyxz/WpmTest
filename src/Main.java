import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final long TIME_SECONDS = 60;
    public static volatile boolean isRunning = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the WPM test!!");
        startTest();
    }

    private static void startTest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type start to start the test!");
        while (true) {
            String s = sc.nextLine();
            if (s.equals("start")) {
                break;
            }
        }
        long count = 0;
        long wordsDone = 0;
        long lastTime = System.nanoTime();
        boolean doCreateNewWord = true;
        String currentWord = "";
        while (count < (TIME_SECONDS * 1000000000L)) {
            if (doCreateNewWord) {
                currentWord = getRandomWord();
                System.out.println(currentWord);
                doCreateNewWord = false;
            }
            String word = sc.nextLine();
            if (word.equals(currentWord)) {
                wordsDone++;
                doCreateNewWord = true;
            }
            count += (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
        }
        System.out.println("WPM is " + wordsDone / (Main.TIME_SECONDS / 60f));
    }

    private static String getRandomWord() {
        BufferedReader r = new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("words/words.txt")));
        Random random = new Random();
        List<String> strings = r.lines().toList();
        try {
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings.get(random.nextInt(strings.size()));
    }
}