package cn.zjavax.zjavaxjidanci;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController()
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
            List<Danci> danciList = jidanciRepository.findByDifficulty(difficulty, Sort.by("name").ascending());
            if(difficulty == 10){
                printDanci(danciList);
            }

            return danciList;
        }
        return new ArrayList<>();
    }


//    单词查询{英语单词查询解释}
//    请对Text以表格返回：
//    项目|结果
//    翻译成英文
//    国际音标
//    词性
//    词形
//    中文解释
//    词根词缀
//    例句1(英-中)
//    例句2
//    例句3
//    Text: """effect"""
    public static void printDanci(List<Danci> danciList) {
        System.out.println("==========================================");
        System.out.println("单词查询{英语单词查询解释}");
        System.out.println("请对Text以表格返回：");
        System.out.println("项目|结果");
        System.out.println("翻译成英文");
        System.out.println("词形");
        System.out.println("中文解释");
        System.out.println("词根词缀");
        System.out.println("例句1(英-中)");
        System.out.println("例句2(英-中)");
        System.out.println("例句3(英-中)");

        danciList.forEach(d ->{
            if (!StringUtils.isEmpty(d.name)){
                System.out.println("Text: \"\"\""+d.name+"\"\"\"");
            }
        });
    }


    @GetMapping("/searchWords")
    List<Danci> searchWords(String searchWords) {
        return jidanciRepository.findByNameLike("%"+searchWords+"%");
    }

    @DeleteMapping("/danci/deleteById/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        jidanciRepository.deleteById(id);
    }

    @PutMapping("/danci")
    public void putById(@RequestBody Danci danci) {
        jidanciRepository.save(danci);
    }

    @PostMapping("/addAll")
//    @ResponseBody
    public List<Danci> addAll(@RequestBody List<Danci> dicts){
        return (List<Danci>) jidanciRepository.saveAll(dicts);
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
                danciRow.setName(danci.trim());
                danciRow.setDifficulty(0);



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

    // 格式：单词 或者 单词:中文，支持多行
    // 给单词添加含义，使用gpt解释
    @PostMapping ("/alldancigroup")
    public void putDanciGroupById(@RequestBody Input input) {
        list = (List<Danci>) jidanciRepository.findAll();
        for (Danci danci : list) {
            map.put(danci.getName(),danci);
        }

        String alldanci = input.getAlldancigroup();

        String[] rowArr =  alldanci.trim().split("\\n+");

        List<Danci> danciList = new ArrayList<>();
        for (String row:rowArr){
            String[] alldanciArray = row.trim().split("::|：：");
            Danci danciRow = map.get(alldanciArray[0].trim().toLowerCase(Locale.ROOT));
            if (danciRow == null) {
                Danci danci = new Danci();
                danci.setDifficulty(input.difficulty);
                danci.setName(alldanciArray[0].trim());
                if(alldanciArray.length>1){
                    danci.setTrans(alldanciArray[1].trim());
                }
                danciList.add(danci);
                map.put(alldanciArray[0].trim(),danci);

            } else {
                if(alldanciArray.length>1){
                    danciRow.setTrans(alldanciArray[1].trim());
                }
                danciRow.setDifficulty(input.difficulty);
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
                danciRow.setName(danci.trim());
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
            map.put(danci.getName(),danci);
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
                danciRow.setName(danci);
                danciRow.setDifficulty(10);
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

@Data
class Input{
    String alldanci;
    String alldancigroup;
    String article;
    int difficulty;
}