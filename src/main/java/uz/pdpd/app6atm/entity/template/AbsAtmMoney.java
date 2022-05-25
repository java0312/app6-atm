package uz.pdpd.app6atm.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.entity.MoneyAmount;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Data
public class AbsAtmMoney {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private MoneyAmount moneyAmount;

    @ManyToOne(optional = false)
    private Atm atm;

    @Column(nullable = false)
    private Integer count;

    @CreationTimestamp
    private Timestamp createdAt;

}
