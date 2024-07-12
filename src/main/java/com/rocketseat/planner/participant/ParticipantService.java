package com.rocketseat.planner.participant;
import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private PartipantRepository repository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite.stream()
                .map(email -> new Participant(email, trip))
                .collect(Collectors.toList());

        this.repository.saveAll(participants);
        System.out.println(participants.get(0).getId());
    }
    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip){
        Participant newParticipant = new Participant(email,trip);
        this.repository.save(newParticipant);
        return new ParticipantCreateResponse(newParticipant.getId());
    }
    public void triggerConfirmationEmailToParticipants(UUID tripId) {
        // Implementação do método
    }
    public void triggerConfirmationEmailToParticipant(String email) {
        // Implementação do método
    }
    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return (List<ParticipantData>) this.repository.findByTripId(tripId).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed()));
    }

}





