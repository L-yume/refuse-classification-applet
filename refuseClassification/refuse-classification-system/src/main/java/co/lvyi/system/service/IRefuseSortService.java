package co.lvyi.system.service;

import co.lvyi.bean.admin.entity.RefuseSort;

import java.util.List;

public interface IRefuseSortService {

    public List<RefuseSort> selectSortList(RefuseSort refuseSort);

    public RefuseSort selectSortById(Integer sortId);

    public int insertSort(RefuseSort refuseSort);

    public int updateSort(RefuseSort refuseSort);

    public int deleteSortById(Integer sortId);
}
