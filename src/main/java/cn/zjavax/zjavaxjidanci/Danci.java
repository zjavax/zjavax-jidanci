package cn.zjavax.zjavaxjidanci;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Entity
//@Table(name="danci")
//@Table(name="danci3")  // 文章 your-cardano-onboarding-guide
@Table(name="ada_article2") // what-is-cardano-cardano-101
//@Table(name="danci2")
//@Table(name="anhui_zhongkao")  // 安徽中考
//@Table(name="suffix_word")
//@Table(name="top250adv")
//@Table(name="top500adj")
//@Table(name="top2000words")
//@Table(name="nce_1")
//@Table(name="all1")
//@Table(name="danci3000")
//@Table(name="nce_2")
public class Danci implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String name;
    public  String trans;
    public  int know = 0;
    public int difficulty = 0;
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



    public static void main(String[] args) {
        // 设置属性
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 创建一个Annotation对象
        String text = "The cats [are] chasing's the mice is be train training trading trade. The mice are-scared of the cats.";
        Annotation document = new Annotation(text);

        // 运行pipeline
        pipeline.annotate(document);

        List<CoreMap> words = document.get(CoreAnnotations.SentencesAnnotation.class);

        // 获取每个单词的原型，并统计出现次数
        Map<String, Integer> wordCountMap = new HashMap<>();

        for(CoreMap wordTemp: words) {
            for (CoreLabel token: wordTemp.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                if(word.matches("[a-zA-Z]+")){ // 只输出英文单词，去掉标点符号
                    String originalForm = token.get(CoreAnnotations.LemmaAnnotation.class);
                    System.out.println(word + " --> " + originalForm);

                    int count = wordCountMap.getOrDefault(originalForm, 0);
                    wordCountMap.put(originalForm, count + 1);
                }
            }
        }

        for(String s:wordCountMap.keySet()){
            System.out.println(s+":"+wordCountMap.get(s));
        }
    }
}
