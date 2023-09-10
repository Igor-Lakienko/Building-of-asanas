package lia.ru.building.asanas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "asanas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Asana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
