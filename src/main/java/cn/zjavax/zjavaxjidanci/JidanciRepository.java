package cn.zjavax.zjavaxjidanci;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JidanciRepository extends CrudRepository<Danci, Integer> {


    List<Danci> findByDifficulty(int difficulty, Sort sort);
    List<Danci>  findByDifficulty(int difficulty);
//    findByDifficultyByOrderByKnowDesc





}