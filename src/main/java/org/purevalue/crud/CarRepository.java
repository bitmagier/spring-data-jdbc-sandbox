package org.purevalue.crud;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("select * from car")
    List<Car> getAll();

    @Query("select * from car where brand = :brand")
    List<Car> findByBrand(@Param("brand") String brand);

    @Query("select brand from car where brand like :brand group by brand")
    List<String> findBrandsLike(@Param("brand") String brand);
}
