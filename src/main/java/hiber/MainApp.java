package hiber;

import hiber.config.AppConfig;

import hiber.model.User;
import hiber.model.Car;

import hiber.service.UserService;
import hiber.service.CarService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mysql.cj.xdevapi.SessionFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class MainApp {

  public static final String[] FIRST_NAMES = {
      "Одуванчик", "Ромашка", "Роза", "Зверобой", "Лилия"
  };

  public static final String[] LAST_NAMES = {
      "Штирлиц", "Чапаев", "Вовочка", "МаленькийМальчик"
  };

  public static final String[] MAILBOX = {
      "oneone", "twotwo", "threeThee", "fourfour", "fivefive", "sixsix"
  };

  public static final String[] MAIL_DOMAINS = {
      "gmail.com", "yandex.ru", "yahoo.com", "mail.ru"
  };

  public static final String[] MODELS = { "VOLVO", "AUDI", "VERDO", "GMC" };

  public static String generateCarModel() {
    return MODELS[PRNG.nextInt(MODELS.length)];
  }

  private static final Random PRNG = new Random();

  public static String generateMail() {
    String result = MAILBOX[PRNG.nextInt(MAILBOX.length)] + MAIL_DOMAINS[PRNG.nextInt(MAIL_DOMAINS.length)];
    return result;
  }

  public static void main(String[] args) throws SQLException {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    UserService userService = context.getBean(UserService.class);
    CarService carService = context.getBean(CarService.class);

    // Create sample car
    Car car = new Car(generateCarModel(), PRNG.nextInt());
    carService.add(car);

    // Create sample user
    User userOne = new User(FIRST_NAMES[PRNG.nextInt(FIRST_NAMES.length)], LAST_NAMES[PRNG.nextInt(LAST_NAMES.length)],
        generateMail());
    car.setUser(userOne);

    userService.add(userOne);

    // car.save();

    // System.out.println(userOne);

    List<User> users = userService.listUsers();
    for (User user : users) {
      System.out.println(user);
    }

    /*
     * System.out.println(userOne);
     * 
     * // выдадим автомобиль юзверю
     * Car carOne = new Car(userOne.getId(), "старый запорожеТц", (int) (100500 +
     * userOne.getId()));
     * carService.add(carOne);
     * 
     * System.out.println(userOne);
     */

    /*
     * List<User> users = userService.listUsers();
     * for (User user : users) {
     * 
     * System.out.println("Id = " + user.getId());
     * System.out.println("First Name = " + user.getFirstName());
     * System.out.println("Last Name = " + user.getLastName());
     * System.out.println("Email = " + user.getEmail());
     * System.out.println();
     * }
     */

    /*
     * carService.add(new Car("Moskvitch", 407));
     * carService.add(new Car("Moskvitch", 408));
     * carService.add(new Car("Moskvitch", 412));
     * carService.add(new Car("ВАЗ", 21011));
     * carService.add(new Car("ВАЗ", 21012));
     * carService.add(new Car("ВАЗ", 21093));
     * carService.add(new Car("ВАЗ", 21099));
     * 
     * List<Car> cars = carService.listCars();
     * for (Car car : cars) {
     * System.out.println("MODEL:" + car.getModel() + " SERIES:" + car.getSeries());
     * }
     */

    context.close();
  }
}
