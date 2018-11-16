
package pe.com.syscenterlife.dao.global;

import java.util.List;
import org.springframework.stereotype.Repository;
import pe.com.syscenterlife.SysDataAccess;

import pe.com.syscenterlife.modelo.GloTipodocidentidad;

@Repository
public class TdocumentoidentidadDaoImpl extends SysDataAccess<Integer, GloTipodocidentidad> implements TdocumentoidentidadDaoI{
    
   @SuppressWarnings("unchecked")
    
    @Override
    public List<GloTipodocidentidad> listarEntidad(){return getListAll();}
    @Override
    public List<GloTipodocidentidad> listarEntidadDato(String dato){
            return (List<GloTipodocidentidad>)sessionFactory.getCurrentSession()
                .createQuery("SELECT p from GloTipodocidentidad p WHERE  p.igvPorcent LIKE ?1 ")
                .setParameter(1, "%"+dato+"%")
                .list(); 
    }
    @Override
    public GloTipodocidentidad buscarEntidadId(int id){return getById(id);}
    @Override
    public void guardarEntidad(GloTipodocidentidad tipodocidentidad){savev(tipodocidentidad);}
    @Override
    public void eliminarEntidad(int id){delete(id);}
    @Override
    public void modificarEntidad(GloTipodocidentidad tipodocidentidad){update(tipodocidentidad);}




    
}
