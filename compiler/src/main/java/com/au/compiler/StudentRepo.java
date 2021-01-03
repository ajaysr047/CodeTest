package com.au.compiler;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, String> {
}
