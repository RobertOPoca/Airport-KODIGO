package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirlineRepository extends JpaRepository<Airline, Integer> {
}
