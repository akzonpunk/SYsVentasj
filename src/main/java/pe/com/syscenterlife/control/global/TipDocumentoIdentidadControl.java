
package pe.com.syscenterlife.control.global;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pe.com.syscenterlife.modelo.GloTipodocidentidad;
import pe.com.syscenterlife.servicio.global.TdocumentoidentidadServicioI;



@Controller
public class TipDocumentoIdentidadControl {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    TdocumentoidentidadServicioI tdocumentoidentidadServicioI;

    
    Logger logger = Logger.getLogger(TipDocumentoIdentidadControl.class.getName());
    
@RequestMapping(value = {"/tdiMain" }, method = RequestMethod.GET)    
public ModelAndView inicio(Locale locale, Map<String,Object> model){
    String welcome=messageSource.getMessage("welcome.message", new Object[]{"john"}, locale);
    List<GloTipodocidentidad> lista=tdocumentoidentidadServicioI.listarEntidad();

    model.put("ListaTipodocidentidad", lista);
    model.put("message", welcome);
    model.put("startMeeting", "09:10");
    
    return new ModelAndView("global/tipodocumentoidentidad/mainTipDocumento");
}





@RequestMapping(value = {"/elimTdi" }, method = RequestMethod.GET)
public ModelAndView eliminarTipDocumento(HttpServletRequest r){
    int idEntidad=Integer.parseInt(r.getParameter("id"));
    tdocumentoidentidadServicioI.eliminarEntidad(idEntidad);
return new ModelAndView(new RedirectView("/tdiMain"));
}
  
@RequestMapping(value = {"/buscartdi"}, method = RequestMethod.POST)
public  ModelAndView buscarEntidad(Locale locale, Map<String,Object> model, HttpServletRequest r){
    String welcome=messageSource.getMessage("welcome.message", new Object[]{"John"}, locale);
    String dato=r.getParameter("dato");
    List<GloTipodocidentidad> lista=tdocumentoidentidadServicioI.listarEntidadDato(dato);
    model.put("ListaTipodocidentidad", lista);
    model.put("message", welcome);
    model.put("startMeeting", "09:10");    
return new ModelAndView("global/tipodocumentoidentidad/mainTipDocumento");
}

@RequestMapping(value = "/formTipodocidentidad", method = RequestMethod.GET)
public ModelAndView irFormulario(@ModelAttribute("modeloTipDocumento")GloTipodocidentidad Tipodocidentidad,
        BindingResult result, Model model){
    
        Map<String,String> idioma = new LinkedHashMap<String,String>();
        idioma.put("en", "Ingles");
        idioma.put("es", "Español");

        model.addAttribute("urlAccion", "guardarTTipodocidentidad"); 
       
        model.addAttribute("ListIdioma", idioma); 
    return new ModelAndView("global/tipodocumentoidentidad/formTipDocumento");
}

@RequestMapping(value = "/guardarTipDocumento", method = RequestMethod.POST)
public ModelAndView guardarEntidad(@ModelAttribute("modeloTipDocumento")GloTipodocidentidad Tipodocidentidad,
        BindingResult result, HttpServletRequest r){
        try {
        tdocumentoidentidadServicioI.guardarEntidad(Tipodocidentidad);
        return new ModelAndView(new RedirectView("/tdiMain"));
        } catch (Exception e) { 
        logger.info("Error Guardar: "+e.getMessage());    
        return new ModelAndView(new RedirectView("/formTipDocumento"));
        }    
}

@RequestMapping(value = "/formModifTipDocumento", method = RequestMethod.GET)
public ModelAndView irModificarTipDocumento(HttpServletRequest r ){
   int id=Integer.parseInt(r.getParameter("id"));
       GloTipodocidentidad entidad=null;
       entidad=tdocumentoidentidadServicioI.buscarEntidadId(id);
    return new ModelAndView("global/tipodocumentoidentidad/formTipDocumento","modeloTipodocidentidad",entidad);
}

@RequestMapping(value = "/formModif2Tipodocidentidad", method = RequestMethod.GET)
public String irModificar2Tipodocidentidad(HttpServletRequest r, Model model ){
   int id=Integer.parseInt(r.getParameter("id"));
       GloTipodocidentidad tipodocidentidad=null;
       tipodocidentidad=tdocumentoidentidadServicioI.buscarEntidadId(id);
       

        Map<String,String> idioma = new LinkedHashMap<String,String>();
        idioma.put("en", "Ingles");
        idioma.put("es", "Español");       
        model.addAttribute("modeloTipodocidentidad", tipodocidentidad);   
        model.addAttribute("ListIdioma", idioma);
        
        model.addAttribute("urlAccion", "actualizarTipodocidentidad");             
    return "global/tipodocumentoidentidad/formTipDocumento";
}

@RequestMapping(value = "actualizarTipodocidentidad", method = RequestMethod.POST)
public ModelAndView actualizarTipodocidentidad(@ModelAttribute("modeloTipodocidentidad") GloTipodocidentidad entidad,
                                      BindingResult result, HttpServletRequest r ){
        try {
        tdocumentoidentidadServicioI.modificarEntidad(entidad);
        return new ModelAndView(new RedirectView(r.getContextPath()+"/tdiMain"));
    } catch (Exception e) {
        logger.info("Error al modificar: "+e.getMessage());
        return new ModelAndView(new RedirectView(r.getContextPath()+"/formModif2Tipodocidentidad?id="+entidad.getIdTipodocidentidad()));
    }
    
}

}
