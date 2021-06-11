package com.kodigo.airport.repositories;

import com.kodigo.airport.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
