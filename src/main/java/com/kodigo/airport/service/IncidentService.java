package com.kodigo.airport.service;

import com.kodigo.airport.model.Incident;
import com.kodigo.airport.repository.IIncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IncidentService implements IIncidentService{

    @Autowired
    private IIncidentRepository incidentRepo;

    @Override
    public Incident create(Incident incident) {
        return incidentRepo.save(incident);
    }

    @Override
    public Incident update(Incident incident) {
        Incident incidentTMP = findById(incident.getIdIncident());
        incidentTMP.setDescription(incident.getDescription());
        incidentTMP.setDateTime(incident.getDateTime());
        incidentTMP.setFlight(incident.getFlight());
        incidentTMP.setIdFlight(incident.getIdFlight());
        return null;
    }

    @Override
    public Incident findById(Integer id) {
        Optional<Incident> incidentOptional = incidentRepo.findById(id);
        return incidentOptional.orElse(null);
    }

    @Override
    public List<Incident> findAll() {
        return incidentRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        incidentRepo.deleteById(id);
    }
}
