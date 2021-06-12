package com.kodigo.airport.repository;

import com.kodigo.airport.model.CommercialAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommercialAirplaneRepository extends JpaRepository<CommercialAirplane, Integer> {
}
