import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jiehuihui.common.utils.IdWorkerJie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

}
