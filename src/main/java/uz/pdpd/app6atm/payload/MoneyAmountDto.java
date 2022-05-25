package uz.pdpd.app6atm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyAmountDto {
    private Integer amount;
    private UUID currencyId;
}
