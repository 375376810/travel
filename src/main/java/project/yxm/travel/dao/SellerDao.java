package project.yxm.travel.dao;

import project.yxm.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据sid查找商家
     * @param sid
     * @return
     */
    public Seller findBySid(int sid);
}
