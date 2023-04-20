package co.lvyi.system.service;

import co.lvyi.bean.admin.entity.RefuseSort;

import java.util.List;

public interface IRefuseSortService {

    public List<RefuseSort> selectSortList(RefuseSort refuseSort);
}
