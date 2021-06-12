package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncidentRepository extends JpaRepository<Incident, Integer> {
}
