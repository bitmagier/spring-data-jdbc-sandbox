package org.purevalue.crud;

import org.springframework.data.annotation.Id;

/**
 * create table car(
 *   id bigserial,
 *   brand varchar,
 *   model varchar
 * );
 */
public class Car {
    @Id
    Long id;
    String brand;
    String model;

    public Car(String brand, String model) {
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
