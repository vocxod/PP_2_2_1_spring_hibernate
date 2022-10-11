package hiber.service;

import hiber.model.User;
import hiber.model.Car;
import java.util.List;

public interface UserService {
  void add(User user);

  List<User> listUsers();

  void add(User user, Car car);

  User getUserByCar(Car car);

}
