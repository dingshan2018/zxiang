package com.zxiang.project.settle.coefficient.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.coefficient.mapper.CoefficientMapper;
import com.zxiang.project.settle.coefficient.domain.Coefficient;
import com.zxiang.project.settle.coefficient.service.ICoefficientService;
import com.zxiang.common.support.Convert;

/**
 * 系数配置 服务层实现
 * 
 * @author ZXiang
 * @date 2019-05-27
 */
@Service
public class CoefficientServiceImpl implements ICoefficientService 
{
	@Autowired
	private CoefficientMapper coefficientMapper;

	/**
     * 查询系数配置信息
     * 
     * @param id 系数配置ID
     * @return 系数配置信息
     */
    @Override
	public Coefficient selectCoefficientById(Integer id)
	{
	    return coefficientMapper.selectCoefficientById(id);
	}
	
	/**
     * 查询系数配置列表
     * 
     * @param coefficient 系数配置信息
     * @return 系数配置集合
     */
	@Override
	public List<Coefficient> selectCoefficientList(Coefficient coefficient)
	{
	    return coefficientMapper.selectCoefficientList(coefficient);
	}
	
    /**
     * 新增系数配置
     * 
     * @param coefficient 系数配置信息
     * @return 结果
     */
	@Override
	public int insertCoefficient(Coefficient coefficient)
	{
	    return coefficientMapper.insertCoefficient(coefficient);
	}
	
	/**
     * 修改系数配置
     * 
     * @param coefficient 系数配置信息
     * @return 结果
     */
	@Override
	public int updateCoefficient(Coefficient coefficient)
	{
	    return coefficientMapper.updateCoefficient(coefficient);
	}

	/**
     * 删除系数配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCoefficientByIds(String ids)
	{
		return coefficientMapper.deleteCoefficientByIds(Convert.toStrArray(ids));
	}
	
}
