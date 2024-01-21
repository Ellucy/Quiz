package quiz;

import org.hibernate.Session;

import java.util.*;

public class Engine {

    public static void playGame(Session session, List<City> cities, List<Capital> capitals) {

        final int NUM_QUESTIONS = 5;
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        Random random = new Random();

        for (int i = 0; i < NUM_QUESTIONS; i++) {

            City selectedCity = cities.get(random.nextInt(cities.size()));

            Capital correctCapital = selectedCity.getCapital();

            List<Capital> distractors = getDistractors(capitals, correctCapital);

            List<Capital> options = new ArrayList<>(List.of(correctCapital, distractors.get(0), distractors.get(1), distractors.get(2)));

            // Shuffle four options (correct + 3 distractors)
            Collections.shuffle(options);

            System.out.println("Question " + (i + 1) + ": What is the capital of " + selectedCity.getCityName() + "? (1/2/3/4)");
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j).getCapitalName());
            }

            // User input
            int userChoice = scanner.nextInt();

            // Check if the user's choice is correct
            if (options.get(userChoice - 1).equals(correctCapital)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + correctCapital.getCapitalName() + "\n");
            }
        }

        System.out.println("Your score: " + score + "/" + NUM_QUESTIONS);
    }

    private static List<Capital> getDistractors(List<Capital> allCapitals, Capital correctCapital) {

        // Shuffle all capitals
        Collections.shuffle(allCapitals);

        // Remove correct capital
        allCapitals.remove(correctCapital);

        // Take three capitals as distractors
        return allCapitals.subList(0, 3);
    }
}
