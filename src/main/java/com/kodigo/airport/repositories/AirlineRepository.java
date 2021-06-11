package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
