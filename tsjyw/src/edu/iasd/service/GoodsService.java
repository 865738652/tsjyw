package edu.iasd.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsForm;
import edu.iasd.form.GoodsTypeVo;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.GoodsState;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.ViewGoods;
import edu.iasd.pojo.ViewGoodsType;

@Service
@Transactional
public interface GoodsService {
	public Page getGoodsByBusiness(int pageNumber, int pageSize, int businessId);
	
	public ViewGoods getGoods(int goodsId);
	
	public String createGoods(GoodsForm goods);
	
	public boolean modifyGoods(GoodsForm goods);
	
	public boolean deleteGoods(int goodsId);
	
	public List<GoodsState> getGoodsState();
	
	
	//首页获取数据的分页函数，根据商品的三个类型获取，作废的商品不能算数
	public Page getAllGoods(int pageNumber, int pageSize,JSONObject searchJson);
	
	
	public Page getMyAllGoods(int pageNumber,int pageSize,int businessId,Integer userId,String queryString);
	
	public List<Business> getBusiness();
	
	public List<GoodsType> getGoodsType();

	public List<ViewGoods> getAll();
	
	public List<ViewGoodsType> getgoodsType(int goodsId);
	
	public ViewGoods getGoodsId(Integer goodsId);
	
	
	//在商品详情页面表示商品能不能购买
	public String GoodsIsOrNotBuy(Integer goodsId);
	
	//根据商品的类型获取类似商品
	public List<ViewGoods> getGoodsByType(List<GoodsTypeVo> goodsTypes);
	
	public int getGoodsRemainNum(Integer goodsId);
	
	public boolean editGoodsIntrol(Integer goodsId,String goodsIntro) throws Exception;
	
	
}
