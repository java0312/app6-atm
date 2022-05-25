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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"amount", "currency_id"}))
public class MoneyAmount {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    private Currency currency;

}
