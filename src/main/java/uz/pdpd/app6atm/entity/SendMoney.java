package uz.pdpd.app6atm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SendMoney {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Card fromCard;

    @ManyToOne
    private Card toCard;

    @ManyToOne
    private Atm atm;

    private Integer amount;

    @CreationTimestamp
    private Timestamp createdAt;

}
