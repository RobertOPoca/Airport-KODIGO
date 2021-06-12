package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirplaneRepository extends JpaRepository<Airplane, String> {
}
