package xhu.wncg.firesystem.modules.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xhu.wncg.FireSystemApplication;

import static org.junit.Assert.*;

/**
 * @author BZhao
 * @version 2017/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FireSystemApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class DailyTableServiceTest {
    @Autowired
    private DailyTableService dailyTableService;

    @Test
    public void countDaily() throws Exception {
        dailyTableService.countDaily(null);
    }

    @Test
    public void queryAll() throws Exception {
    }

}