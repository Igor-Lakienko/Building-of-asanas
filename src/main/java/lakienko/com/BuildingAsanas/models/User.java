package lakienko.com.BuildingAsanas.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username,password,email,comment;
    private boolean enabled;


    @OrderBy
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private Set<UserAsanas> userAsanas = new HashSet<>() ;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {}


    public User(String username, String password, String email, boolean enabled, Set<Role> roles, String comment) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
        this.comment = comment;
    }


    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return getRoles(); }


    public Set<UserAsanas> getUserAsanas() { return userAsanas; }
    public void setUserAsanas(Set<UserAsanas> userAsanas) { this.userAsanas = userAsanas; }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }


    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
