package eapli.shodrone.showmanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowExtendedDTO {
    private  String id;
    private  String date;
    private  String time;
    private  String address;
    private  String status;
    private  String duration;
    private  String description;
    private  String figures;
    private  String drones;

}
