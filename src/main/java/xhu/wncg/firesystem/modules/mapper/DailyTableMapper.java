package xhu.wncg.firesystem.modules.mapper;

import xhu.wncg.firesystem.modules.controller.vo.DailyTableVO;
import xhu.wncg.firesystem.modules.controller.qo.DailyTableQO;
import xhu.wncg.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xhu.wncg.firesystem.modules.pojo.DailyTable;

import java.util.List;

/**
 * 日常检查表
 * 
 * @author zhaobo
 * @email 15528330581@163.com
 * @version 2017-11-02 15:58:16
 */
@Mapper
public interface DailyTableMapper extends BaseMapper<DailyTableQO, DailyTableVO> {

    /**
     * 通过场所id查询该场所的检查记录
     * @param countId
     * @return list
     */
    List<DailyTable> countDaily(Integer countId);

    /**
     * 通过日常检查表id查具体信息
     * @param dailyTableId
     * @return DailyTableVO
     */
    DailyTableVO queryByDailyTableId(Integer dailyTableId);
}
