package com.kodigo.airport.repository;

import com.kodigo.airport.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirplaneRepository extends JpaRepository<Airplane, String> {
}
