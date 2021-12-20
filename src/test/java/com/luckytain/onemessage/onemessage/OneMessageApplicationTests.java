package com.luckytain.onemessage.onemessage;

import com.luckytain.onemessage.onemessage.Service.SentenceService;
import com.luckytain.onemessage.onemessage.entity.sentence;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@SpringBootTest
@MapperScan("com.luckytain.onemessage.onemessage.Mapper")
class OneMessageApplicationTests {

    @Autowired
    private SentenceService sentenceService;

    @Test
    public void testCreate(){
        sentence s = new sentence();
        String m = "This is a Test Message";
        s.setSentence(m);
        s.setType("Test");
        s.setProvenance("Admin");
        s.setAuthor("Root");
        s.setUploader_uid(1);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s.setUploadtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        s.setCreatedtime(Timestamp.valueOf(sdf.format(Timestamp.from(Instant.now()))));
        s.setLength(m.length());
        s.setMark(0);
        if (sentenceService.save(s)){
            System.out.println("保存成功");
        }
        else{
            System.out.println("保存失败");
        }
    }

    @Test
    public void addOne(){
        sentence s = new sentence();
        s.setAuthor("TEST");
        s.setProvenance("TESTProvenance");
        s.setType("123");
        s.setSentence("TEST MESSAGE");
        if(sentenceService.addOne(s)){
            System.out.println("成功");
        }
        else {
            System.out.println("失败");
        }
    }

    @Test
    public void testa(){
        sentence s = sentenceService.getRandomOneByType("Test");
        System.out.println(s.toString());
    }

    @Test
    public void testList(){
        List<sentence> sentenceList = sentenceService.list();
        sentenceList.forEach(System.out::println);
    }

    @Test
    public void testListSentence(){
        List<sentence> sentenceList = sentenceService.list();
        for (sentence s: sentenceList
             ) {
            System.out.println(s.getSentence());
        }
    }

    @Test
    public void testTime(){
        final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp ts = Timestamp.from(Instant.now());
        System.out.println(sdf1.format(ts));
    }

}
