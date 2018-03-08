/*#
 lib=snsoft/sw/cd.js
#*/
package snsoft.sw.cd;

import js.JSCode;

/**
 * <p>标题：筹款远程服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午11:04:19</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@JSCode(remoteBean = "SN-PM.LoadService")
public interface LoadService
{
	/**
	 * 加载信用卡
	 * @param lid
	 */
	void loadCards(int lid);

	/**
	 * 计算筹款金额
	 * @param lid
	 */
	void calcCards(int lid);
}
