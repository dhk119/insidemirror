package kr.co.iei.photo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dto.Member;
import kr.co.iei.photo.model.dao.PhotoDao;
import kr.co.iei.photo.model.dto.Photo;

@Service
public class PhotoService {
	@Autowired
	private PhotoDao photoDao;

	@Transactional
	public int insertPhoto(Photo p) {
		int result = photoDao.insertPhoto(p);
		return result;
	}

	public int getTotalCount() {
		int totalCount = photoDao.getTotalCount();
		return totalCount;
	}

	public List selectPhotoList(int start, int amount, Member member) {
		int end = start + amount - 1;
		List photoList = photoDao.selectPhotoList(start, end, member);
		return photoList;
	}

	@Transactional
	public int pushLike(int isLike, int photoNo, Member member) {
		int result = 0;
		if(isLike == 0){
			//좋아요 누른 경우
			result = photoDao.pushLike(photoNo, member);
		}
		else{
			//취소
			result = photoDao.deleteLike(photoNo, member);
		}
		return result;
	}

	@Transactional
	public int pushBookmark(int isBookmark, int photoNo, Member member) {
		int result = 0;
		String boardNo = null;
		if(isBookmark == 0){
			result = photoDao.pushBookmark(photoNo, boardNo, member);
		}
		else{
			result = photoDao.deleteBookmark(photoNo, member);
		}
		return result;
	}
}
