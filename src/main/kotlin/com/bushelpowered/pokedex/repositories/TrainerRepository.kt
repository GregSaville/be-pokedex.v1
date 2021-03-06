package com.bushelpowered.pokedex.repositories

import com.bushelpowered.pokedex.entities.Trainer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TrainerRepository : JpaRepository<Trainer, Long>{
    fun findTrainerByEmail(email: String) : Optional<Trainer>
    fun findByName(name: String): MutableIterable<Optional<Trainer>>
}
