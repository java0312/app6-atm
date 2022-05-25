package uz.pdpd.app6atm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmDto {
    private UUID cardTypeId;
    private String number;
    private Integer removableMaxSum;
    private Integer removableMaxDollar;
    private double commissionInRemovable;
    private double commissionInSend;
    private Integer minSumForWaring;
    private Integer minDollarForWaring;
    private String city;
    private String street;
}
