package eapli.shodrone.showproposalmanagement.application;

import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneStatus;
import eapli.shodrone.dronemanagement.domain.AvailableDroneModelDTO;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AddDronesToProposalService {

    private final ShowProposalRepository proposalRepository;
    private final DroneRepository droneRepository;
    private final DroneModelRepository droneModelRepository;

    public AddDronesToProposalService(
            ShowProposalRepository proposalRepository,
            DroneRepository droneRepository,
            DroneModelRepository droneModelRepository) {
        this.proposalRepository = proposalRepository;
        this.droneRepository = droneRepository;
        this.droneModelRepository = droneModelRepository;
    }

    public List<AvailableDroneModelDTO> getAvailableDroneModels() {
        List<AvailableDroneModelDTO> result = new ArrayList<>();

        List<DroneModel> models = droneModelRepository.findByActiveTrue();

        for (DroneModel model : models) {
            Iterable<Drone> dronesIterable = droneRepository.findByModelAndStatus(model, DroneStatus.ACTIVE);
            List<Drone> activeDrones = StreamSupport.stream(dronesIterable.spliterator(), false)
                    .toList();

            if (!activeDrones.isEmpty()) {
                result.add(new AvailableDroneModelDTO(model.name(), activeDrones.size()));
            }
        }

        return result;
    }

    public void addDronesToProposal(String proposalIdStr, List<AvailableDroneModelDTO> selectedDrones) {
        ShowProposalID proposalId = ShowProposalID.valueOf(Integer.valueOf(proposalIdStr));

        ShowProposal proposal = proposalRepository.ofIdentity(proposalId)
                .orElseThrow(() -> new IllegalArgumentException("Show Proposal not found: " + proposalIdStr));

        for (AvailableDroneModelDTO dto : selectedDrones) {
            Optional<DroneModel> optModel = droneModelRepository.findByNameIgnoreCase(dto.modelName);
            if (optModel.isEmpty()) {
                throw new IllegalArgumentException("Drone model not found: " + dto.modelName);
            }

            DroneModel model = optModel.get();
            Iterable<Drone> availableDrones = droneRepository.findByModelAndStatus(model, DroneStatus.ACTIVE);
            List<Drone> activeList = StreamSupport.stream(availableDrones.spliterator(), false)
                    .toList();

            if (activeList.size() < dto.availableCount) {
                throw new IllegalArgumentException("Not enough drones available for model: " + dto.modelName);
            }

            proposal.addDronesOfModel(model, dto.availableCount);
        }

        proposalRepository.save(proposal);
    }
}
