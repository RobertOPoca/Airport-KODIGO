package com.kodigo.airport.repository;

import com.kodigo.airport.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncidentRepository extends JpaRepository<Incident, Integer> {
}
