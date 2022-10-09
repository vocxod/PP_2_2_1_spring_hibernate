package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserDao userDao;

  @Transactional
  @Override
  public void add(User user) {
    userDao.add(user);
  }

  @Transactional
  @Override
  public void add(User user, Car car) {
    userDao.add(user, car);
  }

  @Transactional(readOnly = true)
  @Override
  public List<User> listUsers() {
    return userDao.listUsers();
  }
  /*
   * @Override
   * public User getCarUser(Car car) {
   * User user = userDao.getCarUser(car);
   * return user;
   * }
   */
}
