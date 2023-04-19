package cn.zjavax.zjavaxjidanci;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
//@Table(name="danci2")
@Table(name="danci3")
public class Danci implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public  String danci;
    public  String chinese;
    public  int know = 1;
    public int difficulty = 1;

    public Danci() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDanci() {
        return danci;
    }

    public void setDanci(String danci) {
        this.danci = danci;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
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
