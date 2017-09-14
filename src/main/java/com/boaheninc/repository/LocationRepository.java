package com.boaheninc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boaheninc.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

}
