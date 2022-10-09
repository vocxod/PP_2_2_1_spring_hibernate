package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "model")
  private String model;

  @Column(name = "series")
  private Integer series;

  @OneToOne(mappedBy = "car")
  private User user;

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Car() {
  }

  public Car(Long id, String model, Integer series) {
    this.setId(id);
    this.model = model;
    this.series = series;
  }

  public Car(String model, Integer series) {
    this.model = model;
    this.series = series;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getSeries() {
    return series;
  }

  public void setSeries(Integer series) {
    this.series = series;
  }

  public String toString() {
    return "Owner:" + " model:" + this.model + " series:" + this.series;
  }

}
