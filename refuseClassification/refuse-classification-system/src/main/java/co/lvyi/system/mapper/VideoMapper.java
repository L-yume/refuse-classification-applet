package co.lvyi.system.mapper;

import co.lvyi.bean.admin.dto.VideoDTO;
import co.lvyi.bean.admin.entity.Video;

import java.util.List;

public interface VideoMapper {
    /**
     * 根据条件分页查询视频数据
     */
    List<Video> selectVideoList(VideoDTO videoDTO);

    public int addVideo(Video video);

    public int deleteVideoById(Long videoId);
}
