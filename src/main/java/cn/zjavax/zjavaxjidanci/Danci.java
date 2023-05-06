package cn.zjavax.zjavaxjidanci;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name="danci")
//@Table(name="danci3")  // 文章
//@Table(name="danci2")
//@Table(name="anhui_zhongkao")  // 安徽中考
@Table(name="danci3000")
//@Table(name="suffix_word")
//@Table(name="top250adv")
//@Table(name="top500adj")
//@Table(name="top2000words")
//@Table(name="nce_1")
public class Danci implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String name;
    public  String trans;
    public  int know = 1;
    public int difficulty = 10;  // 初始化
    public  String notes;

    // 表名
//    public String tableName;

    public Danci() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKnow() {
        return know;
    }

    public void setKnow(int know) {
        this.know = know;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Danci danci1 = (Danci) o;
//        return Objects.equals(danci, danci1.danci);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
}
