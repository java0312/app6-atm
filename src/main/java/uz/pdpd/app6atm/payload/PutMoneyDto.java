package uz.pdpd.app6atm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutMoneyDto {
    private UUID moneyAmountId;
    private UUID atmId;
    private Integer count;
}
