package com.rentpicture.model;

import java.util.List;

public class RentPictureService {
	private RentPictureDAO_interface dao;

	public RentPictureService() {
		dao = new RentPictureJDBCDAO();
	}

	public RentPictureVO addRentPicture(String rp_id, byte[] rp_picture, String r_id) {

		RentPictureVO rentPictureVO = new RentPictureVO();

		rentPictureVO.setRp_id(rp_id);
		rentPictureVO.setRp_picture(rp_picture);
		rentPictureVO.setR_id(r_id);

		dao.insert(rentPictureVO);

		return rentPictureVO;
	}

	public RentPictureVO updateRentPicture(byte[] rp_picture,String r_id, String rp_id) {

		RentPictureVO rentPictureVO = new RentPictureVO();	
		rentPictureVO.setRp_picture(rp_picture);
		rentPictureVO.setR_id(r_id);
		rentPictureVO.setRp_id(rp_id);
		dao.update(rentPictureVO);

		return rentPictureVO;
	}

	public void deleteRentPicture(String r_id) {
		dao.delete(r_id);
	}

	public RentPictureVO getOneRentPicture(String rp_id) {
		return dao.findByPrimaryKey(rp_id);
	}

	public List<RentPictureVO> getAll() {
		return dao.getAll();
	}
}
