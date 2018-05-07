package com.bot.telegram.component.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.bot.telegram.component.entities.Replacement;

/**
 * Repository which has CRUD for Replacement entity (replacement table in database)
 * 
 * @author yagi
 *
 */
public interface IReplacement extends CrudRepository<Replacement, Integer> {
}
