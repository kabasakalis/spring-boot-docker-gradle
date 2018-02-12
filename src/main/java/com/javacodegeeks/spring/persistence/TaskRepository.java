package com.javacodegeeks.spring.persistence;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer>  {

}
