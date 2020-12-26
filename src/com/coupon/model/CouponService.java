package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponJDBCDAO();
	}

	public CouponVO addCoupon(String co_code, Integer co_amount, Timestamp co_start, Timestamp co_expire,
			String co_status,String m_id) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCo_code(co_code);
		couponVO.setCo_amount(co_amount);
		couponVO.setCo_start(co_start);
		couponVO.setCo_expire(co_expire);
		couponVO.setCo_status(co_status);
		couponVO.setM_id(m_id);

		dao.insert(couponVO);

		return couponVO;
	}

	public CouponVO updateCoupon(String co_id, String co_code, Integer co_amount, Timestamp co_start,
			Timestamp co_expire, String co_status,String m_id) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCo_id(co_id);
		couponVO.setCo_code(co_code);
		couponVO.setCo_amount(co_amount);
		couponVO.setCo_start(co_start);
		couponVO.setCo_expire(co_expire);
		couponVO.setCo_status(co_status);
		couponVO.setM_id(m_id);
		dao.update(couponVO);

		return couponVO;
	}

	public void deleteCoupon(String co_id) {
		dao.delete(co_id);
	}

	public CouponVO getOneCoupon(String co_id) {
		return dao.findByPrimaryKey(co_id);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}
