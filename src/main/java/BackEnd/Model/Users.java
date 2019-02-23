package BackEnd.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "firstname")
    private String firstname;
    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private Adress adress;

    public Users(@NotNull String firstname, @NotNull String lastname, Adress adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country=" + adress.getCountry() +
                ", city=" + adress.getCity() +
                ", street=" +adress.getStreet()+
                '}';
    }
}


