package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Integer> {
}
