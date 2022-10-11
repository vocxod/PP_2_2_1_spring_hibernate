package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

  public static final String[] MODELS = { "TAZIK", "OSEL", "VEDRO", "HORSE", "COW" };

  public static String generateCarModel() {
    return MODELS[PRNG.nextInt(MODELS.length)];
  }

  private static final Random PRNG = new Random();

  public static String generateMail() {
    String result = MAILBOX[PRNG.nextInt(MAILBOX.length)] + "@" + MAIL_DOMAINS[PRNG.nextInt(MAIL_DOMAINS.length)];
    return result;
  }

  public static void main(String[] args) throws SQLException {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    CarService carService = context.getBean(CarService.class);
    UserService userService = context.getBean(UserService.class);

    Car car = new Car(MODELS[PRNG.nextInt(MODELS.length)], PRNG.nextInt(10));
    carService.add(car);

    User user = new User(
        FIRST_NAMES[PRNG.nextInt(FIRST_NAMES.length)],
        LAST_NAMES[PRNG.nextInt(LAST_NAMES.length)],
        generateMail());

    System.out.println("\u001B[31m Add new user. \u001B[0m");
    System.out.print("\u001B[36m");
    System.out.print("NEW USER: " + user);
    System.out.print("\u001B[0m");
    System.out.print("\u001B[0;34m \nNEW CAR: MODEL=" + car.getModel()
        + " \tSERIES=" + car.getSeries() + " ");
    System.out.println();

    userService.add(user, car);

    System.out.println("\u001B[35m Show all users. \u001B[0m");
    List<User> users = userService.listUsers();
    for (User uItem : users) {
      System.out.print("\u001B[1;33m");
      System.out.println(uItem);
      System.out.print("\u001B[0m");
    }

    System.out.println("\u001B[4;36m Find user by each car.\u001B[0m");
    List<Car> cars = carService.listCars();
    for (Car carItem : cars) {
      User selectedUser = userService.getUserByCar(carItem);
      System.out.print("\u001B[0;33m CAR: MODEL=" + carItem.getModel()
          + " \tSERIES=" + carItem.getSeries() + " ");
      System.out.print("\u001B[32m");
      System.out.println(selectedUser);
      System.out.print("\u001B[0m");
    }

    context.close();
  }
}
