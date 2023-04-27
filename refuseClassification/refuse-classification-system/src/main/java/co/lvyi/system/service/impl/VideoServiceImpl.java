/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-23  21:24
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.dto.VideoDTO;
import co.lvyi.bean.admin.entity.Video;
import co.lvyi.system.mapper.VideoMapper;
import co.lvyi.system.service.IVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectVideoList(VideoDTO videoDTO) {
        return videoMapper.selectVideoList(videoDTO);
    }

    @Override
    public int addVideo(VideoDTO videoDTO) {
        Video video = new Video();
        BeanUtils.copyProperties(videoDTO, video);
        video.setStatus("0");
        return videoMapper.addVideo(video);
    }

    @Override
    public int deleteVideoById(Long videoId) {
        return videoMapper.deleteVideoById(videoId);
    }
}

