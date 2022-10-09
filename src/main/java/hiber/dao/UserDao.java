package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import java.util.List;

public interface UserDao {
  void add(User user);

  void add(User user, Car car);

  List<User> listUsers();
  // User getCarUser(Car car);
}
