package org.workat.workat_project.picture.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureMapper {
    List<String> getPlacePictureSources(int place_id);

    List<String> getRoomPictureSources(int roomId);

    List<String> getReviewPictureSources(int reviewId);
}
