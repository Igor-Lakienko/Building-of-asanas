package lakienko.com.BuildingAsanas.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "asana")
public class Asana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title,info,image,fullInfo;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


    public Asana() {}
    public Asana(String title, String info, String image, String fullInfo, User user) {
        this.title = title;
        this.info = info;
        this.image = image;
        this.fullInfo = fullInfo;
        this.user = user;

    }


    public long getId() { return id;}
    public void setId(long id) {this.id = id;}


    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }


    public String getTitle() { return title; }
    public void setTitle(String title) {this.title = title;}


    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }


    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }


    public String getFullInfo() { return fullInfo; }
    public void setFullInfo(String fullInfo) { this.fullInfo = fullInfo; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asana asana = (Asana) o;
        return id == asana.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
