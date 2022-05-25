package uz.pdpd.app6atm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeMoneyDto {
    private UUID atmId;
    private Integer money;
    private String cardNumber;
    private String password;
}
