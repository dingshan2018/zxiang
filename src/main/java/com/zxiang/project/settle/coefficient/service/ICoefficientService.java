package com.zxiang.project.settle.coefficient.service;

import com.zxiang.project.settle.coefficient.domain.Coefficient;
import java.util.List;

/**
 * 系数配置 服务层
 * 
 * @author ZXiang
 * @date 2019-05-27
 */
public interface ICoefficientService 
{
	/**
     * 查询系数配置信息
     * 
     * @param id 系数配置ID
     * @return 系数配置信息
     */
	public Coefficient selectCoefficientById(Integer id);
	
	/**
     * 查询系数配置列表
     * 
     * @param coefficient 系数配置信息
     * @return 系数配置集合
     */
	public List<Coefficient> selectCoefficientList(Coefficient coefficient);
	
	/**
     * 新增系数配置
     * 
     * @param coefficient 系数配置信息
     * @return 结果
     */
	public int insertCoefficient(Coefficient coefficient);
	
	/**
     * 修改系数配置
     * 
     * @param coefficient 系数配置信息
     * @return 结果
     */
	public int updateCoefficient(Coefficient coefficient);
		
	/**
     * 删除系数配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCoefficientByIds(String ids);
	
	
	public int selectCoefficientTotal(String type);
	
	
	public Coefficient selectCoefficientByType(String type);
	
}
