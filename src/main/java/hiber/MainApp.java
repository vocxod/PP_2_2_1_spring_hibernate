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
      "Гранит", "Валун", "Мрамор", "Булыжник"
  };

  public static final String[] MAILBOX = {
      "oneone", "twotwo", "jetjet", "fourfour", "fivefive", "yetyet"
  };

  public static final String[] MAIL_DOMAINS = {
      "mylo.com", "yandex.aru", "mail.oru", "vodka.rom"
  };

  public static final String[] MODELS = { "TAZIK", "OSEL", "VEDRO", "LOSHAD" };

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
    Car car = new Car(generateCarModel(), PRNG.nextInt(99));
    carService.add(car);

    // Create sample user
    User userOne = new User(
        FIRST_NAMES[PRNG.nextInt(FIRST_NAMES.length)],
        LAST_NAMES[PRNG.nextInt(LAST_NAMES.length)],
        generateMail());

    userService.add(userOne, car);

    // System.out.println(userOne);

    List<User> users = userService.listUsers();
    for (User user : users) {
      System.out.println(user);
    }

    context.close();
  }
}
