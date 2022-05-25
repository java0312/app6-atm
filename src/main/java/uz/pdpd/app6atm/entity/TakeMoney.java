package uz.pdpd.app6atm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdpd.app6atm.entity.template.AbsAtmMoney;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TakeMoney extends AbsAtmMoney {

    @ManyToOne
    private Card fromCard;

}
