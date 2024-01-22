package quiz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import quiz.panelsframes.MyFrame;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Capital.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            String[] cityNames = {"France", "Japan", "Italy", "Germany", "Russia", "China", "Egypt", "Australia", "India", "Canada", "Kenya", "Mexico", "Argentina", "South Korea", "Saudi Arabia", "Estonia", "Norway", "Poland", "Portugal", "Finland", "Latvia", "Lithuania"};
            String[] capitalNames = {"Paris", "Tokyo", "Rome", "Berlin", "Moscow", "Beijing", "Cairo", "Sydney", "Delhi", "Ottawa", "Nairobi", "Mexico City", "Buenos Aires", "Seoul", "Riyadh", "Tallinn", "Oslo", "Warsaw", "Lisbon", "Helsinki", "Riga", "Vilnius"};

            if (cityNames.length == capitalNames.length) {
                persistCitiesAndCapitals(session, transaction, cityNames, capitalNames);

            } else {
                System.out.println("Number of cities and capitals don't match.");
            }
        }
    }

    private static void persistCitiesAndCapitals(Session session, Transaction transaction, String[] cityNames, String[] capitalNames) {
        for (int i = 0; i < cityNames.length; i++) {

            // Check if city exists
            Query<City> cityQuery = session.createQuery("FROM City WHERE cityName = :name", City.class);
            cityQuery.setParameter("name", cityNames[i]);
            City existingCity = cityQuery.uniqueResult();

            // Check if capital exists
            Query<Capital> capitalQuery = session.createQuery("FROM Capital WHERE capitalName = :name", Capital.class);
            capitalQuery.setParameter("name", capitalNames[i]);
            Capital existingCapital = capitalQuery.uniqueResult();

            // Persist city and capital
            if (existingCity == null && existingCapital == null) {
                City city = new City();
                city.setCityName(cityNames[i]);

                Capital capital = new Capital();
                capital.setCapitalName(capitalNames[i]);

                city.setCapital(capital);
                capital.setCity(city);

                session.persist(city);
            }
        }
        transaction.commit();

        List<City> cities = session.createQuery("FROM City", City.class).getResultList();
        List<Capital> capitals = session.createQuery("FROM Capital", Capital.class).getResultList();

        if (cities.size() < 5 || capitals.size() < 5) {
            System.out.println("Not enough data to play the game.");
        } else {
            Scanner scanner = new Scanner(System.in);
            char res;

            do{
                System.out.println("Do you want to play in the terminal (t) or in a new window (w)?");

                res = scanner.nextLine().toLowerCase().charAt(0);

                if (res == 'w') {
                    Engine.playWindowGame(session, cities, capitals);
                } else if (res == 't') {
                    Engine.playTerminalGame(session, cities, capitals);
                } else {
                    System.out.println("Invalid input. Please enter 't' or 'w'.");
                }

            } while (res != 't' && res != 'w');
        }
    }
}
