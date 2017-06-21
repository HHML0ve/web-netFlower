package dao;

import java.util.ArrayList;

import entity.Items;

public interface IFlower {
	// 得到所有的商品
	public ArrayList<Items> getAllItems();

	// 通过ID获取商品
	public Items getItemsById(int id);

	// 获得视图
	public ArrayList<Items> getViewList(String list);

	//
	public boolean buyGoods(String sql);
}
