package cn.zjavax.zjavaxjidanci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class JidanciApi {

    @Autowired
    private JidanciRepository jidanciRepository;

    public static List<Danci> list = new ArrayList<>();
//    public static Set<String> set = new HashSet<>();
    public static Map<String,Danci> map = new HashMap<>();


//    @Autowired
//    private EasydanciRepository  easydanciRepository;

    @GetMapping("/getDanci")
    List<Danci> getDanci(@RequestParam(value = "difficulty", required = false) int difficulty,
                         @RequestParam(value = "sort", required = false) String sort) {
        if ("desc".equals(sort)){
            return jidanciRepository.findByDifficulty(difficulty, Sort.by("know").descending());
        }else if("asc".equals(sort)){
            return jidanciRepository.findByDifficulty(difficulty, Sort.by("know").ascending());
        }else if("no".equals(sort)){
            return jidanciRepository.findByDifficulty(difficulty, Sort.by("danci").ascending());
        }
        return new ArrayList<>();
    }

    @DeleteMapping("/danci/deleteById/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        jidanciRepository.deleteById(id);
    }

    @PutMapping("/danci")
    public void putById(@RequestBody Danci danci) {
        jidanciRepository.save(danci);
    }


//    空格 分割
    @PostMapping ("/alldanci")
    public void addDanci(@RequestBody Input input) {



        String alldanci = input.getAlldanci();

        String[] rowArr =  alldanci.trim().split("\\n+");

        List<Danci> danciList = new ArrayList<>();
        for (String row:rowArr){

            String[] alldanciArray = row.trim().split("\\s+");

            for (String danci:alldanciArray){
                Danci danciRow = new Danci();
                danciRow.setDanci(danci.trim());
                danciRow.setDifficulty(1);



                danciList.add(danciRow);
//                if (!list.contains(danciRow)){
//                    danciList.add(danciRow);
//                }
//                if(list.contains(danci) && !danciRow.chinese.isBlank()){
//                    danciList.add(danciRow);
//                }
            }

        }

        jidanciRepository.saveAll(danciList);

    }


    // 给单词添加含义，使用gpt解释
    @PostMapping ("/alldancigroup")
    public void putDanciGroupById(@RequestBody Input input) {
        list = (List<Danci>) jidanciRepository.findAll();
        for (Danci danci : list) {
            map.put(danci.getDanci(),danci);
        }

        String alldanci = input.getAlldancigroup();

        String[] rowArr =  alldanci.trim().split("\\n+");

        List<Danci> danciList = new ArrayList<>();
        for (String row:rowArr){
            String[] alldanciArray = row.trim().split(":");
            Danci danciRow = map.get(alldanciArray[0].trim());
            if (danciRow == null) {
                continue;
            } else {
                danciRow.setChinese(alldanciArray[1].trim());
                danciList.add(danciRow);
            }


        }

        jidanciRepository.saveAll(danciList);
        map.clear();

    }


    // 按行 分割
    @PostMapping ("/alldancigroup2")
    public void putDanciGroupById2(@RequestBody Input input) {

        String alldanci = input.getAlldancigroup();
//        String[] alldanciArray = alldanci.split("\\n");  // 分行


        String[] rowArr =  alldanci.trim().split("\\n+");

        List<Danci> danciList = new ArrayList<>();
        for (String row:rowArr){
            String[] alldanciArray = row.trim().split("\\s+");

            for (String danci:alldanciArray){
                Danci danciRow = new Danci();
                danciRow.setDanci(danci.trim());
                danciRow.setDifficulty(1);



                danciList.add(danciRow);
//                if (!list.contains(danciRow)){
//                    danciList.add(danciRow);
//                }
//                if(list.contains(danci) && !danciRow.chinese.isBlank()){
//                    danciList.add(danciRow);
//                }
            }

        }

        jidanciRepository.saveAll(danciList);


    }



    // 按行 分割
    @PostMapping ("/addArticle")
    public void addArticle(@RequestBody Input input) {
        list = (List<Danci>) jidanciRepository.findAll();
        for (Danci danci : list) {
            map.put(danci.getDanci(),danci);
        }

        String article = input.getArticle();
        article = article.replaceAll("[\\pP‘’“”[0-9]]", " ");


        String[] danciArr =  article.trim().split("\\n+|\\s+");

        List<Danci> danciList = new ArrayList<>();
        for (String danci:danciArr){
            danci = danci.trim().toLowerCase(Locale.ROOT);
            Danci danci1 = map.get(danci);
            Danci danciRow = new Danci();

            if(danci1 == null){
                danciRow.setDanci(danci);
                danciRow.setDifficulty(0);
                map.put(danci,danciRow);
                danciList.add(danciRow);
            } else {
                if(danci1.getId() != 0){
                    danci1.setKnow(danci1.getKnow()+1);
                    map.put(danci,danci1);
                    danciList.add(danci1);
                } else {
                    danci1.setKnow(danci1.getKnow()+1);
                }
            }

        }

        jidanciRepository.saveAll(danciList);
        map.clear();


    }

}

class Input{
    String alldanci;
    String alldancigroup;
    String article;

    public String getAlldanci() {
        return alldanci;
    }

    public void setAlldanci(String alldanci) {
        this.alldanci = alldanci;
    }

    public String getAlldancigroup() {
        return alldancigroup;
    }

    public void setAlldancigroup(String alldancigroup) {
        this.alldancigroup = alldancigroup;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}