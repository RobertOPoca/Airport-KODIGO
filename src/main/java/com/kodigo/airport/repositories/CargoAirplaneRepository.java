package com.kodigo.airport.repositories;

import com.kodigo.airport.models.CargoAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoAirplaneRepository extends JpaRepository<CargoAirplane, Integer> {
}
