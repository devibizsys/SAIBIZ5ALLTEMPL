package ${pub.getPKGCodeName()}.srv.ba;

import net.ibizsys.psba.entity.IBAEntity;
import net.ibizsys.psba.core.BASchemeModelGlobal;
import net.ibizsys.psba.core.IBASchemeModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 大数据架构[${item.codeName}]DAO对象基类
 * 
 * @param <ET>
 */
public abstract class ${item.codeName}BADAOBase<ET extends IBAEntity> extends net.ibizsys.psba.dao.BADAOBase<ET>
{
	private static final Log log = LogFactory.getLog(${item.codeName}BADAOBase.class);
        public ${item.codeName}BADAOBase(){
        super();
    }

       private ${item.codeName}BASchemeModel ${srfparamname('${item.codeName}')}BASchemeModel;

      /**
  	 	* 获取大数据[${item.codeName}]模型对象
  	 	* @return
  	 	*/
      public ${item.codeName}BASchemeModel get${item.codeName}BASchemeModel() {
            if(this.${srfparamname('${item.codeName}')}BASchemeModel==null){
                try
                {
                     this.${srfparamname('${item.codeName}')}BASchemeModel = (${item.codeName}BASchemeModel)BASchemeModelGlobal.getBASchemeModel("${pub.getPKGCodeName()}.srv.ba.${item.codeName}BASchemeModel");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${item.codeName}')}BASchemeModel;
      }

      	/*
      	 * (non-Javadoc)
      	 * @see net.ibizsys.psba.dao.IBADAO#getBASchemeModel
      	 */
  		@Override
       public  IBASchemeModel getBASchemeModel() {
          return this.get${item.codeName}BASchemeModel();
      }
}