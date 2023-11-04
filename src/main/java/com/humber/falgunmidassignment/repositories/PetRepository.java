package com.humber.falgunmidassignment.repositories;

import com.humber.falgunmidassignment.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Integer> {
}
