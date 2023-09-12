package cn.zjavax.zjavaxjidanci;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JidanciRepository extends JpaRepository<Danci, Integer> {


    List<Danci> findByDifficulty(int difficulty, Sort sort);
    List<Danci> findByDifficulty(int difficulty);
//    List<Danci>  findByDifficulty(int difficulty);
    List<Danci> findByNameLike(String name);
//    findByDifficultyByOrderByKnowDesc






}