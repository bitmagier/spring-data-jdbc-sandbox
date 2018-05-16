package org.purevalue.crud;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("select * from car")
    List<Car> getAll();

    @Query("select * from car where brand like '%:brand%'")
    List<Car> findByBrand(@Param("brand") String brand);
}
