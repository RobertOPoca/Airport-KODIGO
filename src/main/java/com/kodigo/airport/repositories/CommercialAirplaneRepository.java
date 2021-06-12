package com.kodigo.airport.repositories;

import com.kodigo.airport.models.CommercialAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialAirplaneRepository extends JpaRepository<CommercialAirplane, Integer> {
}
