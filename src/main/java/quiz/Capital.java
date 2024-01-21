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
@Table(name= "capital")
public class Capital {

    @Id
    @Column(name="capital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capital_name", unique = true)
    private String capitalName;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;
}
