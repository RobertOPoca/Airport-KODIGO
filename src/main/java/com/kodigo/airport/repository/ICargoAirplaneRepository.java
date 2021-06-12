package com.kodigo.airport.repository;

import com.kodigo.airport.model.CargoAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICargoAirplaneRepository extends JpaRepository<CargoAirplane, Integer> {
}
