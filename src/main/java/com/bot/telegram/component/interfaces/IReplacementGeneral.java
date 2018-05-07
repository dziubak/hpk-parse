package com.bot.telegram.component.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.bot.telegram.component.entities.ReplacementGeneral;

/**
 * Repository which has CRUD for ReplacementGeneral entity (replacement_general
 * table in database)
 * 
 * @author yagi
 *
 */
public interface IReplacementGeneral extends CrudRepository<ReplacementGeneral, Integer> {
}
