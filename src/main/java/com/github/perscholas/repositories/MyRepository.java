package com.github.perscholas.repositories;

import com.github.perscholas.models.MyModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends CrudRepository<MyModel, Long> {
}
