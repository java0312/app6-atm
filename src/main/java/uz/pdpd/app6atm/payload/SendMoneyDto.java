package uz.pdpd.app6atm.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SendMoneyDto {
    private UUID fromCardId;
    private UUID toCardId;
    private UUID atmId;
    private Integer amount;
}
