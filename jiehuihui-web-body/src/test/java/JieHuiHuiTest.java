import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jiehuihui.common.utils.IdWorkerJie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JieHuiHuiTest.class)
public class JieHuiHuiTest {



    /**
     * 测试mybatisPlus自带工具类IdWorker生成id
     */
    @Test
    public void IdTest(){
        System.out.println(IdWorker.getTimeId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.get32UUID());


    }


    /**
     * 测试时间
     */
    public void DateTest() throws ParseException {
        String beginTime=new String("2017-06-09 10:22:22");
        String endTime=new String("2017-05-08 11:22:22");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sd1=df.parse(beginTime);
        Date sd2=df.parse(endTime);
        System.out.println(sd1.before(sd2));
        System.out.println(sd1.after(sd2));
    }

}
