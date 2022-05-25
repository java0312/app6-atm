package uz.pdpd.app6atm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdpd.app6atm.entity.enums.CurrencyName;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String cvvCode;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date expireDate;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private CardType cardType;

    private boolean cardNonExpired = true;

    private boolean cardNonLocked = true;

    private boolean enabled = true;

    @Column(nullable = false)
    private Double balance;

}
