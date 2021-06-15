package com.kodigo.airport.repository;

import com.kodigo.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE f.departureTime BETWEEN ?1 AND ?2")
    List<Flight> getDateBetweenDate(String start, String end);
}
