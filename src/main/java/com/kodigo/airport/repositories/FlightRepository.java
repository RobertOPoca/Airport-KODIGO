package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
