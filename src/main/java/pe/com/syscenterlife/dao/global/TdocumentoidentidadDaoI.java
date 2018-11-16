
package pe.com.syscenterlife.dao.global;

import java.util.List;
import pe.com.syscenterlife.modelo.GloTipodocidentidad;



public interface TdocumentoidentidadDaoI {
    public List<GloTipodocidentidad> listarEntidad();
    public List<GloTipodocidentidad> listarEntidadDato(String dato);
    public GloTipodocidentidad buscarEntidadId(int id);
    public void guardarEntidad(GloTipodocidentidad tipodocidentidad);
    public void eliminarEntidad(int id);
    public void modificarEntidad(GloTipodocidentidad tipodocidentidad);
}
