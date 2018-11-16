/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.syscenterlife.servicio.global;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.syscenterlife.dao.global.TdocumentoidentidadDaoI;

import pe.com.syscenterlife.modelo.GloTipodocidentidad;

@Service
@Transactional
public class TdocumentoidentidadServicioImpl implements TdocumentoidentidadServicioI{
     
    @Autowired
    public TdocumentoidentidadDaoI daoI;
    
    @Override
    public List<GloTipodocidentidad> listarEntidad(){ return daoI.listarEntidad();}
    @Override
    public List<GloTipodocidentidad> listarEntidadDato(String dato){ return daoI.listarEntidadDato(dato);}
    @Override
    public GloTipodocidentidad buscarEntidadId(int id){return daoI.buscarEntidadId(id);}
    @Override
    public void guardarEntidad(GloTipodocidentidad tipodocidentidad){daoI.guardarEntidad(tipodocidentidad);}
    @Override
    public void eliminarEntidad(int id){daoI.eliminarEntidad(id);}
    @Override
    public void modificarEntidad(GloTipodocidentidad tipodocidentidad){daoI.modificarEntidad(tipodocidentidad);}
    
    
    
}
