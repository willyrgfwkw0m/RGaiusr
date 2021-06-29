package xhu.wncg.firesystem.modules.service;

import xhu.wncg.firesystem.modules.controller.vo.UnitVO;
import xhu.wncg.firesystem.modules.controller.qo.UnitQO;
import xhu.wncg.common.service.BaseService;
import xhu.wncg.firesystem.modules.pojo.Unit;

import java.util.List;

/**
 * 场所表
 *
 * @author zhaobo
 * @version 2017-11-02 15:58:16
 * @email 15528330581@163.com
 */
public interface UnitService extends BaseService<UnitQO, UnitVO> {

    /**
     * 统计派出所辖区中场所分布情况
     *
     * @param policeStationId 派出所id
     * @return list
     */
    List<UnitVO> count(Integer policeStationId);

    /**
     * 模糊查询场所,按创建时间查询场所
     *
     * @param params
     * @return list
     */
    List<Unit> queryCount(UnitQO params);

    /**
     * 通过场所id查场所信息
     *
     * @param countId
     * @return Unit
     */
    Unit queryById(Integer countId);
}
