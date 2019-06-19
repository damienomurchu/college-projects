package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Donation extends Model
{
  @ManyToOne
  public User donor;
  
  public String method;
  public Long amount;  
}
