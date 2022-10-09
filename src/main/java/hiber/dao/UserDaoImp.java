package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(User user) {
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  public void add(User user, Car car) {
    user.setCar(car);
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {
    TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
    return query.getResultList();
  }

  /*
   * @Override
   * public User getCarUser(Car car) {
   * TypedQuery<User> query = sessionFactory.getCurrentSession()
   * .createQuery("SELECT u FROM User WHERE u.id NOT NULL", User.class);
   * return query.getSingleResult();
   * }
   */

}
