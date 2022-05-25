package uz.pdpd.app6atm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atm {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private CardType cardType;          //bankomat kartasi turi

    @Column(nullable = false, unique = true)
    private String number;              //bankomatlarni ajratib olish uchun

    @Column(nullable = false)
    private Integer removableMaxSum;    //bankomatdan olinadigan max pul so'mda

    @Column(nullable = false)
    private Integer removableMaxDollar; //bankomatdan olinadigan max pul dollar da

    @Column(nullable = false)
    private double commissionInRemovable; // pul olayotgandagi komissiya miqdori

    @Column(nullable = false)
    private double commissionInSend; //pul boshqa kartaga o'tkazayotgandagi komissiya miqdori

    @Column(nullable = false)
    private Integer minSumForWaring; // bankomatda qancha SO'M qolsa ogohlantirish berilishi

    @Column(nullable = false)
    private Integer minDollarForWaring; // bankomatda qancha DOLLAR qolsa ogohlantirish berilishi

    @OneToOne(cascade = CascadeType.ALL)
    private Address address; //bakomat address i

    private Integer balance;

}
