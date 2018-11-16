
package pe.com.syscenterlife.servicio.global;

import java.util.List;
import pe.com.syscenterlife.modelo.GloTipodocidentidad;


public interface TdocumentoidentidadServicioI {
    public List<GloTipodocidentidad> listarEntidad();
    public List<GloTipodocidentidad> listarEntidadDato(String dato);
    public GloTipodocidentidad buscarEntidadId(int id);
    public void guardarEntidad(GloTipodocidentidad tipodocidentidad);
    public void eliminarEntidad(int id);
    public void modificarEntidad(GloTipodocidentidad tipodocidentidad); 
}
