package uz.pdpd.app6atm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private String number;
    private String bank;
    private String cvvCode;
    private String firstName;
    private String lastName;
    private String password;
    private UUID cardTypeId;
    private Integer expiredYear;
    private Double balance;
}
