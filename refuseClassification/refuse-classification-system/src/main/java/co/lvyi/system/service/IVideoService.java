/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service
 * @Author: 吕易
 * @CreateTime: 2023-04-23  21:23
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service;

import co.lvyi.bean.admin.dto.VideoDTO;
import co.lvyi.bean.admin.entity.Video;

import java.util.List;

public interface IVideoService {
    public List<Video> selectVideoList(VideoDTO videoDTO);

    public int addVideo(VideoDTO videoDTO);

    public int deleteVideoById(Long videoId);
}

