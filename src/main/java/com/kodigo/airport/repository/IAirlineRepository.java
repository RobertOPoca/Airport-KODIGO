package com.kodigo.airport.repository;

import com.kodigo.airport.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirlineRepository extends JpaRepository<Airline, Integer> {
}
