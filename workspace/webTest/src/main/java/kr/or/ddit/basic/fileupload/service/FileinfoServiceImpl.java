package kr.or.ddit.basic.fileupload.service;

import java.util.List;

import kr.or.ddit.basic.fileupload.dao.FileinfoDaoImpl;
import kr.or.ddit.basic.fileupload.dao.IFileinfoDao;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoServiceImpl implements IFileinfoService {
	
	private static IFileinfoDao dao;
	private static IFileinfoService instance;
	private FileinfoServiceImpl() {
		dao = FileinfoDaoImpl.getInstance();
	}
	
	public static IFileinfoService getInstance() {
		if(instance == null) instance = new FileinfoServiceImpl();
		return instance;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVO) {
		return dao.insertFileinfo(fileVO);
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		return dao.getAllFileinfo();
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		return dao.getFileinfo(fileNo);
	}

}
