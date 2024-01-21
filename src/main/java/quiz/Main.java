package quiz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Capital.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            String[] cityNames = {"France", "Japan", "Italy", "Germany", "Russia", "China", "Egypt", "Australia", "India", "Canada", "Kenya", "Mexico", "Argentina", "South Korea", "Saudi Arabia", "Estonia", "Norway", "Poland", "Portugal", "Finland"};
            String[] capitalNames = {"Paris", "Tokyo", "Rome", "Berlin", "Moscow", "Beijing", "Cairo", "Sydney", "Delhi", "Ottawa", "Nairobi", "Mexico City", "Buenos Aires", "Seoul", "Riyadh", "Tallinn", "Oslo", "Warsaw", "Lisbon", "Helsinki"};

            if (cityNames.length == capitalNames.length) {
                for (int i = 0; i < cityNames.length; i++) {
                    City city = new City();
                    city.setCityName(cityNames[i]);

                    Capital capital = new Capital();
                    capital.setCapitalName(capitalNames[i]);

                    city.setCapital(capital);
                    capital.setCity(city);

                    session.persist(city);
                }
                transaction.commit();
            } else {
                System.out.println("Number of cities and capitals don't match.");
            }
        }

    }

}
