package lia.ru.building.asanas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "asanas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Asana {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asana_generator")
    @SequenceGenerator(name = "asana_generator", sequenceName = "seq_asana")
    @NonNull
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String info;

    @NonNull
    private String image;

    @NonNull
    private String positiveEffects;

    @NonNull
    private String negativeEffects;

//    @JoinColumn(name = "user_id")
//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    private User user;

//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Asana asana = (Asana) o;
//        return id == asana.id;
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
