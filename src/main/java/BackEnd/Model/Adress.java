package BackEnd.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String city;

    private String street;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users user;

    public Adress(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;

    }
}
