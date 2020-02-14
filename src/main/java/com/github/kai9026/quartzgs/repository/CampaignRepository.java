package com.github.kai9026.quartzgs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Integer> {
    Optional<Campaign> findByName(String name);
}
