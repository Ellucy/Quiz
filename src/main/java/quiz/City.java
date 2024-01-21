package quiz;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "city")
public class City {

    @Id
    @Column(name="city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="city_name", unique = true)
    private String cityName;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "capital_id", unique = true, nullable = false)
    private Capital capital;
}
