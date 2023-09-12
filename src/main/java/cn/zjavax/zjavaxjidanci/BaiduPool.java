package cn.zjavax.zjavaxjidanci;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="baidu_pool")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiduPool {

    @Id
    public String stakeAddress12;

    public String stakeAddress;

    public int totalAda;

    public int epoch;

    public  String vxName;

    public int hosky;

}
