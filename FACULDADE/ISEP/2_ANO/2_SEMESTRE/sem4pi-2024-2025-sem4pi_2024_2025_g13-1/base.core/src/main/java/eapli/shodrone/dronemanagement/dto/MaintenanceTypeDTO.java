package eapli.shodrone.dronemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaintenanceTypeDTO {
    private Long id;
    private String name;
    private String description;
    private boolean active;
}