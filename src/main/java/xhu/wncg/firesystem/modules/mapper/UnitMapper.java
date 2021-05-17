package xhu.wncg.firesystem.modules.mapper;

import org.apache.ibatis.annotations.Param;
import xhu.wncg.firesystem.modules.controller.vo.UnitVO;
import xhu.wncg.firesystem.modules.controller.qo.UnitQO;
import xhu.wncg.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xhu.wncg.firesystem.modules.pojo.Unit;

import java.util.Date;
import java.util.List;

/**
 * 场所表
 * 
 * @author zhaobo
 * @email 15528330581@163.com
 * @version 2017-11-02 15:58:16
 */
@Mapper
public interface UnitMapper extends BaseMapper<UnitQO, UnitVO> {

    /**
     * 统计派出所辖区内场所分布情况
     * @param policeStationId 派出所id
     * @return list
     */
    List<UnitVO> count(Integer policeStationId);

    /**
     * 模糊查询场所,按创建时间查询场所
     * @param countKeys 关键字 startTime,endTime 起止时间
     * @return list
     */
    List<Unit> queryCount(@Param("countKeys") String countKeys,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    /**
     * 通过场所id查场所信息
     */
    Unit queryById(Integer countId);

}
