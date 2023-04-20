/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service
 * @Author: 吕易
 * @CreateTime: 2023-04-18  15:59
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.entity.RefuseSort;
import co.lvyi.system.mapper.RefuseSortMapper;
import co.lvyi.system.service.IRefuseSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefuseSortServiceImpl implements IRefuseSortService {

    @Autowired
    private RefuseSortMapper sortMapper;

    @Override
    public List<RefuseSort> selectSortList(RefuseSort refuseSort) {
        return sortMapper.selectSortList(refuseSort);
    }
}

