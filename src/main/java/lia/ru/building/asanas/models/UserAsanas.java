package lia.ru.building.asanas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_asanas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAsanas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_asanas_generator")
    @SequenceGenerator(name = "user_asanas_generator", sequenceName = "seq_user_asanas")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "asana_id", referencedColumnName = "id")
    private Asana asana;
}
