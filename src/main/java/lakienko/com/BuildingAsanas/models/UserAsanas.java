package lakienko.com.BuildingAsanas.models;


import javax.persistence.*;

@Entity
public class UserAsanas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "asana_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Asana asana;


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Asana getAsana() { return asana;}
    public void setAsana(Asana asana) { this.asana = asana; }

}
