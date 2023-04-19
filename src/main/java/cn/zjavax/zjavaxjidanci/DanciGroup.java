package cn.zjavax.zjavaxjidanci;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class DanciGroup implements Serializable {
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    public  String _class;
    public  String danci;
    public  String chinase;
    public  int know = 1;
    public int difficulty = 1;
}
