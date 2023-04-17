/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-17  14:14
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.entity.SysDictData;
import co.lvyi.common.utils.DictUtils;
import co.lvyi.common.utils.StringUtils;
import co.lvyi.system.mapper.SysDictDataMapper;
import co.lvyi.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据 业务层处理
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }
}

