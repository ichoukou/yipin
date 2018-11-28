package com.ytoxl.module.yipin.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Links;
import com.ytoxl.module.yipin.content.mapper.LinksMapper;
import com.ytoxl.module.yipin.content.service.LinksService;

/**
 * @作者：罗典
 * @描述：友情链接操作service层
 * @时间：2013-12-03
 * */
@Service
public class LinkServiceImpl implements LinksService {
	@Autowired
	private LinksMapper<Links> linksMapper;

	/**
	 * @作者：罗典
	 * @描述：查询友情链接(传入对象为空时，查询所有)
	 * @时间：2013-12-04
	 * */
	public List<Links> queryByLinks(Links links) throws YiPinStoreException{
		return  linksMapper.queryByLinks(links);
	}

	/**
	 * @作者：罗典
	 * @描述：新增友情链接
	 * @时间：2013-12-04
	 * */
	@Override
	public void saveLinks(Links links) throws YiPinStoreException{
		linksMapper.add(links);
	}

	/**
	 * @作者：罗典
	 * @描述：修改友情链接
	 * @时间：2013-12-04
	 * */
	@Override
	public int updateLinksById(Links links) throws YiPinStoreException{
		return linksMapper.update(links);
	}

	/**
	 * @作者：罗典
	 * @描述：根据ID删除友情链接
	 * @时间：2013-12-04
	 * */
	@Override
	public int deleteById(Integer linkId) throws YiPinStoreException{
		return linksMapper.del(linkId);
	}
	/**
	 * 
	 * @描述：根据ID查询友情链接
	 * 
	 * */
	@Override
	public Links queryLinksById(Integer linkId)throws YiPinStoreException{
		return linksMapper.get(linkId);
	}
	/**
	 * 
	 * @描述：根据Name查询友情链接
	 * 
	 * */
	@Override
	public Links getLinksByName(String name) throws YiPinStoreException{
		return linksMapper.getLinksByName(name);
	}
	public LinksMapper<Links> getLinksMapper() {
		return linksMapper;
	}

	public void setLinksMapper(LinksMapper<Links> linksMapper) {
		this.linksMapper = linksMapper;
	}



	

}
