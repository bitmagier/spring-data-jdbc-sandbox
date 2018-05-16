package org.purevalue.crud;

import org.springframework.data.annotation.Id;

/**
 * create table car(
 *   id bigint,
 *   brand varchar,
 *   model varchar
 * );
 */
public class Car {
    @Id
    Long id;
    String brand;
    String model;

    public Car(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
