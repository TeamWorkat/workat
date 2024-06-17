package org.workat.workat_project.picture.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PictureMapper {
    List<String> getPlacePictureSources(int place_id);

    List<String> getRoomPictureSources(int roomId);

    List<String> getReviewPictureSources(int reviewId);

    void insertUserReviewPicture(String fileName, String fileSource, int reviewId);

    void updateUserReviewSource(Map<String, Object> srcMap);

    void deleteReviewPicture(int reviewId);

    void updateUserReviewSourceOne(int reviewId);

}
