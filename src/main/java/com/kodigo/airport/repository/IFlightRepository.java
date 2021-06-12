package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer> {
}
